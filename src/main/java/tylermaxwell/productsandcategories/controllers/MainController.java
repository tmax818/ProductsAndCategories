package tylermaxwell.productsandcategories.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tylermaxwell.productsandcategories.models.Category;
import tylermaxwell.productsandcategories.models.Product;
import tylermaxwell.productsandcategories.services.CategoryService;
import tylermaxwell.productsandcategories.services.ProductService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ProductService prodService;

    @Autowired
    private CategoryService catService;

    @GetMapping("/")
    public String index(Model model) {
        List<Product> products = prodService.allProducts();
        List<Category> categories = catService.allCategories();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);

        return "index.jsp";
    }


}
