package org.launchcode.models.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

/**
 * Created by afaust on 6/26/17.
 */
//don't need @Entity because it's not a persistent class
public class AddMenuItemForm {

    // Fields needed to render the form

    private Menu menu;

    public Iterable<Cheese> cheeses;   //cannot be rendered in view as private - shouldn't * call on the public getter?

    // Fields needed to process the form

    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    // Default No-arg Constructor (needed for model binding)

    public AddMenuItemForm() {}

    // Constructor to set menu and cheeses

    public AddMenuItemForm(Menu menu, Iterable<Cheese> cheeses) {
        this.menu = menu;
        this.cheeses = cheeses;
    }

    // Getters and Setters

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Iterable<Cheese> getCheese() {
        return cheeses;
    }

    public void setCheese(Iterable<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }
}
