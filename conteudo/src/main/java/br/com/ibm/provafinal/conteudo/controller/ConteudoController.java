package br.com.ibm.provafinal.conteudo.controller;

import br.com.ibm.provafinal.conteudo.facade.ConteudoFacade;
import br.com.ibm.provafinal.conteudo.model.ConteudoEntrada;
import br.com.ibm.provafinal.conteudo.model.ConteudoSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "conteudo", produces = "application/json")
@Configuration
@CrossOrigin
public class ConteudoController {
    @Autowired
    ConteudoFacade conteudoFacade;

    @PostMapping
    public ConteudoSaida salvar(@RequestBody ConteudoEntrada conteudoEntrada){
        ConteudoSaida conteudoSaida = conteudoFacade.salvar(conteudoEntrada);
        return conteudoSaida;
    }

    @GetMapping
    public List<ConteudoSaida> listar(){
        List<ConteudoSaida> saida = conteudoFacade.listar();
        return saida;
    }

    @PutMapping("/alterarTitulo/{id}")
    public ConteudoSaida alterarTitulo(@PathVariable Long id, @RequestBody ConteudoEntrada conteudoEntrada) throws Exception{
        ConteudoSaida saida = conteudoFacade.alterarTitulo(id,conteudoEntrada);
        return saida;
    }
    @GetMapping("/{id}")
    public ConteudoSaida obterPorId(@PathVariable Long id) throws Exception {
        return conteudoFacade.buscarPorId(id);
    }
}
