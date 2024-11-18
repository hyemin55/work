package com.green.orderservice.order;

import com.green.orderservice.order.service.OrderService;
import com.green.orderservice.order.vo.OrderRequest;
import com.green.orderservice.order.vo.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("join")
    public ResponseEntity<OrderResponse> joinUser(@RequestBody OrderRequest orderRequest){

        OrderResponse orderResponse = orderService.join(orderRequest);

        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("login")
    public ResponseEntity<String> getUser(){

        return ResponseEntity.ok(null);
    }

    @GetMapping("kakaologin")
    public ResponseEntity<String> kakaoLogin(){

        return ResponseEntity.ok(null);
    }
}
