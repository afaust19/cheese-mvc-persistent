package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by afaust on 6/15/17.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @OneToMany                                          //one-to-many relationship = each one category will have many cheeses, but each cheese can only have one category
    @JoinColumn(name = "category_id")                   //tells Hibernate to use the category_id column of the cheese table to determine which cheese belong to a given category (category_id is automatically created by Hibernate in the Cheese class (category field)
    private List<Cheese> cheeses = new ArrayList<>();   //creates a new field named cheeses (empty ArrayList of Cheese objects) - Hibernate will population this list for us using above annotation specifications

    public Category() {}        //default constructor

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cheese> getCheeses() { return cheeses;}         //not part of the studio but shown in the video?

    public int getId() {
        return id;
    }
}
