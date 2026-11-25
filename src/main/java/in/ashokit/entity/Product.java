package in.ashokit.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQuantity;
    private String productCategory;
    private String productImageUrl;
}
