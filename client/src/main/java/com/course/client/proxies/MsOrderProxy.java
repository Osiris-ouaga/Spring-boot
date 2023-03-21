package com.course.client.proxies;


import com.course.client.beans.OrderBean;
import com.course.client.beans.OrderItemBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ms-order", url = "localhost:8093")
public interface MsOrderProxy {

    @PostMapping(value = "/order")
    public ResponseEntity<OrderBean> createNewOrder(@RequestBody OrderBean orderData);

    @GetMapping(value = "/order/{id}")
    public Optional<OrderBean> getOrder(@PathVariable Long id);

    @GetMapping(value = "/orders")
    public List<OrderBean> getOrderList();

    @PostMapping(value = "/order/{id}")
    public ResponseEntity<OrderItemBean> addProductToOrder(@PathVariable Long id, @RequestBody OrderItemBean orderItem);
}