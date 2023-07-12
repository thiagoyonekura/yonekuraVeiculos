package com.projeto.yonekuraveiculos.repository;

import com.projeto.yonekuraveiculos.entity.Pessoa;
import com.projeto.yonekuraveiculos.entity.Transacao;
import com.projeto.yonekuraveiculos.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repositório responsável por realizar operações de acesso a dados relacionadas à entidade Transacao.
@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    //Barra de pesquisa
    @Query("SELECT t FROM Transacao t JOIN t.veiculo ve WHERE ve.renavam LIKE %:keyword%")
    List<Transacao> findAll(@Param("keyword") String keyword);

    //Verifica a existência de transações relacionadas a uma determinada pessoa.
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END " +
            "FROM Transacao t WHERE t.comprador = :pessoa OR t.vendedor = :pessoa")
    boolean existsByCompradorOrVendedor(@Param("pessoa") Pessoa pessoa);

    //Verifica a existência de transações relacionadas a uma determinado veículo
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END " +
            "FROM Transacao t WHERE t.veiculo = :veiculo")
    boolean existsByVeiculo(@Param("veiculo") Veiculo veiculo);
}

