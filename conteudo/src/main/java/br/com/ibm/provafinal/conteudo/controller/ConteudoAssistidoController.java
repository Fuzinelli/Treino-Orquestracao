package br.com.ibm.provafinal.conteudo.controller;

import br.com.ibm.provafinal.conteudo.facade.ConteudoAssistidoFacade;
import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoEntrada;
import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "conteudoAssistido", produces = "application/json")
@Configuration
@CrossOrigin
public class ConteudoAssistidoController {
    @Autowired
    ConteudoAssistidoFacade conteudoAssistidoFacade;

    @PostMapping
    public ConteudoAssistidoSaida salvar(@RequestBody ConteudoAssistidoEntrada entrada) throws Exception {
        ConteudoAssistidoSaida saida = conteudoAssistidoFacade.salvar(entrada);
        return saida;
    }

    @GetMapping("/{id}")
    public List<ConteudoAssistidoSaida> listarPorUsuarioId(@PathVariable Long id){
        List<ConteudoAssistidoSaida> saida = conteudoAssistidoFacade.listarPorUsuarioId(id);
        return saida;
    }
}
