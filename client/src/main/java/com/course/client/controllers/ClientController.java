package com.course.client.controllers;

import com.course.client.beans.CartBean;
import com.course.client.beans.CartItemBean;
import com.course.client.beans.OrderBean;
import com.course.client.beans.ProductBean;
import com.course.client.proxies.MsCartProxy;
import com.course.client.proxies.MsOrderProxy;
import com.course.client.proxies.MsProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    private MsProductProxy msProductProxy;

    @Autowired
    private MsCartProxy msCartProxy;

    @Autowired
    private MsOrderProxy msOrderProxy;

    @RequestMapping("/")
    public String index(Model model) {

        List<ProductBean> products =  msProductProxy.list();

        model.addAttribute("products", products);

        return "index";
    }

    @RequestMapping("/product-detail/{id}")
    public String productDetail(Model model, @PathVariable Long id) {

        Optional<ProductBean> productInstance = msProductProxy.get(id);
        if (!productInstance.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get product");

        model.addAttribute("productInstance", productInstance.get());

        return "product-detail";
    }

    @RequestMapping("/cart/{idCart}/product/{idProduct}")
    public String cart(Model model, @PathVariable Long idCart, @PathVariable Long idProduct) {



        Optional<ProductBean> productInstance = msProductProxy.get(idProduct);
        if (!productInstance.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get product");

        // Create a CartItem
        CartItemBean cartItemBean = new CartItemBean(idProduct, 1, productInstance.get());

        // Add the cartItem to the cart
        System.out.println(">>>>>>> cartItemBean " + cartItemBean);
        msCartProxy.addProductToCart(1L, cartItemBean);


        Optional<CartBean> cartInstance = msCartProxy.getCart(idCart = 1L);
        if (!cartInstance.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get cart");

        List<CartItemBean> cartItem = cartInstance.get().getProducts();
        System.out.println(">>>>>> list item apres l'ajout  " + cartItem);

        // Creation de la liste a envoyer au template
        List<CartItemBean> cartProducts = cartItem ;

        System.out.println(">>>>>>> produits to html  " + cartProducts);
        model.addAttribute("products", cartProducts);
        model.addAttribute("totalPrice", 9999);
        return "cart";
    }

    @RequestMapping("/order/{idCart}")
    public String carts(Model model, @PathVariable Long idCart) {
        CartBean cart = msCartProxy.getCart(idCart).get();
        OrderBean orderBean = new OrderBean();
        //msOrderProxy.createNewOrder(orderBean);
        cart.clearCart();
        return "order";
    }


    @RequestMapping("/cart")
    public String orderr(Model model, @PathVariable Long idCart) {

        return "order";
    }

}
