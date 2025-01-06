package org.example.evaluations.evaluation.clients;

import jakarta.annotation.Nullable;
import org.example.evaluations.evaluation.dtos.FakeStoreCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreCart getCartById(Long cartId) {

        String url = "https://fakestoreapi.com/carts/{cartId}";

        FakeStoreCart cart = requestForEntity(url, HttpMethod.GET, null, FakeStoreCart.class, cartId).getBody();

        return cart;
    }

    public FakeStoreCart[] getCartsByUserId(Long userId) {

        String url = "https://fakestoreapi.com/carts/user/{userId}";

        FakeStoreCart[] carts = requestForEntity(url, HttpMethod.GET, null, FakeStoreCart[].class, userId).getBody();

        return carts;
    }

    public FakeStoreCart deleteCartById(Long cartId) {

        String url = "https://fakestoreapi.com/carts/{cartId}";

        FakeStoreCart cart = requestForEntity(url, HttpMethod.DELETE, null, FakeStoreCart.class, cartId).getBody();

        return cart;
    }

    public FakeStoreCart updateCart(Long cartId,FakeStoreCart request) {

        String url = "https://fakestoreapi.com/carts/{cartId}";

        FakeStoreCart cart = requestForEntity(url, HttpMethod.PUT, request, FakeStoreCart.class, cartId).getBody();

        return cart;
    }

    public FakeStoreCart addCart(FakeStoreCart request) {
        String url = "https://fakestoreapi.com/carts";

        FakeStoreCart cart = requestForEntity(url, HttpMethod.POST, request, FakeStoreCart.class).getBody();

        return cart;
    }

    private <T> ResponseEntity<T> requestForEntity(
            String url, HttpMethod httpMethod,
            @Nullable Object request,
            Class<T> responseType,
            Object... uriVariables){

        RestTemplate restTemplate = this.restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);

        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}
