package controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static utils.HttpConnector.*;
import static utils.StringUtils.*;

public class NewsController {
    private final String fileName = fileNameWithTimestamp("news", ".txt");

    public void saveArticlesToFile(String forCountry, String onTopic) {
        String responseAsString = fetchAllArticlesByCountryAndTopic(forCountry, onTopic);

        final JSONParser parse = new JSONParser();
        try {
            JSONObject jsonAsObject = (JSONObject) parse.parse(responseAsString);
            JSONArray jsonArrayExtracted = (JSONArray) jsonAsObject.get("articles");
            String articleData = "";
            for (int i = 0; i < jsonArrayExtracted.size(); i++) {
                JSONObject arrayItem = (JSONObject) jsonArrayExtracted.get(i);
                String title = arrayItem.get("title").toString().replaceAll("\n", "");
                String desc = arrayItem.get("description").toString().replaceAll("\n", "");
                String author = arrayItem.get("author").toString().replaceAll("\n", "");
                articleData += "\n" + title + ":" + desc + ":" + author;
            }
            writeNewsToFile(fileName, articleData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String fetchAllArticlesByCountryAndTopic(String forCountry, String onTopic) {
        final String NEWS_URL = baseUrl();
        final String API_KEY = fetchApiKey();
        final URL url = createUrl(NEWS_URL, API_KEY, onTopic, forCountry);

        String responseAsString = "";

        if (onTopic.isEmpty() || forCountry.isEmpty()) {
            throw new IllegalArgumentException("Params should not be empty.");
        } else {
            try {
                HttpURLConnection connection = connectTo(url);
                int responsecode = connection.getResponseCode();
                if (responsecode != 200) {
                    throw new IllegalStateException("Request failed - response code: " + responsecode);
                } else {
                    responseAsString = readFrom(url);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseAsString;
    }

    public String getFileName() {
        return fileName;
    }
}
