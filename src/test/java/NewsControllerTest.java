import controller.NewsController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NewsControllerTest {

    @Test
    void shouldFetchArticlesFromNewsApiWhenCorrectParams() {
        String countryPl = "pl";
        String topic = "business";
        NewsController newsController = new NewsController();
        String articles = newsController.fetchAllArticlesByCountryAndTopic(countryPl, topic);
        Assertions.assertThat(articles.isEmpty()).isFalse();
    }

    @Test
    void shouldThrowExceptionWhenEmptyTopicParam() {
        String countryPl = "pl";
        String topic = "";
        NewsController newsController = new NewsController();
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    newsController.fetchAllArticlesByCountryAndTopic(countryPl, topic);
                });
    }

    @Test
    void shouldThrowExceptionWhenEmptyCountryParam() {
        String countryPl = "";
        String topic = "business";
        NewsController newsController = new NewsController();
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    newsController.fetchAllArticlesByCountryAndTopic(countryPl, topic);
                });
    }

    @Test
    void shouldGenerateAFileWhenArticlesFetched() {
        String countryPl = "pl";
        String topic = "business";
        NewsController newsController = new NewsController();
        String fileName = newsController.getFileName();
        Path path = Paths.get("C:\\Users\\48785\\IdeaProjects\\business-news-pl\\" + fileName);
        newsController.saveArticlesToFile(countryPl, topic);
        try {
            org.junit.jupiter.api.Assertions.assertNotNull(Files.find(path, 1,
                    (p, basicFileAttributes) ->
                            p.getFileName().toString().equalsIgnoreCase(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
