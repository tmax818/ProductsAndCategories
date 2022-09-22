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

    @GetMapping("/products/new")
    public String newProduct(@ModelAttribute("product") Product product) {

        return "/products/new.jsp";
    }

    @PostMapping("/products/new")
    public String addNewProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "/products/new.jsp";
        }else {
            prodService.addProduct(product);
            return "redirect:/";
        }
    }

    @GetMapping("/products/{id}")
    public String showProduct(@PathVariable("id") Long id, Model model) {
        Product product = prodService.findById(id);
        model.addAttribute("categories", catService.getAssignedProducts(product));
        model.addAttribute("newCategories", catService.getUnassignedProducts(product));
        model.addAttribute("product", product);
        return "products/show.jsp";
    }

    @PostMapping("/products/{id}")
    public String editProduct(@PathVariable("id") Long id, @RequestParam(value="catId") Long catId, Model model) {
        Product product = prodService.findById(id);
        Category category = catService.findById(catId);
        product.getCategories().add(category);
        prodService.updateProduct(product);
        model.addAttribute("assignedCategories", catService.getAssignedProducts(product));
        model.addAttribute("unassignedCategories", catService.getUnassignedProducts(product));
        return "redirect:/products/" + id;
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
            catService.addCategory(category);
            return "redirect:/";
        }
    }

    @GetMapping("/categories/{id}")
    public String showCategory(@PathVariable("id") Long id, Model model) {
        Category category = catService.findById(id);
        model.addAttribute("products", prodService.getAssignedCategories(category));
        model.addAttribute("newProducts", prodService.getUnassignedCategories(category));
        model.addAttribute("category", catService.findById(id));
        return "categories/show.jsp";
    }

    @PostMapping("/categories/{id}")
    public String editCategory(@PathVariable("id") Long id, @RequestParam(value="productId") Long productId, Model model) {
        Category category = catService.findById(id);
        Product product = prodService.findById(productId);
        category.getProducts().add(product);
        catService.updateCategory(category);
        model.addAttribute("products", prodService.getAssignedCategories(category));
        model.addAttribute("newProducts", prodService.getUnassignedCategories(category));
        return "redirect:/categories/" + id;
    }
}
