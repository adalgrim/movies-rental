package application.common.domain;

/**
 * Helper class for REST to return id and name of something.
 *
 * Created by Adam_Skowron on 25.08.2016.
 */
public class RestIdNamePair {

    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
