package com.dbccompany.receitasapp.service;

import com.dbccompany.receitasapp.dataTransfer.RatingCreate;
import com.dbccompany.receitasapp.dataTransfer.RatingFormed;
import com.dbccompany.receitasapp.entity.RatingEntity;
import com.dbccompany.receitasapp.exceptions.ObjectNotFoundException;
import com.dbccompany.receitasapp.repository.RatingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final ObjectMapper objectMapper;

    public List<RatingFormed> readAllRatings() {
        log.info("Chamada de método service:: Ler todas as notas.");
        return converterLista(ratingRepository.findAll());
    }

    public RatingFormed findRatingById(Long idRating) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Encontrar por id.");
        RatingEntity r = ratingRepository.findById(idRating).orElseThrow(() -> new ObjectNotFoundException("Rating not found"));
        return objectMapper.convertValue(r, RatingFormed.class);
    }

    public RatingFormed saveRating(RatingCreate ratingCreate, Long idUser) {
        log.info("Chamada de método service:: Salvar notas.");
        RatingEntity r = objectMapper.convertValue(ratingCreate, RatingEntity.class);
        log.info("Objeto DTO convertido para tipo Nota.");
        //TODO: Setar o usuário dentro da RatingEntity
        RatingEntity r2 = ratingRepository.save(r);
        log.info("Nota salva no repositório.");
        return objectMapper.convertValue(r2, RatingFormed.class);
    }

    public RatingFormed updateRating(RatingCreate ratingCreate, Long idRating) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Atualizar notas.");
        RatingEntity newRating = objectMapper.convertValue(ratingCreate, RatingEntity.class);
        log.info("Objeto DTO convertido para tipo Nota.");
        //TODO: Setar os atributos da nova na velha
        RatingEntity oldRating = ratingRepository.findById(idRating)
                .orElseThrow(() -> new ObjectNotFoundException("Rating not found"));
        log.info("Nota atualizada no repositório.");
        RatingEntity ratingReturn = ratingRepository.save(oldRating);
        return objectMapper.convertValue(ratingReturn, RatingFormed.class);
    }

    public void deleteRating(Long idRating) {
        log.info("Chamada de método service:: Deletar notas.");
        ratingRepository.deleteById(idRating);
        log.info("Nota deletada no repositório.");
    }

    private List<RatingFormed> converterLista(List<RatingEntity> ratingEntities) {
        log.info("Iniciando conversão de lista...");
        return ratingEntities
                .stream()
                .map(rating -> objectMapper.convertValue(rating, RatingFormed.class))
                .collect(Collectors.toList());
    }
}
