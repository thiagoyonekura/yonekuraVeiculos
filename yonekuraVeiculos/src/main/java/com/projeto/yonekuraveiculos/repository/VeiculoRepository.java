package com.projeto.yonekuraveiculos.repository;

import com.projeto.yonekuraveiculos.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//repositório responsável por realizar operações de acesso a dados relacionadas à entidade Veiculo.
@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    @Query("SELECT v FROM Veiculo v WHERE " +
            "CONCAT(v.renavam, v.placa, v.ano, v.cor, v.quilometragem, v.fabricante, v.modelo, v.categoria, v.tipo, v.descricao, v.status) " +
            "LIKE %:keyword%")
    List<Veiculo> findAll(@Param("keyword") String keyword);
}
