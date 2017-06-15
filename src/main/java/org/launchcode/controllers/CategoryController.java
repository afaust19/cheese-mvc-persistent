package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by afaust on 6/15/17.
 */
@Controller
@RequestMapping("category")                         //root route
public class CategoryController {

    @Autowired                                      //annotation that is part of Spring's dependency injection framework
    private CategoryDao categoryDao;                //categoryDao object is the mechanism with which we interact with objects stored in the database
                                                    //Spring does the work of creating a class that implements CategoryDao interface and putting one of those objects in the categoryDao field when the application starts up
    @RequestMapping(value = "")                     //route is /category
    public String index(Model model) {
        model.addAttribute("categories", categoryDao.findAll());   //collection (iterable) of all Category objects managed by categoryDao into view
        model.addAttribute("title", "Categories");
        return "category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)    //route is /category/add, accepts GET requests at this route
    public String add(Model model) {                                //allows the user to create a new category
        model.addAttribute(new Category());                         //creates a new Category object using the default constructor; passes it into the view with the key "category"; longer method--> Category newCategory = new Category; model.addAttribute("category", newCategory);
        model.addAttribute("title", "Add Category");
        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)                         //route is /category/add, accepts POST requests at this route
    public String add(Model model,
                      @ModelAttribute @Valid Category category, Errors errors) {

        if (errors.hasErrors()) {                                                       //if there are errors in form submission
            model.addAttribute("title", "Add Category");        //do you need to pass Category object back to model?
            return "category/add";                                                      //render category/add view again
        }

        categoryDao.save(category);                                                 //if no errors, then save new category object to database through categoryDao interface
        return "redirect:";                                     //redirect to index handler
    }


}
