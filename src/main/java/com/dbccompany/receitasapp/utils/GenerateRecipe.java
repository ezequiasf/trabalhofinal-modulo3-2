package com.dbccompany.receitasapp.utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenerateRecipe {

//    public List<RecipeCreate> gerarLista(Map<String, Object> jsonResponse) {
//
//        JSONObject jsonTotal = new JSONObject(jsonResponse);
//        List<ReceitaClienteDTO> receitas = new ArrayList<>();
//        JSONArray arrHits = jsonTotal.getJSONArray("hits");
//
//        for (int i = 0; i < arrHits.toList().size(); i++) {
//            JSONObject objTodo = arrHits.getJSONObject(i);
//            JSONObject recipeObj = objTodo.getJSONObject("recipe");
//            ReceitaClienteDTO dto = ReceitaClienteDTO.builder()
//                    .label(recipeObj.getString("label"))
//                    .image(recipeObj.getString("image"))
//                    .calories(BigDecimal.valueOf(recipeObj.getDouble("calories")))
//                    .mealType(convertJSONArray(recipeObj.getJSONArray("mealType")))
//                    .ingredientLines(convertJSONArray(recipeObj.getJSONArray("ingredientLines")))
//                    .dietLabels(convertJSONArray(recipeObj.getJSONArray("dietLabels")))
//                    .healthLabels(convertJSONArray(recipeObj.getJSONArray("healthLabels")))
//                    .build();
//            receitas.add(dto);
//        }
//        return receitas;
//    }
//
//    private List<String> convertJSONArray(JSONArray arr) {
//        return arr.toList().stream().map(obj -> (String) obj)
//                .collect(Collectors.toList());
//    }
}
