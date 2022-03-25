package com.dbccompany.receitasapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "ingredient")
public class IngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ING_GEN")
    @SequenceGenerator(name = "ING_GEN", sequenceName = "seq_ingredient", allocationSize = 1)
    @Column(name = "ingredient_id")
    private Long idIngredient;

    @JsonIgnore
    @ManyToOne
    private RecipeEntity recipeEntity;

    @Column(name = "ingredient_text")
    private String ingredient;
}
