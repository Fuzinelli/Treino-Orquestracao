package br.com.ibm.provafinal.gateway.usuario.controller;

import br.com.ibm.provafinal.gateway.usuario.model.UsuarioEntrada;
import br.com.ibm.provafinal.gateway.usuario.model.UsuarioSaida;
import br.com.ibm.provafinal.gateway.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "gateway/usuario", produces = "application/json")
@Configuration
@CrossOrigin
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioSaida> listar(){
        return usuarioService.listar();
    }
    @PostMapping
    public UsuarioSaida salvar(@RequestBody UsuarioEntrada entrada){
        return usuarioService.salvar(entrada);
    }
    @PutMapping("/alterarNome/{id}")
    public UsuarioSaida alterarNome(@PathVariable Long id, @RequestBody UsuarioEntrada entrada) throws Exception {
        return usuarioService.alterarNome(id, entrada);
    }
    @GetMapping("/{id}")
    public UsuarioSaida obterPorId(@PathVariable Long id) throws Exception {
        return usuarioService.buscarPorId(id);
    }
}
