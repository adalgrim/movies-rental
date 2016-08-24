package application.common.domain;

/**
 * Created by Adam_Skowron on 22.08.2016.
 */
public final class MovieSearchParamsBuilder {

    private String title;
    private String actorName;
    private int lengthFrom = 60;
    private int lengthTo = 180;
    private int yearFrom = 1995;
    private int yearTo = 2016;
    private int languageId;
    private int categoryId;

    private MovieSearchParamsBuilder() {
    }

    public static MovieSearchParamsBuilder aMovieSearchParams() {
        return new MovieSearchParamsBuilder();
    }

    public MovieSearchParamsBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public MovieSearchParamsBuilder withActorName(String actorName) {
        this.actorName = actorName;
        return this;
    }

    public MovieSearchParamsBuilder withLengthFrom(int lengthFrom) {
        this.lengthFrom = lengthFrom;
        return this;
    }

    public MovieSearchParamsBuilder withLengthTo(int lengthTo) {
        this.lengthTo = lengthTo;
        return this;
    }

    public MovieSearchParamsBuilder withYearFrom(int yearFrom) {
        this.yearFrom = yearFrom;
        return this;
    }

    public MovieSearchParamsBuilder withYearTo(int yearTo) {
        this.yearTo = yearTo;
        return this;
    }

    public MovieSearchParamsBuilder withLanguageId(int languageId) {
        this.languageId = languageId;
        return this;
    }

    public MovieSearchParamsBuilder withCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public MovieSearchParams build() {
        MovieSearchParams movieSearchParams = new MovieSearchParams();
        movieSearchParams.setTitle(title);
        movieSearchParams.setActorName(actorName);
        movieSearchParams.setLengthFrom(lengthFrom);
        movieSearchParams.setLengthTo(lengthTo);
        movieSearchParams.setYearFrom(yearFrom);
        movieSearchParams.setYearTo(yearTo);
        movieSearchParams.setLanguageId(languageId);
        movieSearchParams.setCategoryId(categoryId);
        return movieSearchParams;
    }
}
