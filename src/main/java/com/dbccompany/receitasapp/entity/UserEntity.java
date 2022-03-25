package com.dbccompany.receitasapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "user_recipe")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_GEN")
    @SequenceGenerator(name = "USER_GEN", sequenceName = "seq_user", allocationSize = 1)
    @Column (name = "user_id")
    private Long idUser;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "pass")
    private String password;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @JoinColumn
    @OneToMany
    private Set<RatingEntity> ratings;

    @JsonIgnore
    @JoinColumn
    @OneToMany
    private Set<ComentEntity> coments;

    @JsonIgnore
    @JoinColumn
    @OneToMany
    private Set<RecipeEntity> recipes;
}
