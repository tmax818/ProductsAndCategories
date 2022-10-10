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
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @GetMapping("/products/new")
    public String newProduct(@ModelAttribute("product") Product product) {

        return "products/new.jsp";
    }

    @PostMapping("/products/new")
    public String addNewProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "products/new.jsp";
        }else {
            productService.addProduct(product);
            return "redirect:/";
        }
    }

    @GetMapping("/products/{id}")
    public String showProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("categories", categoryService.getAssignedProducts(product));
        model.addAttribute("newCategories", categoryService.getUnassignedProducts(product));
        model.addAttribute("product", product);
        return "products/show.jsp";
    }

    @PostMapping("/products/{id}")
    public String editProduct(@PathVariable("id") Long id, @RequestParam(value="catId") Long catId, Model model) {
        Product product = productService.findById(id);
        Category category = categoryService.findById(catId);
        product.getCategories().add(category);
        productService.updateProduct(product);
        model.addAttribute("assignedCategories", categoryService.getAssignedProducts(product));
        model.addAttribute("unassignedCategories", categoryService.getUnassignedProducts(product));
        return "redirect:/products" + id;
    }
}
