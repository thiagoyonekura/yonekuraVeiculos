package com.projeto.yonekuraveiculos.controller;

import com.projeto.yonekuraveiculos.entity.Pessoa;
import com.projeto.yonekuraveiculos.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PessoaController {

    private PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/cadastrarPessoa")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String cadastrarPessoa(Model model) {
        model.addAttribute("pessoa", new Pessoa());
        return "cadastrarPessoa";
    }

    @PostMapping("/cadastrarPessoa")
    public String addPessoa(@ModelAttribute Pessoa pessoa, Model model) {
        // Verificar se o CPF ou CNPJ já existe
        boolean cpfCnpjExists = pessoaService.isCpfCnpjExists(pessoa.getDocumento());

        if (cpfCnpjExists) {
            // Adicionar mensagem de erro ao modelo
            model.addAttribute("error", "CPF ou CNPJ já existe.");
            return "cadastrarPessoa";
        }

        pessoaService.cadastrarPessoa(pessoa);
        return "redirect:/listarPessoa";
    }

    @GetMapping("/listarPessoa")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String listarPessoa(Model model, @Param("keyword") String keyword) {
        List<Pessoa> lista = pessoaService.listarPessoas(keyword);
        model.addAttribute("listaPessoas", lista);
        model.addAttribute("keyword", keyword);
        return "listarPessoas";
    }

    @GetMapping("/alterarPessoa/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String alterarPessoa(@PathVariable Long id, Model model) {
        Pessoa pessoa = pessoaService.obterPessoa(id);
        model.addAttribute("pessoa", pessoa);
        return "alterarPessoa";
    }

    @PostMapping("/alterarPessoa")
    public String alteraPessoa(@ModelAttribute("pessoa") Pessoa pessoa, @RequestParam("id") Long id) {
        pessoa.setId(id); // Definir o ID da pessoa
        pessoaService.alterarPessoa(pessoa);
        return "redirect:/listarPessoa";
    }

    @GetMapping("/excluirPessoa/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String excluirPessoa(@PathVariable Long id) {
        pessoaService.excluirPessoa(id);
        return "redirect:/listarPessoa";
    }
}