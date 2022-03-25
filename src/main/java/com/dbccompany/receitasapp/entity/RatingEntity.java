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
@Entity(name = "rating")
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RATING_GEN")
    @SequenceGenerator(name = "RATING_GEN", sequenceName = "seq_rating", allocationSize = 1)
    @Column(name = "rating_id")
    private Long idRating;

    @JsonIgnore
    @ManyToOne
    private UserEntity userEntity;

    @JsonIgnore
    @ManyToOne
    private RecipeEntity recipeEntity;

    @Column(name = "rating")
    private BigDecimal rating;
}
