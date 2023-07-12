package com.projeto.yonekuraveiculos.controller;

import com.projeto.yonekuraveiculos.entity.Veiculo;
import com.projeto.yonekuraveiculos.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class VeiculoController {

    private VeiculoService veiculoService;

    @Autowired
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping("/cadastrarVeiculo")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String cadastrarVeiculo(Model model) {
        model.addAttribute("veiculo", new Veiculo());
        return "cadastrarVeiculo";
    }

    @PostMapping("/cadastrarVeiculo")
    public String addVeiculo(@ModelAttribute Veiculo veiculo, Model model) {
        veiculoService.cadastrarVeiculo(veiculo);
        return "redirect:/listarVeiculo";
    }

    @GetMapping("/listarVeiculo")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public String listarVeiculo(Model model, @Param("keyword") String keyword) {
        List<Veiculo> lista = veiculoService.listarVeiculos(keyword);
        model.addAttribute("listaVeiculos", lista);
        model.addAttribute("keyword", keyword);
        return "listarVeiculos";
    }

    @GetMapping("/alterarVeiculo/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String alterarVeiculo(@PathVariable Long id, Model model) {
        Veiculo veiculo = veiculoService.obterVeiculo(id);
        model.addAttribute("veiculo", veiculo);
        return "alterarVeiculo";
    }

    @PostMapping("/alterarVeiculo")
    public String alteraVeiculo(@ModelAttribute("veiculo") Veiculo veiculo, @RequestParam("id") Long id) {
        veiculo.setId(id); // Definir o ID do veículo
        veiculoService.alterarVeiculo(veiculo);
        return "redirect:/listarVeiculo";
    }

    @GetMapping("/excluirVeiculo/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String excluirVeiculo(@PathVariable Long id) {
        veiculoService.excluirVeiculo(id);
        return "redirect:/listarVeiculo";
    }
}