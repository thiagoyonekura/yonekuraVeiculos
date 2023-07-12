package com.projeto.yonekuraveiculos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "comprador_id", nullable = true)
    private Pessoa comprador;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = true)
    private Pessoa vendedor;

    //    BigDecimal: valores decimais precisos
    @Column(name = "preco_compra", nullable = true, precision = 10, scale = 2)
    private BigDecimal precoCompra;
    @Column(name = "preco_venda", nullable = true, precision = 10, scale = 2)
    private BigDecimal precoVenda;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_compra", nullable = true)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataCompra;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_venda", nullable = true)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataVenda;
}
