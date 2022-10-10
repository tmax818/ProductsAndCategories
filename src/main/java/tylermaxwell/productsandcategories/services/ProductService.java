package tylermaxwell.productsandcategories.services;

import org.springframework.stereotype.Service;
import tylermaxwell.productsandcategories.models.Product;
import tylermaxwell.productsandcategories.repositories.CategoryRepository;
import tylermaxwell.productsandcategories.repositories.ProductRepository;

import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAll() {
        return (List<Product>) productRepository.findAll();
    }
}
