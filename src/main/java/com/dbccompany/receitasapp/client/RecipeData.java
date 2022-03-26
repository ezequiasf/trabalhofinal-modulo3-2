package com.dbccompany.receitasapp.client;

import feign.Headers;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.LinkedHashMap;
import java.util.Map;

@FeignClient(value = "ReceitasApp", url = "https://api.edamam.com/api/recipes/v2")
@Headers("Content-type: application/json")
public interface RecipeData {

    @RequestLine("GET /")
    LinkedHashMap<String, Object> findRecipes(@QueryMap Map<String, String> params);
}
