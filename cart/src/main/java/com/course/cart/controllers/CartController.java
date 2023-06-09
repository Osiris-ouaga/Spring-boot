package com.course.cart.controllers;

import com.course.cart.domain.Cart;
import com.course.cart.domain.CartItem;
import com.course.cart.repositories.CartItemRepository;
import com.course.cart.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
public class CartController {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @PostMapping(value = "/cart")
    public ResponseEntity<Cart> createNewCart(@RequestBody Cart cartData)
    {
        Cart cart = cartRepository.save(new Cart());

        if (cart == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a new cart");

        return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
    }

    @GetMapping(value = "/cart/{id}")
    public Optional<Cart> getCart(@PathVariable Long id)
    {
        Optional<Cart> cart = cartRepository.findById(id);

        if (cart == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get cart");

        return cart;
    }

    @PostMapping(value = "/cart/{id}")
    @Transactional
    public ResponseEntity<CartItem> addProductToCart(@PathVariable Long id, @RequestBody CartItem cartItem)
    {
        AtomicBoolean exist = new AtomicBoolean(false);
        System.out.println(">>>>>> cartItem "+ cartItem);
        Cart cart = cartRepository.getOne(id);


        if (cart == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get cart");
        // tester si le produit exist sur dans cart si oui j'incremente la quantité
        System.out.println(">>>>>> les produits qui existe deja dans Cart");
        System.out.println(">>>>>> "+ cart.getProducts());

        cart.getProducts().forEach(items -> {
            if (items.getProductId() == cartItem.getProductId() ){
                System.out.println(">>>>>> entre ");
                items.setQuantity(items.getQuantity() + 1 );
                exist.set(true);
            }
        });
        System.out.println(">>>> cart after " + cartItem);

        if (!exist.get())
            cart.addProduct(cartItem);

        cartRepository.save(cart);

        return new ResponseEntity<CartItem>(cartItem, HttpStatus.CREATED);
    }


}
