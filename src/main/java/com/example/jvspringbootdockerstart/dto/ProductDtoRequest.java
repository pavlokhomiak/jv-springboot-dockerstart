package com.example.jvspringbootdockerstart.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductDtoRequest {
    @NotBlank(message = "Tittle cannot be blank")
    private String title;
    @NotNull(message = "Price cannot be null")
    private BigDecimal price;
}
