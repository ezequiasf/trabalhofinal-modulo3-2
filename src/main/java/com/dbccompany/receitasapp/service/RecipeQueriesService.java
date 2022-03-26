package com.dbccompany.receitasapp.service;

import com.dbccompany.receitasapp.dataTransfer.*;
import com.dbccompany.receitasapp.dataTransferQuery.*;
import com.dbccompany.receitasapp.entity.RecipeEntity;
import com.dbccompany.receitasapp.repository.RecipeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeQueriesService {
    private final RecipeRepository recipeRepository;
    private final ObjectMapper objectMapper;


    public List<RecipeFormed> findByMaxPrice(BigDecimal price) {
        if (price != null) {
            return recipeRepository.findByMaxPrice(price)
                    .stream()
                    .map(recipeEntity -> objectMapper.convertValue(recipeEntity, RecipeFormed.class))
                    .collect(Collectors.toList());
        }
        return recipeRepository.findAll().stream()
                .map(user ->  objectMapper.convertValue(user, RecipeFormed.class))
                .collect(Collectors.toList());
}


    public List<RecipeFormed> findByIngredient(String ingredient) {
        return recipeRepository.findAll()
                .stream()
                .filter(recipeEntity -> {
                    if(recipeEntity.getIngredientEntities().stream()
                            .anyMatch(ingredientEntity -> ingredientEntity.getIngredient().toUpperCase().contains(ingredient.toUpperCase()))){
                        return true;
                    }
                    return false;
                }).map(recipeEntity -> objectMapper.convertValue(recipeEntity, RecipeFormed.class))
                .collect(Collectors.toList());

    }


    public List<RecipeFormed>  findByPrepareTime(Integer prepareTime){
        if (prepareTime != null) {
            return recipeRepository.findByPrepareTime(prepareTime)
                    .stream()
                    .map(recipeEntity -> objectMapper.convertValue(recipeEntity, RecipeFormed.class))
                    .collect(Collectors.toList());
        }
        return recipeRepository.findAll().stream()
                .map(user ->  objectMapper.convertValue(user, RecipeFormed.class))
                .collect(Collectors.toList());
    }


    public List<RecipeFormed>findByMaxCalories (BigDecimal calories){
        if (calories != null) {
            return recipeRepository.findByMaxCalories(calories)
                    .stream()
                    .map(recipeEntity -> objectMapper.convertValue(recipeEntity, RecipeFormed.class))
                    .collect(Collectors.toList());
        }
        return recipeRepository.findAll().stream()
                .map(user ->  objectMapper.convertValue(user, RecipeFormed.class))
                .collect(Collectors.toList());
    }

    public List<RecipeFormed> findRecipeComents (RecipeEntity recipeEntity){
        return recipeEntity.getComentEntities().stream()
                .map(coment -> objectMapper.convertValue(coment, RecipeFormed.class))
                .collect(Collectors.toList());
    }


    public Page<RecipeFormed> orderForPrice (Integer paginaSolicitada, Integer tamanhoDaPagina){
        Pageable pageable = PageRequest.of(paginaSolicitada, tamanhoDaPagina, Sort.by("price").ascending());
        Page<RecipeEntity> all = recipeRepository.findAll(pageable);
        return all.map(recipeEntity-> objectMapper.convertValue(recipeEntity, RecipeFormed.class));

    }


    public Page<RecipeFormed> orderForTimePrepare(Integer paginaSolicitada, Integer tamanhoDaPagina){
        Pageable pageable = PageRequest.of(paginaSolicitada, tamanhoDaPagina, Sort.by("prepareTime").ascending());
        Page<RecipeEntity> all = recipeRepository.findAll(pageable);
        return all.map(recipeEntity -> objectMapper.convertValue(recipeEntity, RecipeFormed.class));
    }


    public List<RecipeComentDTO> findComentsOfRecipe (Long idRecipe){
        if (idRecipe != null) {
            return recipeRepository.findById(idRecipe).stream()
                    .map(recipe -> (RecipeComentDTO) buildDataTransfer(recipe, new RecipeComentDTO()))
                    .collect(Collectors.toList());
        }
        return recipeRepository.findAll().stream()
                .map(recipe -> (RecipeComentDTO) buildDataTransfer(recipe, new RecipeComentDTO()))
                .collect(Collectors.toList());
    }

    public List<RecipeRatingDTO> findRatingsOfRecipe (Long idRecipe){
        if (idRecipe != null) {
            return recipeRepository.findById(idRecipe).stream()
                    .map(user -> (RecipeRatingDTO) buildDataTransfer(user, new RecipeRatingDTO()))
                    .collect(Collectors.toList());
        }
        return recipeRepository.findAll().stream()
                .map(recipe -> (RecipeRatingDTO) buildDataTransfer(recipe, new RecipeRatingDTO()))
                .collect(Collectors.toList());
    }

    private RecipeCreate buildDataTransfer (RecipeEntity recipe, RecipeCreate dataTransfer) {
        if (dataTransfer instanceof RecipeComentDTO) {
            RecipeComentDTO recipewithcoment = objectMapper.convertValue(recipe, RecipeComentDTO.class);
            recipewithcoment.setComents(findComents(recipe));
            return recipewithcoment;
        }
        RecipeRatingDTO recipeRatingDTO = objectMapper.convertValue(recipe, RecipeRatingDTO.class);
        recipeRatingDTO.setRatings(findRatings(recipe));
        return recipeRatingDTO;
    }



    private List<ComentFormed> findComents (RecipeEntity recipeEntity){
        return recipeEntity.getComentEntities().stream()
                .map(coment -> objectMapper.convertValue(coment, ComentFormed.class))
                .collect(Collectors.toList());
    }

    private List<RatingFormed> findRatings (RecipeEntity recipeEntity){
        return recipeEntity.getRatingEntities().stream()
                .map(rating -> objectMapper.convertValue(rating, RatingFormed.class))
                .collect(Collectors.toList());
    }
}
