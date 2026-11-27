package com.ecomm.product.domain.entity;

import com.ecomm.product.domain.enums.Categoria;
import com.ecomm.product.domain.exception.NegativePriceException;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {

    private UUID id;
    private String name;
    private String description;
    private String sku;
    private BigDecimal price;
    private Categoria categoria;
    private LocalDate inclusionDate;
    private boolean isActive;

    public static Product createProduct(String name, String description, String sku, BigDecimal price, Categoria categoria) {
        return Product.builder()
                .id(UUID.randomUUID())
                .name(name)
                .description(description)
                .sku(sku)
                .price(validatePrice(price))
                .categoria(categoria)
                .inclusionDate(LocalDate.now())
                .isActive(true)
                .build();
    }

    private static BigDecimal validatePrice(BigDecimal price) {
        final int SCALE = 2;
        final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

        price = price.setScale(SCALE, ROUNDING_MODE);

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativePriceException();
        }
        return price;
    }
}
