package com.generation.blogpessoal.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_postagens")
public class Postagem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @NotBlank
    @Size(min = 4,max = 100, message = "o titulo tem que ter de 4 a 100 caracteres")
    private String titulo;


    @NotBlank
    @Size(min = 10,max = 1000, message = "o titulo tem que ter de 10 a 1000 caracteres")
    private String texto;

    @UpdateTimestamp
    private LocalDateTime data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}