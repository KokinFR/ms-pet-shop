package com.example.ms_pet_shop.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "É obrigatório colocar um descrição no produto")
    private String description;

    @NotNull(message = "O preço é obrigatório.")
    @DecimalMin(value = "0.01", message = "O valor deve ser superior a zero.")
    private BigDecimal price;

    @NotBlank(message = "O fabricante é obrigatório.")
    private String maker;
}
