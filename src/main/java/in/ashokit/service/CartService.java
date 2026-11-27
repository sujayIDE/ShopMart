package in.ashokit.service;

import in.ashokit.entity.Cart;
import in.ashokit.entity.Product;
import in.ashokit.entity.User;
import in.ashokit.repository.CartRepository;
import in.ashokit.repository.ProductRepository;
import in.ashokit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart addToCart(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = new Cart();

        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(1);

        return cartRepository.save(cart);
    }

    public List<Cart> getCartByUserId(Long userId)
    {
        return cartRepository.findByUser_UserId(userId);
    }

    public Cart updateCartQuantity(Integer cartId,Integer quantity)
    {
        Optional<Cart> byId = cartRepository.findById(cartId);
        if(byId.isPresent())
        {
            Cart cart = byId.get();
            cart.setQuantity(quantity);
            return cartRepository.save(cart);
        }
        throw new RuntimeException("Cart not found");
    }

    public Cart removeFromCart(Integer cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cartRepository.delete(cart);

        return cart;
    }

    public Integer calculateCartTotal(Long userId)
    {
        List<Cart> carts = cartRepository.findByUser_UserId(userId);

        Integer total=0;

        for(Cart cart:carts)
        {
            total+= (int) (cart.getProduct().getProductPrice()*cart.getQuantity());
        }

        return total;
    }
}
