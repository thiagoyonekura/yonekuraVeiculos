package com.projeto.yonekuraveiculos.service;

import com.projeto.yonekuraveiculos.entity.Transacao;
import com.projeto.yonekuraveiculos.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    private TransacaoRepository transacaoRepository;

    @Autowired
    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void cadastrarTransacao(Transacao transacao) {
        transacaoRepository.save(transacao);
    }

    public List<Transacao> listarTransacoes(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return transacaoRepository.findAll(keyword);
        } else {
            return transacaoRepository.findAll();
        }
    }

    public Transacao obterTransacao(Long id) {
        return transacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transacao não encontrada com o ID: " + id));
    }

    public void alterarTransacao(Transacao transacao) {
        transacaoRepository.save(transacao);
    }

    public void excluirTransacao(Long id) {
        transacaoRepository.deleteById(id);
    }
}