package tylermaxwell.productsandcategories.services;

import org.springframework.stereotype.Service;
import tylermaxwell.productsandcategories.models.Category;
import tylermaxwell.productsandcategories.repositories.CategoryRepository;
import tylermaxwell.productsandcategories.repositories.ProductRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> getAll() {
        return (List<Category>) categoryRepository.findAll();
    }
}