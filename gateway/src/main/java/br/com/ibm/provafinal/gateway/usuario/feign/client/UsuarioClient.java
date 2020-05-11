package br.com.ibm.provafinal.gateway.usuario.feign.client;

import br.com.ibm.provafinal.gateway.usuario.model.UsuarioEntrada;
import br.com.ibm.provafinal.gateway.usuario.model.UsuarioSaida;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="usuario", url="http://localhost:8082/usuario")
public interface UsuarioClient {
    @PostMapping
    public UsuarioSaida salvar(UsuarioEntrada entrada);
    @GetMapping
    public List<UsuarioSaida> listar();
    @PutMapping("/alterarNome/{id}")
    public UsuarioSaida alterarNome(@PathVariable Long id, UsuarioEntrada usuarioBean) throws Exception;
    @GetMapping("/{id}")
    public UsuarioSaida obterPorId(@PathVariable Long id) throws Exception;
}
