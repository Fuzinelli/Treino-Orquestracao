package br.com.ibm.provafinal.gateway.conteudo.service;

import br.com.ibm.provafinal.gateway.conteudo.feign.client.ConteudoClient;
import br.com.ibm.provafinal.gateway.conteudo.model.ConteudoEntrada;
import br.com.ibm.provafinal.gateway.conteudo.model.ConteudoSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConteudoService {
    @Autowired
    ConteudoClient conteudoClient;

    public List<ConteudoSaida> listarConteudos(){
        return conteudoClient.listar();
    }

    public ConteudoSaida salvarConteudo(ConteudoEntrada entrada){
        return conteudoClient.salvar(entrada);
    }

    public ConteudoSaida alterarTitulo(Long id, ConteudoEntrada entrada) throws Exception {
        return conteudoClient.alterarTitulo(id, entrada);
    }
    public ConteudoSaida buscarPorId(Long id) throws Exception {
        return conteudoClient.obterPorId(id);
    }
}
