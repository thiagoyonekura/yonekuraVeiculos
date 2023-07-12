package com.projeto.yonekuraveiculos.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

/*Essa classe não representa diretamente uma tabela no banco de dados, mas será usada como base para outras classes
 que serão mapeadas para tabelas.*/
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "data_criacao")
    protected LocalDate dataCriacao;

    @Column(name = "data_update")
    protected LocalDate dataUpdate;

    /*Os métodos prePersist() e preUpdate() são úteis para registrar as datas de criação e atualização de cada
     entidade no banco de dados de forma automática*/
    @PrePersist
    public void prePersist() {
        dataCriacao = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataUpdate = LocalDate.now();
    }

    //Utilizado no método alterarPessoa PessoaService
    public Long getId() {
        return id;
    }

    //Utilizado no método alterarPessoa PessoaController
    public void setId(Long id) {
        this.id = id;
    }

}