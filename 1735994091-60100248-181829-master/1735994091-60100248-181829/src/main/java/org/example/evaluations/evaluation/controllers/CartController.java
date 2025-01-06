package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.models.Cart;
import org.example.evaluations.evaluation.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable("cartId") Long cartId){

        Cart cart = this.cartService.getCartById(cartId);

        return ResponseEntity.ok(cart);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Cart>> getCartsByUser(@PathVariable("userId") Long userId){

        List<Cart> carts = this.cartService.getCartByUserId(userId);

        return ResponseEntity.ok(carts);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Cart> deleteCart(@PathVariable("cartId") Long cartId){

        Cart cart = this.cartService.deleteCartById(cartId);

        return ResponseEntity.ok(cart);
    }

    @PostMapping("")
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart){

        Cart newCart = this.cartService.addCart(cart);

        return ResponseEntity.ok(newCart);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart, @PathVariable("cartId") Long cartId){

        Cart updatedCart = this.cartService.updateCart(cartId, cart);

        return ResponseEntity.ok(updatedCart);
    }

}
