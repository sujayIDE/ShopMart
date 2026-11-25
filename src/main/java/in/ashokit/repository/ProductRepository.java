package in.ashokit.repository;

import in.ashokit.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    public List<Product> findByProductName(String productName);

    public List<Product> findByProductNameContainingIgnoreCase(String productName);

    List<Product> findByProductCategoryIgnoreCase(String productCategory);
}
