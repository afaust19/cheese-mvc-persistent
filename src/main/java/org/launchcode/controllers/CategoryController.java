package org.launchcode.controllers;

import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
