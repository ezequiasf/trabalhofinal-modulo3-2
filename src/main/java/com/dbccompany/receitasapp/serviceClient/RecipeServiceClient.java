package com.dbccompany.receitasapp.serviceClient;

import com.dbccompany.receitasapp.client.RecipeData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class RecipeServiceClient {
    private final RecipeData data;

    public LinkedHashMap<String,Object> findRecipes(String q){
        HashMap<String,String> params = new HashMap<>();
        params.put("app_id", "f443ad83");
        params.put("app_key", "d9bc22c6290859ed462249ad4306d069");
        params.put("type", "public");
        params.put("q", q);
        return  data.findRecipes(params);
    }
}
