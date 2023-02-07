package com.generation.blogpessoal.controller;


import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {


    @Autowired
    private PostagemRepository postagemRepository;
    @Autowired
    private TemaRepository temaRepository;



    //metodo para acessar todas as postagens
    @GetMapping
    public ResponseEntity<List<Postagem>> getall(){
        return ResponseEntity.ok(postagemRepository.findAll());
    }

    //metodo para fazer postagem
    @PostMapping
    public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){
        if(temaRepository.existsById(postagem.getTema().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(postagemRepository.save(postagem));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //metodo para acessar por id
    @GetMapping("/{id}")
    public ResponseEntity<Postagem> getById(@PathVariable Long id){
        return postagemRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    //metodo para acessar por titulo
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
        return  ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
    }


    //metodo paara atualizar as postagens
    @PutMapping
    public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
        if (postagemRepository.existsById(postagem.getId())){

            if (temaRepository.existsById(postagem.getTema().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(postagemRepository.save(postagem));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    //metodo para deletar postagens
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
       Optional<Postagem> postagem = postagemRepository.findById(id);

       if(postagem.isEmpty())
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);

       postagemRepository.deleteById(id);
   }







}
