package com.generation.blogpessoal.repository;

import com.generation.blogpessoal.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {

    //query metodo = select * tb_temas where descrisao like "%descrisao%;
    public List<Tema> findAllByDescrisaoContainingIgnoreCase(@Param("descrisao")String descrisao);
}
