package br.com.ibm.provafinal.gateway.conteudoassistido.model;

import br.com.ibm.provafinal.gateway.conteudo.model.ConteudoSaida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConteudoAssistidoSaida {
    Long id;
    ConteudoSaida conteudo;
    Long usuarioId;
}
