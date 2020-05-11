package br.com.ibm.provafinal.gateway.conteudo.feign.client;

import br.com.ibm.provafinal.gateway.conteudo.model.ConteudoEntrada;
import br.com.ibm.provafinal.gateway.conteudo.model.ConteudoSaida;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="conteudo", url="http://localhost:8084/conteudo")
public interface ConteudoClient {
    @PostMapping
    public ConteudoSaida salvar(ConteudoEntrada conteudoBean);

    @GetMapping
    public List<ConteudoSaida> listar();

    @PutMapping("/alterarTitulo/{id}")
    public ConteudoSaida alterarTitulo(@PathVariable Long id, ConteudoEntrada entrada) throws Exception;

    @GetMapping("/{id}")
    public ConteudoSaida obterPorId(@PathVariable Long id) throws Exception;
}
