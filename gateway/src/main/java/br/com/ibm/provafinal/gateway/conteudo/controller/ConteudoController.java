package br.com.ibm.provafinal.gateway.conteudo.controller;

import br.com.ibm.provafinal.gateway.conteudo.model.ConteudoEntrada;
import br.com.ibm.provafinal.gateway.conteudo.model.ConteudoSaida;
import br.com.ibm.provafinal.gateway.conteudo.service.ConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "gateway/conteudo", produces = "application/json")
@Configuration
@CrossOrigin
public class ConteudoController {
    @Autowired
    ConteudoService conteudoService;

    @GetMapping
    public List<ConteudoSaida> listar(){
        return conteudoService.listarConteudos();
    }
    @PostMapping
    public ConteudoSaida salvar(@RequestBody ConteudoEntrada entrada){
        return conteudoService.salvarConteudo(entrada);
    }
    @PutMapping("/alterarTitulo/{id}")
    public ConteudoSaida alterarTitulo(@PathVariable Long id, @RequestBody ConteudoEntrada entrada) throws Exception {
        return conteudoService.alterarTitulo(id, entrada);
    }
    @GetMapping("/{id}")
    public ConteudoSaida obterPorId(@PathVariable Long id) throws Exception {
        return conteudoService.buscarPorId(id);
    }

}
