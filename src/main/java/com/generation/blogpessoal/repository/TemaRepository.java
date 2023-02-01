package com.generation.blogpessoal.repository;

import com.generation.blogpessoal.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TemaRepository extends JpaRepository<Tema, Long> {

    //query metodo = select * tb_temas where descrisao like "%descrisao%;
    public List<Tema> findAllByDescrisaoContainingIgnoreCase(@Param("descrisao")String descrisao);
}
