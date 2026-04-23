package dev.sivalabs.bookstore.web;

import dev.sivalabs.bookstore.domain.exception.OrderNotFoundException;
import dev.sivalabs.bookstore.domain.model.CreateOrderCmd;
import dev.sivalabs.bookstore.domain.model.CreateOrderResult;
import dev.sivalabs.bookstore.domain.model.OrderDto;
import dev.sivalabs.bookstore.domain.model.PagedResult;
import dev.sivalabs.bookstore.domain.service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateOrderResult createOrder(@Valid @RequestBody CreateOrderCmd cmd) {
        log.info("Creating order for customer: {}", cmd.customer().email());
        var savedOrder = orderService.createOrder(cmd);
        return new CreateOrderResult(savedOrder.getOrderNumber());
    }

    @GetMapping(value = "/{orderNumber}")
    OrderDto getOrder(@PathVariable UUID orderNumber) {
        log.info("Fetching order by orderNumber: {}", orderNumber);
        return orderService
                .findOrder(orderNumber)
                .orElseThrow(() -> OrderNotFoundException.forOrderNumber(orderNumber.toString()));
    }

    @GetMapping
    PagedResult<OrderDto> getOrders(@RequestParam(defaultValue = "1")int page) {
        log.info("Fetching orders for page: {}", page);
        return orderService.findOrders(page);
    }
}
