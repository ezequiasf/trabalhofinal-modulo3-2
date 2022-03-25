package com.dbccompany.receitasapp.dataTransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecipeCreate {

    @NotBlank(message = "O nome da receita deve ser informado.")
    @Size(min = 2, max = 40, message = "O nome da receita deve estar entre 2 e 15 caracteres.")
    private String recipeName;

    private String imageUrl;

    @NotBlank(message = "O modo de preparo deve ser informado.")
    @Size(min = 1, max = 2500, message = "O modo de preparo deve ter no máximo 2500 caracteres.")
    private String prepareRecipe;

    @NotNull(message = "O tempo de preparo deve ser informado.")
    private Integer prepareTime;

    @Digits(integer = 6, fraction = 2)
    @DecimalMin(value = "0.0", message = "Não é permitido números negativos.")
    private BigDecimal price;

    @Digits(integer = 6, fraction = 2)
    @DecimalMin(value = "0.0", message = "Não é permitido números negativos.")
    private BigDecimal calories;
}
