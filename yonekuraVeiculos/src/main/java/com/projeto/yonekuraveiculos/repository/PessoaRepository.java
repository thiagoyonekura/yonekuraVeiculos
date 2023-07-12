package com.projeto.yonekuraveiculos.repository;

import com.projeto.yonekuraveiculos.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//repositório responsável por realizar operações de acesso a dados relacionadas à entidade Pessoa.
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query("SELECT p FROM Pessoa p WHERE " +
            "CONCAT(p.id, p.nome, p.email, p.telefone, p.endereco.logradouro, p.endereco.cep, p.endereco.estado, p.endereco.cidade, p.endereco.bairro, p.endereco.numero, p.endereco.complemento, p.documento, p.tipoDocumento) " +
            "LIKE %:keyword%")
    List<Pessoa> findAll(@Param("keyword") String keyword);

    boolean existsByDocumento(String cpfCnpj);
}