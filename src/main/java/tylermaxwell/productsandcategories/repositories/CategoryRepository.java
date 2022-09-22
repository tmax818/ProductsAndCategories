package tylermaxwell.productsandcategories.repositories;

import org.springframework.data.repository.CrudRepository;
import tylermaxwell.productsandcategories.models.Category;
import tylermaxwell.productsandcategories.models.Product;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAll();
    Category findByIdIs(Long id);
    List<Category> findAllByProducts(Product product);
    List<Category> findByProductsNotContains(Product product);
}
