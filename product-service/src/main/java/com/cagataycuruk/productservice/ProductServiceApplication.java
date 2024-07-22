package com.cagataycuruk.productservice;

import com.cagataycuruk.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class ProductServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
    /*
    private final OrderService orderService;

    private final ProductService productService;
    private final InventoryService inventoryService;
     */


    @Override
    public void run(String... args) {
        /*
        for (int i = 0; i < 10; i++) {
            Product product = Product.builder()
                    .name("Product")
                    .price(BigDecimal.valueOf(Math.random() * 1000).setScale(2, RoundingMode.FLOOR))
                    .build();
            productService.createProduct(product);

            Inventory inventory = Inventory.builder()
                    .product(product)
                    .quantity((int) (Math.random() * 100))
                    .build();

            inventoryService.createInventory(inventory);

            List<Product> products = new ArrayList<>();
            products.add(product);

            Order order = Order.builder()
                    .products(products)
                    .totalPrice(product.getPrice())
                    .status(OrderStatus.APPROVAL_PENDING)
                    .build();

            orderService.createOrder(order);
        }
         */
    }
}
