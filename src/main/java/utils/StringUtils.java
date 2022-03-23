package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

public class StringUtils {

    public static void writeNewsToFile(String fileName, String content) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static URL createUrl(String baseURL, String apiKey, String keyword, String country) {
        URL requiredURL = null;
        try {
            requiredURL = new URL(baseURL
                    + "/everything?q=" + keyword
                    + "&apiKey=" + apiKey
                    + "&language=" + country);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return requiredURL;
    }

    public static String readFrom(URL url) throws IOException {
        String content = "";
        Scanner sc = new Scanner(url.openStream(), StandardCharsets.UTF_8);
        while (sc.hasNext()) {
            content += sc.nextLine();
            content.replaceAll("\n","");
        }
        sc.close();
        return content;
    }

    public static String fileNameWithTimestamp(String prefix, String extension) {
        long timestamp = new Date().getTime();
        return prefix + timestamp + extension;
    }
}
