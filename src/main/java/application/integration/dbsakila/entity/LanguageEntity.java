package application.integration.dbsakila.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Adam_Skowron on 22.08.2016.
 */
@Entity
@Table(name = "language")
public class LanguageEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "language_id")
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
