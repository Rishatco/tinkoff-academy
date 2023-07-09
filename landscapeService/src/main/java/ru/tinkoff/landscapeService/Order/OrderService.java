package ru.tinkoff.landscapeService.Order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.tinkoff.landscapeService.Field.Field;
import ru.tinkoff.landscapeService.Field.FieldService;
import ru.tinkoff.landscapeService.client.User;
import ru.tinkoff.landscapeService.client.UserService;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final FieldService fieldService;
    private final UserService userService;

    public Order getById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cannot find order with id = " + id));
    }

    public Page<Order> getPage(Integer offset, Integer limit){
        return orderRepository.findAll(PageRequest.of(offset, limit));
    }

    public void delete(Long id){
        orderRepository.deleteById(id);
    }

    public Order create(OrderDTO orderDTO){
        Order order = new Order();

        order.setStatus(order.getStatus());
        order.setWorkType(orderDTO.getWorkType());
        User user = userService.getById(orderDTO.getUserId());
        Field field = fieldService.getById(orderDTO.getFieldId());
        order.setUser(user);
        order.setField(field);
        return orderRepository.save(order);
    }

    public Order update(Long id, OrderDTO orderDTO){
        Order order = getById(id);
        order.setStatus(orderDTO.getWorkStatus());
        order.setWorkType(orderDTO.getWorkType());
        User user = userService.getById(orderDTO.getUserId());
        Field field = fieldService.getById(orderDTO.getFieldId());
        order.setUser(user);
        order.setField(field);
        return orderRepository.save(order);
    }
}
