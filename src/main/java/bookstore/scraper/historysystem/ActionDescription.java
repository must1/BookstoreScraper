package bookstore.scraper.historysystem;

public enum ActionDescription {

    CATEGORIZED_BOOKS_RANKING("Fetched categorized books ranking"),
    BEST_SELLERS("Fetched bestsellers"),
    MOST_PRECISE_BOOK("Fetched most precise book"),
    CATEGORIZED_BOOK("Fetched categorized books");


    @Override
    public String toString() {
        return actionDescription;
    }

    private String actionDescription;

    ActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

}
