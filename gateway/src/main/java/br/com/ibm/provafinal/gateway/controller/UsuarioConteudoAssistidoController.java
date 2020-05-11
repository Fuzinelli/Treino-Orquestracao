package br.com.ibm.provafinal.gateway.controller;

import br.com.ibm.provafinal.gateway.model.UsuarioConteudoAssistidoEntrada;
import br.com.ibm.provafinal.gateway.model.UsuarioConteudoAssistidoSaida;
import br.com.ibm.provafinal.gateway.service.UsuarioConteudoAssistidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "gateway/usuario/conteudoAssistido", produces = "application/json")
@Configuration
@CrossOrigin
public class UsuarioConteudoAssistidoController {
    @Autowired
    UsuarioConteudoAssistidoService usuarioConteudoAssistidoService;

    @GetMapping("/{id}")
    public UsuarioConteudoAssistidoSaida listarUsuariosEConteudosAssistidos(@PathVariable Long id) throws Exception {
        UsuarioConteudoAssistidoSaida saida  = usuarioConteudoAssistidoService.listarUsuariosEConteudosAssistidos(id);
        return saida;
    }

    @PostMapping
    public UsuarioConteudoAssistidoSaida salvar(@RequestBody UsuarioConteudoAssistidoEntrada entrada) throws Exception {
        return usuarioConteudoAssistidoService. salvar(entrada);
    }
}
