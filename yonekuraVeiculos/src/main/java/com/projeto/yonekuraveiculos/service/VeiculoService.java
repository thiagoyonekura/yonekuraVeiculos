package com.projeto.yonekuraveiculos.service;

import com.projeto.yonekuraveiculos.entity.Transacao;
import com.projeto.yonekuraveiculos.entity.Veiculo;
import com.projeto.yonekuraveiculos.repository.TransacaoRepository;
import com.projeto.yonekuraveiculos.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    private VeiculoRepository veiculoRepository;
    private TransacaoRepository transacaoRepository;

    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> listarVeiculos(String keyword) {
        if (keyword != null) {
            return veiculoRepository.findAll(keyword);
        }
        return veiculoRepository.findAll();
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        veiculoRepository.save(veiculo);
    }

   /* public void excluirVeiculo(Long id) {
        veiculoRepository.deleteById(id);
    }*/

    public Veiculo obterVeiculo(Long id) {
        return veiculoRepository.findById(id).orElse(null);
    }

    public void alterarVeiculo(Veiculo veiculo) {
        Veiculo veiculoExistente = veiculoRepository.findById(veiculo.getId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        veiculoExistente.setRenavam(veiculo.getRenavam());
        veiculoExistente.setPlaca(veiculo.getPlaca());
        veiculoExistente.setAno(veiculo.getAno());
        veiculoExistente.setCor(veiculo.getCor());
        veiculoExistente.setQuilometragem(veiculo.getQuilometragem());
        veiculoExistente.setFabricante(veiculo.getFabricante());
        veiculoExistente.setModelo(veiculo.getModelo());
        veiculoExistente.setCategoria(veiculo.getCategoria());
        veiculoExistente.setTipo(veiculo.getTipo());
        veiculoExistente.setDescricao(veiculo.getDescricao());
        veiculoExistente.setStatus(veiculo.getStatus());

        veiculoRepository.save(veiculoExistente);
    }

    public void excluirVeiculo(Long id) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado com o ID: " + id));

        // Verifica se o veículo está sendo usado em alguma transação
        List<Transacao> transacoesVeiculo = veiculo.getTransacoesVeiculo();
        if (transacoesVeiculo != null && !transacoesVeiculo.isEmpty()) {
            throw new IllegalArgumentException("Não é possível excluir o veículo devido à existência de uma ou mais transações ativas relacionadas a ele.");
        }

        veiculoRepository.delete(veiculo);
    }

}