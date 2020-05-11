package br.com.ibm.provafinal.gateway.service;

import br.com.ibm.provafinal.gateway.conteudo.model.ConteudoSaida;
import br.com.ibm.provafinal.gateway.conteudo.service.ConteudoService;
import br.com.ibm.provafinal.gateway.conteudoassistido.model.ConteudoAssistidoEntrada;
import br.com.ibm.provafinal.gateway.conteudoassistido.model.ConteudoAssistidoSaida;
import br.com.ibm.provafinal.gateway.conteudoassistido.service.ConteudoAssistidoService;
import br.com.ibm.provafinal.gateway.model.UsuarioConteudoAssistidoEntrada;
import br.com.ibm.provafinal.gateway.model.UsuarioConteudoAssistidoSaida;
import br.com.ibm.provafinal.gateway.usuario.model.UsuarioSaida;
import br.com.ibm.provafinal.gateway.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioConteudoAssistidoService {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ConteudoAssistidoService conteudoAssistidoService;

    @Autowired
    ConteudoService conteudoService;

    public UsuarioConteudoAssistidoSaida salvar(UsuarioConteudoAssistidoEntrada entrada) throws Exception {
       /* UsuarioSaida usuarioSaida = usuarioService.buscarPorId(entrada.getUsuario().getId());
        List<ConteudoAssistidoSaida> saida = new ArrayList<>();
        for (ConteudoAssistidoEntrada conteudoAssistidoEntrada : entrada.getConteudosAssistidos()){
            ConteudoSaida conteudo = conteudoService.buscarPorId(entrada.getConteudosAssistidos().get(0).getConteudo());*/

        //}
        UsuarioConteudoAssistidoSaida saida = new UsuarioConteudoAssistidoSaida();
        UsuarioSaida usuarioSaida = usuarioService.buscarPorId(entrada.getUsuario().getId());
        saida.setUsuario(usuarioSaida);
        List<ConteudoAssistidoSaida> conteudoAssistidoSaidas = new ArrayList<>();
        for (ConteudoAssistidoEntrada conteudo : entrada.getConteudosAssistidos()){
            conteudo.setUsuarioId(usuarioSaida.getId());
            ConteudoAssistidoSaida conteudoAssistidoSaida = conteudoAssistidoService.salvar(conteudo);
            conteudoAssistidoSaidas.add(conteudoAssistidoSaida);
            saida.setConteudosAssistidos(conteudoAssistidoSaidas);
        }
        return saida;
    }

    public UsuarioConteudoAssistidoSaida listarUsuariosEConteudosAssistidos(Long id) throws Exception {
        UsuarioSaida usuario = usuarioService.buscarPorId(id);
        List<ConteudoAssistidoSaida> conteudosAssistidos = conteudoAssistidoService.listarPorUsuarioId(id);
        UsuarioConteudoAssistidoSaida saida = new UsuarioConteudoAssistidoSaida();
        saida.setUsuario(usuario);
        saida.setConteudosAssistidos(conteudosAssistidos);
        return saida;
    }
}
