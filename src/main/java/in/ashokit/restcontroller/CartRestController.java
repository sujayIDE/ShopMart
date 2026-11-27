package in.ashokit.restcontroller;

import in.ashokit.entity.Cart;
import in.ashokit.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cart")
public class CartRestController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addToCart/{userId}/{productId}")
    public ResponseEntity<Cart> addToCart(@PathVariable Long userId,@PathVariable Long productId)
    {
        Cart cart = cartService.addToCart(userId, productId);
        if(cart!=null)
        {
            return new ResponseEntity<>(cart, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCartByUserId/{userId}")
    public ResponseEntity<List<Cart>> getCartByUserId(@PathVariable Long userId)
    {
        List<Cart> cartByUserId = cartService.getCartByUserId(userId);
        if(!cartByUserId.isEmpty())
        {
            return new ResponseEntity<>(cartByUserId,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateCartQuantity/{cartId}/{quantity}")
    public ResponseEntity<Cart> updateCartQuantity(@PathVariable Integer cartId,@PathVariable Integer quantity)
    {
        Cart cart = cartService.updateCartQuantity(cartId, quantity);
        if(cart!=null)
        {
            return new ResponseEntity<>(cart,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removeFromCart/{cartId}")
    public ResponseEntity<Cart> removeFromCart(@PathVariable Integer cartId) {

        Cart cart = cartService.removeFromCart(cartId);

        return ResponseEntity.ok(cart);
    }

    @GetMapping("/calculateCartTotal/{userId}")
    public ResponseEntity<Integer> calculateCartTotal(@PathVariable Long userId) {

        Integer total = cartService.calculateCartTotal(userId);

        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}
