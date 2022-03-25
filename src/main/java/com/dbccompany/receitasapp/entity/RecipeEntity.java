package com.dbccompany.receitasapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "recipe")
public class RecipeEntity {
    //TODO: Implementar atributos da Api externa
    //TODO: Fazer banco de dados no DBEAVER e pelo diagrama fazer o mapeamento
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECIPE_GEN")
    @SequenceGenerator(name = "RECIPE_GEN", sequenceName = "seq_recipe", allocationSize = 1)
    @Column(name = "recipe_id")
    private Long idRecipe;

    @Column(name = "recipe_name")
    private String recipeName;

    @Column(name = "image")
    private String imageUrl;

    @Column(name = "prepare_recipe")
    private String prepareRecipe;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "prepare_time")
    private Integer prepareTime;

    @Column(name = "calories")
    private BigDecimal calories;

    @Column(name = "recipe_type")
    @Enumerated(EnumType.ORDINAL)
    private RecipeType recipeType;

    @Column(name = "meal_type")
    @Enumerated(EnumType.ORDINAL)
    private MealType mealType;

    @JsonIgnore
    @ManyToOne
    private UserEntity userEntity;
}
