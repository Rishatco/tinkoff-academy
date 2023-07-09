package ru.tinkoff.landscapeService.Order;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Page<Order> getPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer page,
                               @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer size) {
        return orderService.getPage(page, size);
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @PostMapping
    public Order create(@PathVariable OrderDTO orderDTO) {
        return orderService.create(orderDTO);
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        return orderService.update(id, orderDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteByID(@PathVariable Long id) {
        orderService.delete(id);
    }
}
