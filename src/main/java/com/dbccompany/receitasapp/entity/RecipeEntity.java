package com.dbccompany.receitasapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RecipeEntity {
    //TODO: Implementar atributos da Api externa
    //TODO: Fazer banco de dados no DBEAVER e pelo diagrama fazer o mapeamento
    @Id
    private Long idRecipe;
}
