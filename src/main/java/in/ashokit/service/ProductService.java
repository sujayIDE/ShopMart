package in.ashokit.service;

import in.ashokit.entity.Product;
import in.ashokit.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public Product addProduct(Product product)
    {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id,Product product)
    {
        Optional<Product> byId = productRepository.findById(id);
        if(byId.isPresent())
        {
            product.setProductId(id);
            return productRepository.save(product);
        }else{
            return null;
        }
    }

    public Product deleteProductById(Long id)
    {
        Optional<Product> byId = productRepository.findById(id);
        if(byId.isPresent())
        {
            productRepository.deleteById(id);
            return byId.get();
        }else{
            return null;
        }
    }

    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    public List<Product> getProductByName(String productName)
    {
       return productRepository.findByProductName(productName);
    }

    public List<Product> productSearchByName(String productName)
    {
        return productRepository.findByProductNameContainingIgnoreCase(productName);
    }

    public List<Product> productSearchByCategories(String category)
    {
        return productRepository.findByProductCategoryIgnoreCase(category);
    }
}
