package tylermaxwell.productsandcategories.repositories;

import org.springframework.data.repository.CrudRepository;
import tylermaxwell.productsandcategories.models.Category;
import tylermaxwell.productsandcategories.models.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
    Product findByIdIs(Long id);
    List<Product> findAllByCategories(Category category);
    List<Product> findByCategoriesNotContains(Category category);
}
