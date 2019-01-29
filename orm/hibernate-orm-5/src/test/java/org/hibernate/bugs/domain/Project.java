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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    private Folder folder;

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

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Project))
            return false;

        return
                id != null &&
                        id.equals(((Project) o).id);
    }
    @Override
    public int hashCode() {
        return 31;
    }
}
