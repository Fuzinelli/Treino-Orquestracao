package br.com.ibm.provafinal.gateway.conteudoassistido.service;

import br.com.ibm.provafinal.gateway.conteudoassistido.feign.client.ConteudoAssistidoClient;
import br.com.ibm.provafinal.gateway.conteudoassistido.model.ConteudoAssistidoEntrada;
import br.com.ibm.provafinal.gateway.conteudoassistido.model.ConteudoAssistidoSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConteudoAssistidoService {
    @Autowired
    ConteudoAssistidoClient conteudoAssistidoClient;

    public ConteudoAssistidoSaida salvar(ConteudoAssistidoEntrada entrada) throws Exception {
        return conteudoAssistidoClient.salvar(entrada);
    }

    public List<ConteudoAssistidoSaida> listarPorUsuarioId(Long id){
        return conteudoAssistidoClient.listarPorUsuarioId(id);
    }
}
