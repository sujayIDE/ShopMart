package in.ashokit.restcontroller;

import in.ashokit.entity.Product;
import in.ashokit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product add = productService.addProduct(product);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts()
    {
        List<Product> allProducts = productService.getAllProducts();
        if(!allProducts.isEmpty()){
            return new ResponseEntity<>(allProducts,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(allProducts,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getProductByName/{productName}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String productName)
    {
        List<Product> productByName = productService.getProductByName(productName);
        if(!productByName.isEmpty())
        {
            return new ResponseEntity<>(productByName,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(productByName,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@RequestBody Product product)
    {
        Product updateProduct = productService.updateProduct(productId,product);
        if(updateProduct!=null)
        {
            return new ResponseEntity<>(product,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable Long id)
    {
        Product product = productService.deleteProductById(id);
        if(product!=null)
        {
            return new ResponseEntity<>(product,HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/searchProductByName/{productName}")
    public ResponseEntity<List<Product>> searchByName(@PathVariable String productName)
    {
        List<Product> products = productService.productSearchByName(productName);
        if(!products.isEmpty()){
            return new ResponseEntity<>(products,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/searchProductByCategory/{categoryName}")
    public ResponseEntity<List<Product>> searchProductByCategory(@PathVariable String categoryName)
    {
        List<Product> products = productService.productSearchByCategories(categoryName);
        if(!products.isEmpty())
        {
          return new ResponseEntity<>(products,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
