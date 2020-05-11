package br.com.ibm.provafinal.gateway.conteudoassistido.feign.client;

import br.com.ibm.provafinal.gateway.conteudoassistido.model.ConteudoAssistidoEntrada;
import br.com.ibm.provafinal.gateway.conteudoassistido.model.ConteudoAssistidoSaida;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name="conteudoAssistido", url="http://localhost:8084/conteudoAssistido")
public interface ConteudoAssistidoClient {
    @PostMapping
    public ConteudoAssistidoSaida salvar(ConteudoAssistidoEntrada entrada) throws Exception;

    @GetMapping("/{id}")
    public List<ConteudoAssistidoSaida> listarPorUsuarioId(@PathVariable Long id);
}
