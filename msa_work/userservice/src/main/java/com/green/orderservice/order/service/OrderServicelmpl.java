package com.green.orderservice.order.service;

import com.green.orderservice.order.jpa.OrderEntity;
import com.green.orderservice.order.jpa.OrderRepository;
import com.green.orderservice.order.vo.OrderRequest;
import com.green.orderservice.order.vo.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServicelmpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderResponse join(OrderRequest orderRequest){
        String reqEmail = orderRequest.getEmail();

        orderRepository.findByEmail(reqEmail)
                .ifPresent(user -> {
                    throw new IllegalArgumentException("이미 존재하는 이메일입니다. \n회원가입을 할 수 없습니다.\n%s", reqEmail);
                });


        ModelMapper mapper = new ModelMapper();
        OrderEntity orderEntity = mapper.map(orderRequest, OrderEntity.class);
        orderEntity.setUserId(UUID.randomUUID().toString());
        orderEntity = OrderRepository.save(orderEntity);
        OrderResponse orderResponse = mapper.map(orderEntity, OrderResponse.class);
        return orderResponse;
    }
}
