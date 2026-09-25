package dev.thedevcafe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
class TheDevCafeApplicationTests {

    @Autowired
    MockMvcTester mvc;

    @Test
    void listsProductsByCategory() {
        assertThat(mvc.get().uri("/api/products?category=BEANS"))
                .hasStatusOk()
                .bodyJson()
                .extractingPath("$.length()").isEqualTo(5);
    }

    @Test
    void unknownProductIsNotFound() {
        assertThat(mvc.get().uri("/api/products/9999")).hasStatus(404);
    }

    @Test
    void returnsOrderHistoryOfCustomer() {
        var result = assertThat(mvc.get().uri("/api/customers/42/orders")).hasStatusOk().bodyJson();
        result.extractingPath("$.customerId").isEqualTo(42);
        result.extractingPath("$.orderCount").isEqualTo(500);
        result.extractingPath("$.orders[0].items").asArray().isNotEmpty();
    }

    @Test
    void recordsSale() {
        assertThat(mvc.post().uri("/api/sales")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"productId": 17, "quantity": 2}
                        """))
                .hasStatus(201)
                .bodyJson()
                .extractingPath("$.total").isEqualTo(29.80);
    }

    @Test
    void rejectsInvalidSale() {
        assertThat(mvc.post().uri("/api/sales")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"productId": 17, "quantity": 0}
                        """))
                .hasStatus(400);
    }
}
