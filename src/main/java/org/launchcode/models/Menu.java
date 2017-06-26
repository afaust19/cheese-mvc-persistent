package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by afaust on 6/15/17.
 */
@Entity
public class Menu {

    //Fields with field annotations (determine how class fields are persisted in a database)

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @Id
    @GeneratedValue
    private int id;

    @ManyToMany                     //this class (Menu) is related to the Cheese class by a many-to-many relationship, and this collection (list of cheese objects) should be how one part of that relationship is mapped
    private List<Cheese> cheeses;   //e.g. for a given menu object, this particular collection will store the cheeses that are related to that particular object (the cheeses that are on that particular menu)

    //No-arg Default Constructor  - required for every Hibernate-managed class (allows Hibernate to create a new instance of the object, no matter what properties it may have

    public Menu()  {}

    //Constructor - not in video?

    public Menu(String name) {
        this.name = name;
    }

    //Methods

    public void addItem(Cheese item) {              //allows user to add items to the menu
        cheeses.add(item);
    }

    //Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }
}
