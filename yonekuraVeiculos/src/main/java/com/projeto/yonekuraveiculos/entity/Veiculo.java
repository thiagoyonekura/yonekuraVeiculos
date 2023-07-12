package com.projeto.yonekuraveiculos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo extends BaseEntity {

    @Column(name = "renavam", nullable = false, length = 20)
    private String renavam;

    @Column(name = "placa", nullable = false, length = 10)
    private String placa;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @Column(name = "cor", nullable = false, length = 20)
    private String cor;

    @Column(name = "quilometragem", nullable = false)
    private Integer quilometragem;

    @Column(name = "fabricante", length = 50)
    private String fabricante;

    @Column(name = "modelo", length = 50)
    private String modelo;

    @Column(name = "categoria", length = 50)
    private String categoria;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "descricao", length = 100)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private StatusVeiculo status;

    // Criado este List para verificar se o veículo está associado à alguma transação ativa.
    @OneToMany(mappedBy = "veiculo")
    private List<Transacao> transacoesVeiculo;
}
