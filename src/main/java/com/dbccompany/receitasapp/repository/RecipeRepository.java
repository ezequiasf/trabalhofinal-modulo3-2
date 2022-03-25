package com.dbccompany.receitasapp.repository;

import com.dbccompany.receitasapp.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    //TODO: Implementar lógica do trabalho inicial das queries;
    //TODO: Implementar uma API para paginação
    //TODO: Fazer um html para interação (Se der tempo) para exemplificar a paginação
}
