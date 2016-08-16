package application.common.types;

/**
 * Created by Adam_Skowron on 11.08.2016.
 */
public enum Rating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");

    private String rating;

    Rating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }
}
