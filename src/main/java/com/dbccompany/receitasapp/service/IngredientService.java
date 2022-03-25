package com.dbccompany.receitasapp.service;

import com.dbccompany.receitasapp.dataTransfer.IngredientCreate;
import com.dbccompany.receitasapp.dataTransfer.IngredientFormed;
import com.dbccompany.receitasapp.entity.IngredientEntity;
import com.dbccompany.receitasapp.exceptions.ObjectNotFoundException;
import com.dbccompany.receitasapp.repository.IngredientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final ObjectMapper objectMapper;

    public List<IngredientFormed> readAllIngredients() {
        log.info("Chamada de método service:: Ler todas os ingredientes.");
        return convertList(ingredientRepository.findAll());
    }

    public IngredientFormed findIngredientById(Long idIngredient) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Encontrar por id.");
        IngredientEntity i = ingredientRepository.findById(idIngredient)
                .orElseThrow(()->new ObjectNotFoundException("Ingredient not found!"));
        log.info("Feita verificação do ID.");
        return objectMapper.convertValue(i, IngredientFormed.class);
    }

    public IngredientFormed saveIngredient(IngredientCreate ingredientCreate, Long idRecipe) {
        log.info("Chamada de método service:: Salvar ingredientes.");
        IngredientEntity i = objectMapper.convertValue(ingredientCreate, IngredientEntity.class);
        log.info("Objeto DTO convertido para tipo Ingrediente.");
        //TODO: Salvar a receita no ingrediente (Recuperar a receita através do Id)
        IngredientEntity i2 = ingredientRepository.save(i);
        log.info("Ingrediente salvo no repositório.");
        return objectMapper.convertValue(i2, IngredientFormed.class);
    }

    public IngredientFormed updateIngredient(IngredientCreate ingredientCreate, Long idIngredient) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Atualizar ingredientes.");
        IngredientEntity newIngredient = objectMapper.convertValue(ingredientCreate, IngredientEntity.class);
        log.info("Objeto DTO convertido para tipo Ingrediente.");
        IngredientEntity oldIngredient = ingredientRepository.findById(idIngredient)
                .orElseThrow(()-> new ObjectNotFoundException("Ingredient Not found!"));
        //TODO: Setar os atributos do novo ingrediente no velho
        IngredientEntity ingredientReturn = ingredientRepository.save(oldIngredient);
        log.info("Ingrediente atualizado no repositório.");
        return objectMapper.convertValue(ingredientReturn, IngredientFormed.class);
    }

    public void deleteIngredient(Long idIngredient) {
        log.info("Chamada de método service:: Deletar ingredientes.");
        ingredientRepository.deleteById(idIngredient);
        log.info("Ingrediente deletado do repositório.");
    }


    private List<IngredientFormed> convertList(List<IngredientEntity> ingredients) {
        log.info("Iniciando conversão de lista...");
        return ingredients
                .stream()
                .map(ingredient -> objectMapper.convertValue(ingredient, IngredientFormed.class))
                .collect(Collectors.toList());
    }

}
