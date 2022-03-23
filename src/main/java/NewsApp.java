import controller.NewsController;

public class NewsApp {
    public static void main(String[] args) {
        runApplicationFor("pl", "business");
    }

    public static void runApplicationFor(String country, String topic) {
        NewsController controller = new NewsController();
        controller.saveArticlesToFile(country, topic);
    }
}
