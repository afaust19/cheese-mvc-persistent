package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Created by afaust on 6/15/17.
 */

@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    MenuDao menuDao;

    @Autowired
    CheeseDao cheeseDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("menus", menuDao.findAll());
        model.addAttribute("title", "Menus");
        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Menu());
        model.addAttribute("title", "Add Menu");
        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid Menu menu,
                      Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }

        menuDao.save(menu);
        return "redirect:view/" + menu.getId();
    }

    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId) {                         //allows the user to view all menus
        model.addAttribute("menu", menuDao.findOne(menuId));                   //find menu object using menuId
        return "menu/view";
    }

    //@RequestMapping(value = "view/menu/add-item/{menuId}", method = RequestMethod.GET)
    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int menuId) {                          //allows the user to add a cheese to a specific menu
        Menu menu = menuDao.findOne(menuId);                                                //get the menu object using the menuId in the URL path
        AddMenuItemForm form = new AddMenuItemForm(menu, cheeseDao.findAll());              //create a new instance of the AddMenuItemForm class (form will be populated with the specific menu that matches the menuId and a list of all cheeses for the user to choose from)

        model.addAttribute("form", form);                                      //pass new object to view
        model.addAttribute("title", "Add item to menu: " + menu.getName()); //pass title to view
        return "menu/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(@ModelAttribute @Valid AddMenuItemForm form,
                          Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "menu/add-item";
        }

        Menu menu = menuDao.findOne(form.getMenuId());              //find the given menu by id
        Cheese cheese = cheeseDao.findOne(form.getCheeseId());      //find the given cheese by id

        menu.addItem(cheese);                                       //add cheese item to the menu
        menuDao.save(menu);                                         //save the menu (if the menu isn't saved here, the changes will not be pushed to the database, and hence will be lost)
        return "redirect:/menu/view/" + menu.getId();


    }


}
