package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.clients.FakeStoreClient;
import org.example.evaluations.evaluation.dtos.FakeStoreCart;
import org.example.evaluations.evaluation.dtos.FakeStoreProduct;
import org.example.evaluations.evaluation.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class FakeStoreCartService implements ICartService {

    @Autowired
    private FakeStoreClient fakeStoreClient;

    public Cart getCartById(Long cartId) {

        FakeStoreCart fakeStoreCart = this.fakeStoreClient.getCartById(cartId);

        return this.from(fakeStoreCart);
    }

    public List<Cart> getCartByUserId(Long userId) {
        FakeStoreCart[] fakeStoreCarts = this.fakeStoreClient.getCartsByUserId(userId);

        List<Cart> carts = new ArrayList<>();
        for(FakeStoreCart fakeStoreCart: fakeStoreCarts){
            carts.add(this.from(fakeStoreCart));
        }

        return carts;
    }

    public Cart deleteCartById(Long cartId) {

        FakeStoreCart fakeStoreCart = this.fakeStoreClient.deleteCartById(cartId);

        return this.from(fakeStoreCart);
    }

    public Cart updateCart(Long cartId,Cart request) {

        FakeStoreCart fakeStoreCart = this.fakeStoreClient.updateCart(cartId, this.from(request));

        return this.from(fakeStoreCart);
    }

    public Cart addCart(Cart request) {

        FakeStoreCart fakeStoreCart = this.fakeStoreClient.addCart(this.from(request));

        return this.from(fakeStoreCart);

    }

    private FakeStoreCart from(Cart cart) {
        FakeStoreCart fakeStoreCart = new FakeStoreCart();
        fakeStoreCart.setId(cart.getId());
        Instant instant = cart.getDate().toInstant();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        fakeStoreCart.setDate(formatter.format(instant));
        fakeStoreCart.setUserId(cart.getUserId());
        if(cart.getProducts() != null) {
            List<FakeStoreProduct> fakeStoreProducts = new ArrayList<>();
            for (Map.Entry<Long, Double> product : cart.getProducts().entrySet()) {
                Long productId = product.getKey();
                Double quantity = product.getValue();
                FakeStoreProduct fakeStoreProduct = new FakeStoreProduct();
                fakeStoreProduct.setProductId(productId);
                fakeStoreProduct.setQuantity(quantity);
                fakeStoreProducts.add(fakeStoreProduct);
            }
            fakeStoreCart.setProducts(fakeStoreProducts);
        }
        return fakeStoreCart;
    }

    private Cart from(FakeStoreCart fakeStoreCart) {
       Cart cart = new Cart();
       cart.setId(fakeStoreCart.getId());
       cart.setUserId(fakeStoreCart.getUserId());
       Instant instant = Instant.parse(fakeStoreCart.getDate());
       cart.setDate(Date.from(instant));
       if(fakeStoreCart.getProducts() != null) {
           Map<Long,Double> products = new HashMap<>();
           for (FakeStoreProduct fakeStoreProduct : fakeStoreCart.getProducts()) {
             products.put(fakeStoreProduct.getProductId(),fakeStoreProduct.getQuantity());
           }
           cart.setProducts(products);
       }
       return cart;
    }
}
