import controller.NewsController;

public class NewsApp {
    public static void main(String[] args) {
        runApplication();
    }

    private static void runApplication() {
        NewsController controller = new NewsController();
        controller.getArticles("pl", "business");
    }
}
