package com.projeto.yonekuraveiculos.controller;

import com.projeto.yonekuraveiculos.entity.Pessoa;
import com.projeto.yonekuraveiculos.entity.Transacao;
import com.projeto.yonekuraveiculos.entity.Veiculo;
import com.projeto.yonekuraveiculos.service.PessoaService;
import com.projeto.yonekuraveiculos.service.TransacaoService;
import com.projeto.yonekuraveiculos.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransacaoController {

    private TransacaoService transacaoService;
    private VeiculoService veiculoService;
    private PessoaService pessoaService;

    @Autowired
    public TransacaoController(TransacaoService transacaoService, VeiculoService veiculoService, PessoaService pessoaService) {
        this.transacaoService = transacaoService;
        this.veiculoService = veiculoService;
        this.pessoaService = pessoaService;
    }

    @GetMapping("/cadastrarTransacao")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String exibirFormulario(Model model) {
        String keyword = null;
        List<Veiculo> veiculos = veiculoService.listarVeiculos(keyword);
        keyword = null;
        List<Pessoa> pessoas = pessoaService.listarPessoas(keyword);

        model.addAttribute("transacao", new Transacao());
        model.addAttribute("veiculos", veiculos);
        model.addAttribute("pessoas", pessoas);

        return "cadastrarTransacao";
    }

    @PostMapping("/cadastrarTransacao")
    public String addTransacao(@ModelAttribute Transacao transacao, Model model) {
        // Verifica se o comprador foi selecionado ou deixado vazio
        if (transacao.getComprador() != null && transacao.getComprador().getId() == null) {
            transacao.setComprador(null);
        }

        // Verifica se o vendedor foi selecionado ou deixado vazio
        if (transacao.getVendedor() != null && transacao.getVendedor().getId() == null) {
            transacao.setVendedor(null);
        }

        transacaoService.cadastrarTransacao(transacao);
        return "redirect:/listarTransacao";
    }

    @GetMapping("/listarTransacao")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String listarTransacao(Model model, @Param("keyword") String keyword) {
        List<Transacao> lista = transacaoService.listarTransacoes(keyword);
        model.addAttribute("listaTransacoes", lista);
        return "listarTransacoes";
    }

    @GetMapping("/alterarTransacao/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String exibirFormularioAlterar(@PathVariable Long id, Model model) {
        Transacao transacao = transacaoService.obterTransacao(id);
        String keyword = null;
        List<Veiculo> veiculos = veiculoService.listarVeiculos(keyword);
        keyword = null;
        List<Pessoa> pessoas = pessoaService.listarPessoas(keyword);

        model.addAttribute("transacao", transacao);
        model.addAttribute("veiculos", veiculos);
        model.addAttribute("pessoas", pessoas);

        return "alterarTransacao";
    }

    @PostMapping("/alterarTransacao")
    public String alteraTransacao(@ModelAttribute("transacao") Transacao transacao, @RequestParam("id") Long id) {
        transacao.setId(id); // Definir o ID da transação
        transacaoService.alterarTransacao(transacao);
        return "redirect:/listarTransacao";
    }

    @GetMapping("/excluirTransacao/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String excluirTransacao(@PathVariable Long id) {
        transacaoService.excluirTransacao(id);
        return "redirect:/listarTransacao";
    }
}