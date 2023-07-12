package com.projeto.yonekuraveiculos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa extends BaseEntity {

    //    @NotBlank: usado para verificar se uma String não é nula e não está em branco.
    @NotBlank
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @Column(name = "email", length = 100)
    private String email;
    @NotBlank
    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = false, length = 20)
    private TipoDocumento tipoDocumento;
    @NotBlank
    @Column(name = "documento", nullable = false, unique = true, length = 20)
/*    @CPF e @CNPJ: provenientes da biblioteca Hibernate Validator,
    verifica se o número informado é válido de acordo com os dígitos de verificação.*/
    @CPF(groups = {CPF.class})
    @CNPJ(groups = {CNPJ.class})
    private String documento;

    // Criado estes dois List(comprador e vendedor) para verificar se a pessoa está associada à alguma transação ativa.
    @OneToMany(mappedBy = "comprador")
    private List<Transacao> transacoesComprador;

    @OneToMany(mappedBy = "vendedor")
    private List<Transacao> transacoesVendedor;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "cep", column = @Column(nullable = true, length = 10)),
            @AttributeOverride(name = "estado", column = @Column(nullable = true, length = 50)),
            @AttributeOverride(name = "cidade", column = @Column(nullable = true, length = 50)),
            @AttributeOverride(name = "bairro", column = @Column(nullable = true, length = 50)),
            @AttributeOverride(name = "logradouro", column = @Column(nullable = true, length = 100)),
            @AttributeOverride(name = "numero", column = @Column(nullable = true)),
            @AttributeOverride(name = "complemento", column = @Column(nullable = true, length = 100))
    })
    private Endereco endereco;

}
