package com.projeto.yonekuraveiculos.service;

import com.projeto.yonekuraveiculos.entity.Pessoa;
import com.projeto.yonekuraveiculos.entity.Transacao;
import com.projeto.yonekuraveiculos.repository.PessoaRepository;
import com.projeto.yonekuraveiculos.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;
    private TransacaoRepository transacaoRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> listarPessoas(String keyword) {
        if (keyword != null) {
            return pessoaRepository.findAll(keyword);
        }
        return pessoaRepository.findAll();
    }

    public void cadastrarPessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    /*public void excluirPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }*/

    public Pessoa obterPessoa(Long id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    public void alterarPessoa(Pessoa pessoa) {
        pessoaRepository.findById(pessoa.getId()).ifPresent(pessoaExistente -> {
            pessoaExistente.setNome(pessoa.getNome());
            pessoaExistente.setEmail(pessoa.getEmail());
            pessoaExistente.setTelefone(pessoa.getTelefone());
            pessoaExistente.setTipoDocumento(pessoa.getTipoDocumento());
            pessoaExistente.setDocumento(pessoa.getDocumento());
            pessoaExistente.setEndereco(pessoa.getEndereco());

            pessoaRepository.save(pessoaExistente);
        });
    }

    public void excluirPessoa(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada com o ID: " + id));

        // Verifica se a pessoa está sendo usada como comprador em alguma transação
        List<Transacao> transacoesComprador = pessoa.getTransacoesComprador();
        if (transacoesComprador != null && !transacoesComprador.isEmpty()) {
            throw new IllegalArgumentException("Não é possível excluir a pessoa devido à existência de uma ou mais transações ativas associadas a ela.");
        }

        // Verifica se a pessoa está sendo usada como vendedor em alguma transação
        List<Transacao> transacoesVendedor = pessoa.getTransacoesVendedor();
        if (transacoesVendedor != null && !transacoesVendedor.isEmpty()) {
            throw new IllegalArgumentException("Não é possível excluir a pessoa devido à existência de uma ou mais transações ativas associadas a ela.");
        }

        pessoaRepository.delete(pessoa);
    }

    public boolean isCpfCnpjExists(String cpfCnpj) {
        // Verificar se o CPF ou CNPJ já existe no banco de dados
        return pessoaRepository.existsByDocumento(cpfCnpj);
    }
}