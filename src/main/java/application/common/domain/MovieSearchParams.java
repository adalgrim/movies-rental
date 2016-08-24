package application.common.domain;

/**
 * Created by Adam_Skowron on 19.08.2016.
 */
public class MovieSearchParams {

    private String title;

    private String actorName;

    private int lengthFrom = 60;

    private int lengthTo = 180;

    private int yearFrom = 1995;

    private int yearTo = 2016;

    private int languageId;

    private int categoryId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public int getLengthFrom() {
        return lengthFrom;
    }

    public void setLengthFrom(int lengthFrom) {
        this.lengthFrom = lengthFrom;
    }

    public int getLengthTo() {
        return lengthTo;
    }

    public void setLengthTo(int lengthTo) {
        this.lengthTo = lengthTo;
    }


    public int getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(int yearFrom) {
        this.yearFrom = yearFrom;
    }

    public int getYearTo() {
        return yearTo;
    }

    public void setYearTo(int yearTo) {
        this.yearTo = yearTo;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
