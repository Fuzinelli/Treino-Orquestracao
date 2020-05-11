package br.com.ibm.provafinal.gateway.model;

import br.com.ibm.provafinal.gateway.conteudoassistido.model.ConteudoAssistidoSaida;
import br.com.ibm.provafinal.gateway.usuario.model.UsuarioSaida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioConteudoAssistidoSaida {
    UsuarioSaida usuario;
    List<ConteudoAssistidoSaida> conteudosAssistidos = new ArrayList<>();
}
