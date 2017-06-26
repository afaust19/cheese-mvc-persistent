package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Entity                                         //every property (field) in that class will be stored in the database unless you say not to (use @Transient for this)
public class Cheese {

    @Id                                         //says that this should be the primary id
    @GeneratedValue                             //persistence engine (data layer) handles the creation of unique ids for each object
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

    @ManyToOne                                  //specifies that there can be many cheeses for any one category
    private Category category;                  //Hibernate will create a column named category_id (based on the field name) and when a Cheese is stored, this column will contain the id of its category object

    @ManyToMany(mappedBy = "cheeses")           //tells Hibernate how to store and populate objects from the list
    private List<Menu> menus;                   //represents the list of Menu objects that a given cheese is contained in (e.g. the items in this list should correspond to the Menu objects that contain a given Cheese object in their cheeses list

    //public Cheese(String name, String description, Category category) {
    //    this.name = name;
    //    this.description = description;
    //    this.category = category;
    //}

    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Cheese() { }                         //keep the default constructor (Hibernate will use this)

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
