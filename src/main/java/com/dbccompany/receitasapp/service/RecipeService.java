package com.dbccompany.receitasapp.service;

import com.dbccompany.receitasapp.dataTransfer.RecipeCreate;
import com.dbccompany.receitasapp.dataTransfer.RecipeFormed;
import com.dbccompany.receitasapp.entity.RecipeEntity;
import com.dbccompany.receitasapp.exceptions.ObjectNotFoundException;
import com.dbccompany.receitasapp.repository.RecipeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final ObjectMapper objectMapper;

    public List<RecipeFormed> readAllRecipes() {
        return convertList(recipeRepository.findAll());
    }

    public RecipeFormed findRecipeById(Long idRecipe) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Encontrar por id.");
        RecipeEntity r = recipeRepository.findById(idRecipe).orElseThrow(() -> new ObjectNotFoundException("Recipe not found!"));
        return objectMapper.convertValue(r, RecipeFormed.class);
    }

    //TODO: Implementar lógica de consulta depois
//    public List<RecipeFormed> findRecipesByUser(Long idUsuario)  {
//
//        return convertList(recipeRepository.findAll());
//    }

    public RecipeFormed saveRecipe(RecipeCreate recipeCreate, Long idUser) {
        log.info("Chamada de método service:: Salvar receitas.");
        RecipeEntity r = objectMapper.convertValue(recipeCreate, RecipeEntity.class);
        log.info("Objeto DTO convertido para tipo Receita.");
        //TODO: setar o usuário na receita
        RecipeEntity r2 = recipeRepository.save(r);
        log.info("Receita salva no repositório.");
        return objectMapper.convertValue(r2, RecipeFormed.class);
    }

    public RecipeFormed updateRecipe(RecipeCreate recipeCreate, Long idRecipe) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Atualizar receitas.");
        RecipeEntity newRecipe = objectMapper.convertValue(recipeCreate, RecipeEntity.class);
        log.info("Objeto DTO convertido para tipo Receita.");
        //TODO: Setar os atributos da nova receita na antiga
        RecipeEntity oldRecipe = recipeRepository.findById(idRecipe)
                .orElseThrow(() -> new ObjectNotFoundException("Recipe not found!"));
        log.info("Receita atualizada no repositório.");
        RecipeEntity recipeReturn = recipeRepository.save(oldRecipe);
        return objectMapper.convertValue(recipeReturn, RecipeFormed.class);
    }

    public void deleteRecipe(Long idRecipe) {
        log.info("Chamada de método service:: Deletar receitas.");
        recipeRepository.deleteById(idRecipe);
    }

    private List<RecipeFormed> convertList(List<RecipeEntity> recipes) {
        log.info("Iniciando conversão de lista...");
        return recipes
                .stream()
                .map(r -> objectMapper.convertValue(r, RecipeFormed.class))
                .collect(Collectors.toList());
    }
}
