package com.dbccompany.receitasapp.controllerClient;

import com.dbccompany.receitasapp.serviceClient.RecipeServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/clientRecipe")
@RequiredArgsConstructor
public class ReceitaControllerClient {

    private final RecipeServiceClient recipeServiceClient;

    @GetMapping
    public List<RecipeServiceClient> foundRecipes(@RequestParam String q) {
        LinkedHashMap<String, Object> linked = recipeServiceClient.findRecipes(q);
//        GerarReceita gerador = new GerarReceita();
        return null;
    }
}
