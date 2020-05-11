package br.com.ibm.provafinal.usuario.controller;

import br.com.ibm.provafinal.usuario.facade.UsuarioFacade;
import br.com.ibm.provafinal.usuario.model.UsuarioEntity;
import br.com.ibm.provafinal.usuario.model.UsuarioEntrada;
import br.com.ibm.provafinal.usuario.model.UsuarioSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "usuario", produces = "application/json")
@Configuration
@CrossOrigin
public class UsuarioController {
    @Autowired
    UsuarioFacade usuarioFacade;

    @PostMapping
    public UsuarioSaida salvarUsuario(@RequestBody UsuarioEntrada usuarioEntrada){
        UsuarioSaida usuarioSaida = usuarioFacade.salvar(usuarioEntrada);
        return usuarioSaida;
    }

    @GetMapping
    public List<UsuarioSaida> listarUsuarios(){
        List<UsuarioSaida> saida = usuarioFacade.listar();
        return saida;
    }

    @PutMapping("/alterarNome/{id}")
    public UsuarioSaida alterarNome(@PathVariable Long id, @RequestBody UsuarioEntrada usuarioEntrada) throws Exception{
        UsuarioSaida saida =usuarioFacade.alterarNome(id,usuarioEntrada);
        return saida;
    }
    @GetMapping("/{id}")
    public UsuarioSaida obterPorId(@PathVariable Long id) throws Exception {
        return usuarioFacade.buscarPorId(id);
    }
}
