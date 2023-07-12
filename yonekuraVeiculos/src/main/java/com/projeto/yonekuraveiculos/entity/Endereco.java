package com.projeto.yonekuraveiculos.entity;

import jakarta.persistence.*;
import lombok.Data;

/*As classes incorporadas não podem ter identificadores próprios, pois não são entidades independentes,
 mas sim parte de uma entidade principal.*/
@Embeddable
@Data
public class Endereco {

    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private Integer numero;
    private String complemento;

}
