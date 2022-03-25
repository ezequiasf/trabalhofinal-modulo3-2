package com.dbccompany.receitasapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "coment")
public class ComentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMENT_GEN")
    @SequenceGenerator(name = "COMENT_GEN", sequenceName = "seq_coment")
    @Column(name = "coment_id")
    private Long idComent;

    @JsonIgnore
    @ManyToOne
    private UserEntity userEntity;

    @JsonIgnore
    @ManyToOne
    private RecipeEntity recipe;

    @Column(name = "coment_text")
    private String coment;
}
