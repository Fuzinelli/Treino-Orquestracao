package br.com.ibm.provafinal.gateway.conteudoassistido.controller;

import br.com.ibm.provafinal.gateway.conteudoassistido.model.ConteudoAssistidoEntrada;
import br.com.ibm.provafinal.gateway.conteudoassistido.model.ConteudoAssistidoSaida;
import br.com.ibm.provafinal.gateway.conteudoassistido.service.ConteudoAssistidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "gateway/conteudoAssistido", produces = "application/json")
@Configuration
@CrossOrigin
public class ConteudoAssistidoController {
    @Autowired
    ConteudoAssistidoService conteudoAssistidoService;
    @PostMapping
    public ConteudoAssistidoSaida salvar(@RequestBody ConteudoAssistidoEntrada entrada) throws Exception {
        return conteudoAssistidoService.salvar(entrada);
    }
    @GetMapping("/{id}")
    public List<ConteudoAssistidoSaida> listarPorUsuarioId(@PathVariable Long id){
        return conteudoAssistidoService.listarPorUsuarioId(id);
    }
}
