package br.com.ibm.provafinal.gateway.usuario.service;

import br.com.ibm.provafinal.gateway.usuario.feign.client.UsuarioClient;
import br.com.ibm.provafinal.gateway.usuario.model.UsuarioEntrada;
import br.com.ibm.provafinal.gateway.usuario.model.UsuarioSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioClient usuarioClient;

    public List<UsuarioSaida> listar(){
        return usuarioClient.listar();
    }
    public UsuarioSaida salvar(UsuarioEntrada entrada){
        return usuarioClient.salvar(entrada);
    }
    public UsuarioSaida alterarNome(Long id, UsuarioEntrada entrada) throws Exception {
        return usuarioClient.alterarNome(id, entrada);
    }
    public UsuarioSaida buscarPorId(Long id) throws Exception{
        return usuarioClient.obterPorId(id);
    }
}
