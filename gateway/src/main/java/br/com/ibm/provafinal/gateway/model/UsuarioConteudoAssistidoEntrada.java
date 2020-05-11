package br.com.ibm.provafinal.gateway.model;

import br.com.ibm.provafinal.gateway.conteudoassistido.model.ConteudoAssistidoEntrada;
import br.com.ibm.provafinal.gateway.usuario.model.UsuarioEntrada;
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
public class UsuarioConteudoAssistidoEntrada {
    UsuarioEntrada usuario;
    List<ConteudoAssistidoEntrada> conteudosAssistidos = new ArrayList<>();
}
