package com.dbccompany.receitasapp.repository;

import com.dbccompany.receitasapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // receitas que o usuario cadastrou


    //    @Query("select")


    // notas que o usuário deu




    // comentarios que ele fez




    // filtrar pelo nome
    @Query("select u "+
            "   from user_recipe u "+
            "   where u.userName = :userName")//JPQL
    List<UserEntity> findByNameContainingIgnoreCase(String userName);


    //fazer um sort por nome

}
