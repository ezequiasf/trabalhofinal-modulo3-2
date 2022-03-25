package com.dbccompany.receitasapp.service;

import com.dbccompany.receitasapp.dataTransfer.ComentCreate;
import com.dbccompany.receitasapp.dataTransfer.ComentFormed;
import com.dbccompany.receitasapp.entity.ComentEntity;
import com.dbccompany.receitasapp.entity.UserEntity;
import com.dbccompany.receitasapp.exceptions.ObjectNotFoundException;
import com.dbccompany.receitasapp.repository.ComentRepository;
import com.dbccompany.receitasapp.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ComentService {
    private final ComentRepository comentRepository;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    public List<ComentFormed> readAllComents() {
        log.info("Chamada de método service:: Ler todas as notas.");
        return convertList(comentRepository.findAll());
    }

    public ComentFormed findById(Long idComent) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Encontrar por id.");
        ComentEntity c = comentRepository.findById(idComent).orElseThrow(() ->
                new ObjectNotFoundException("Coment not found!"));
        log.info("Feita verificação do ID.");
        return objectMapper.convertValue(c, ComentFormed.class);
    }

    public ComentFormed saveComent(ComentCreate comentCreate, Long idUser) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Salvar comentarios.");
        ComentEntity c = objectMapper.convertValue(comentCreate, ComentEntity.class);
        log.info("Objeto DTO convertido para tipo Comentario.");
        //TODO: Setar o usuário dentro de comentário
        UserEntity user = userRepository.findById(idUser).orElseThrow(()-> new ObjectNotFoundException("User not found!"));
        ComentEntity c2 = comentRepository.save(c);
        log.info("Comentario salvo no repositório.");
        return objectMapper.convertValue(c2, ComentFormed.class);
    }

    public ComentFormed updateComent(ComentCreate comentCreate, Long idComent) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Atualizar comentários.");
        ComentEntity comentReceived = objectMapper.convertValue(comentCreate, ComentEntity.class);
        log.info("Objeto DTO convertido para tipo Comentario.");
        ComentEntity oldComent = comentRepository.findById(idComent)
                .orElseThrow(() -> new ObjectNotFoundException("Coment not found!"));
        //TODO: fazer os sets de todas as propriedades no oldComent ex:: oldComent.setComent(comentReceived.get())
        ComentEntity newComent = comentRepository.save(oldComent);
        log.info("Comentário atualizado no repositório.");
        return objectMapper.convertValue(newComent, ComentFormed.class);
    }

    public void deleteComent(Long idComent) {
        log.info("Chamada de método service:: Deletar comentários.");
        comentRepository.deleteById(idComent);
    }

    private List<ComentFormed> convertList(List<ComentEntity> coments) {
        log.info("Iniciando conversão de lista...");
        return coments
                .stream()
                .map(coment -> objectMapper.convertValue(coment, ComentFormed.class))
                .collect(Collectors.toList());
    }
}
