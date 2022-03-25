package com.dbccompany.receitasapp.repository;

import com.dbccompany.receitasapp.entity.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    //TODO: Implementar lógica do trabalho inicial das queries;
    //TODO: Implementar uma API para paginação
    //TODO: Fazer um html para interação (Se der tempo) para exemplificar a paginação

//    ordenada por preço

//    @Query("select r "+
//        "   from recipe r "+
//        "   where r.price = :price")
//    Page<RecipeEntity> ordenarPorPreço (BigDecimal price, Pageable pageable);

//    ordenada por tempo de preparo
}
