package dev.sivalabs.bookstore.web;

import dev.sivalabs.bookstore.TestcontainersConfiguration;
import dev.sivalabs.bookstore.domain.model.OrderDto;
import dev.sivalabs.bookstore.domain.model.PagedResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
@AutoConfigureRestTestClient
@ActiveProfiles("test")
@Sql("/test-orders-data.sql")
class OrderControllerTests {
    public static final UUID VALID_ORDER_NUMBER = UUID.fromString("16f69458-2f65-49ba-8779-bdaeafc7fa70");
    public static final UUID INVALID_ORDER_NUMBER = UUID.fromString("00000000-2f65-49ba-8779-bdaeafc7fa70");

    @Autowired
    private RestTestClient restTestClient;

    @Test
    void shouldCreateOrderSuccessfully() {
        restTestClient
            .post().uri("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .body("""
                {
                    "customer": {
                        "name": "Siva",
                        "email": "siva123@gmail.com",
                        "phone": "9876523456"
                   },
                    "deliveryAddress": "James, Bangalore, India",
                    "items": [{
                            "code": "P100",
                            "name": "The Hunger Games",
                            "price": 34.0,
                            "quantity": 1
                    }]
                }
                """)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void shouldReturnNotFoundWhenOrderIdNotExist() {
        restTestClient
                .get()
                .uri("/api/orders/{orderNumber}", INVALID_ORDER_NUMBER.toString())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void shouldFindExistingOrderSuccessfully() {
        OrderDto order = restTestClient
                .get()
                .uri("/api/orders/{orderNumber}", VALID_ORDER_NUMBER.toString())
                .exchange()
                .expectStatus().isOk()
                .returnResult(OrderDto.class)
                .getResponseBody();

        assertThat(order).isNotNull();
        assertThat(order.orderNumber()).isEqualTo(VALID_ORDER_NUMBER);
    }

    @Test
    void shouldGetAllOrdersSuccessfully() {
        PagedResult<OrderDto> ordersPage = restTestClient
                .get().uri("/api/orders")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<PagedResult<OrderDto>>() {})
                .returnResult().getResponseBody();

        assertThat(ordersPage).isNotNull();
        assertThat(ordersPage.data()).hasSize(5);
    }
}
