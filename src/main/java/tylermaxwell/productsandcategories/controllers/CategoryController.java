package tylermaxwell.productsandcategories.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tylermaxwell.productsandcategories.models.Category;
import tylermaxwell.productsandcategories.models.Product;
import tylermaxwell.productsandcategories.services.CategoryService;
import tylermaxwell.productsandcategories.services.ProductService;

import javax.validation.Valid;

@Controller
public class CategoryController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public CategoryController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @GetMapping("/categories/new")
    public String newCategory(@ModelAttribute("category") Category category) {
        return "categories/new.jsp";
    }

    @PostMapping("/categories/new")
    public String addNewCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "categories/new.jsp";
        }else {
            categoryService.addCategory(category);
            return "redirect:/";
        }
    }

    @GetMapping("/categories/{id}")
    public String showCategory(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("products", productService.getAssignedCategories(category));
        model.addAttribute("newProducts", productService.getUnassignedCategories(category));
        model.addAttribute("category", categoryService.findById(id));
        return "categories/show.jsp";
    }

    @PostMapping("/categories/{id}")
    public String editCategory(@PathVariable("id") Long id, @RequestParam(value="productId") Long productId, Model model) {
        Category category = categoryService.findById(id);
        Product product = productService.findById(productId);
        category.getProducts().add(product);
        categoryService.updateCategory(category);
        model.addAttribute("products", productService.getAssignedCategories(category));
        model.addAttribute("newProducts", productService.getUnassignedCategories(category));
        return "redirect:/categories/" + id;
    }


}
