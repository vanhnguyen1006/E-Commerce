package com.example.demo.controller.productionController;

import com.example.demo.production.Category;
import com.example.demo.production.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    @Autowired
    private final ProductRepository productRepository;


    public CategoryController(CategoryService categoryService,
                              ProductRepository productRepository) {
        this.categoryService = categoryService;
        this.productRepository = productRepository;
    }

    // Category
    @GetMapping("admin/categories")
    public String showSignUpForm(Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "category/index";
    }

    @GetMapping("admin/categories/add-category")
    public String addCat(Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "category/add-category";
    }

    @PostMapping("admin/categories/add-category")
    public String addCategory(@Valid Category category,
                              BindingResult result,
                              Model model){
        if (result.hasErrors()){
            return "category/add-category";
        }
        categoryService.save(category);
        model.addAttribute("categories", categoryService.findAll());
        return "redirect:/admin/categories/";
    }

    @GetMapping("admin/categories/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Category category = categoryService.findOne(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid category Id: " + id));
        model.addAttribute("category", category);
        return "category/update-category";
    }

    @PostMapping("admin/categories/update-category/{id}")
    public String updateCategory(@PathVariable("id") long id, @Valid Category category,
                                 BindingResult result, Model model){
        if (result.hasErrors()){
            category.setId(id);
            return "category/update-category";
        }
        categoryService.save(category);
        model.addAttribute("categories", categoryService.findAll());
        return "redirect:/admin/categories/";
    }

    @GetMapping("admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") long id, Model model) {
        Category category = categoryService.findOne(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid category Id: " + id));
        categoryService.delete(category);
        model.addAttribute("categories", categoryService.findAll());
        return "redirect:/admin/categories/";
    }


}
