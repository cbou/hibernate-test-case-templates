package org.hibernate.bugs.domain;

import org.junit.experimental.categories.Categories;

import javax.persistence.*;
import java.util.Set;

/**
 * User Entity.
 * @author Charles Bourasseau
 */
@Table(name = "projects")
@Entity
@Categories.IncludeCategory()
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    public Integer getId() {
        return id;
    }

    @Column
    private String name;

    @ManyToOne
    private User owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
