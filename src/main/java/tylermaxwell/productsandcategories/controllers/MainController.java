package tylermaxwell.productsandcategories.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tylermaxwell.productsandcategories.models.Category;
import tylermaxwell.productsandcategories.models.Product;
import tylermaxwell.productsandcategories.services.CategoryService;
import tylermaxwell.productsandcategories.services.ProductService;

import java.util.List;

@Controller
public class MainController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public MainController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String index(Model model){
        List<Product> products = productService.getAll();
        List<Category> categories = categoryService.getAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "index.jsp";

    }
}
