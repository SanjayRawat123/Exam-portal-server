/*
 * Author Name:
 * Date: 12/16/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.controller;

import com.exam.example.examportalproject.model.category.Category;
import com.exam.example.examportalproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/category")
@CrossOrigin("*")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //Add category
    @PostMapping(value = "/")

    public ResponseEntity<?> addCategory(@RequestBody  Category category){

        Category category1= this.categoryService.addCategory(category);

       return ResponseEntity.ok(category1);

    }

    //get category

    @GetMapping(value = "/{cId}")

    public Category getCategory(@PathVariable ("cId") long cId){

        return this.categoryService.getCategoryById(cId);
    }


    //get all catetries
    @GetMapping(value = "/categories")
    public ResponseEntity<?> getCategroy(){
        return  ResponseEntity.ok(this.categoryService.getCategories());

    }

    //Update category
    //remember one thing
    @PutMapping(value = "/")
    public Category updateCategory(@RequestBody Category category){
        return this.categoryService.updateCategory(category);
    }

    //delete category

    @DeleteMapping(value = "/{cId}")
    public void deleteCatrgory(@PathVariable ("cId") long cId){
        this.categoryService.deleteCategory(cId);
    }

}
