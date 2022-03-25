package com.dbccompany.receitasapp.repository;

import com.dbccompany.receitasapp.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity,Long> {
    //TODO: Queries para encontrar notas das receitas
}
