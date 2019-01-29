/*
 * Copyright (c) 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.hibernate.bugs.domain;

import org.junit.experimental.categories.Categories;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User Entity.
 * @author Charles Bourasseau
 */
@Table(name = "folders")
@Entity
@Categories.IncludeCategory
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    public Integer getId() {
        return id;
    }

    @Column
    private String name;

    @OneToMany(mappedBy = "folder",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Project> projects = new HashSet<Project>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}