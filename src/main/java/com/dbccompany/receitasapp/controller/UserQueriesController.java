package com.dbccompany.receitasapp.controller;

import com.dbccompany.receitasapp.dataTransfer.UserFormed;
import com.dbccompany.receitasapp.dataTransferQuery.UserComentDTO;
import com.dbccompany.receitasapp.dataTransferQuery.UserRatingDTO;
import com.dbccompany.receitasapp.dataTransferQuery.UserRecipeDTO;
import com.dbccompany.receitasapp.service.UserQueriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/queryUser")
@RequiredArgsConstructor
public class UserQueriesController {
    private final UserQueriesService userQueriesService;

    @GetMapping("/findComentsOfUser")
    public List<UserComentDTO> findComentsOfUser (@RequestParam(required = false) Long userId){
        return userQueriesService.findComentsOfUser(userId);
    }

    @GetMapping("/findRatingsOfUser")
    public List<UserRatingDTO> findRatingsOfUser (@RequestParam(required = false) Long userId){
        return userQueriesService.findRatingsOfUser(userId);
    }

    @GetMapping("/findRecipesOfUser")
    public List<UserRecipeDTO> findRecipesOfUser (@RequestParam(required = false) Long userId){
        return userQueriesService.findRecipesOfUser(userId);
    }

    @GetMapping("/findUserByName")
    public List<UserFormed> findByName (@RequestParam String userName){
        return userQueriesService.findByNameContainingIgnoreCase(userName);
    }

    @GetMapping ("/findAllUsersOrderByName")
    public List<UserFormed> findAllUsersOrderByName (){
        return userQueriesService.listAllUsersOrderByName();
    }
}
