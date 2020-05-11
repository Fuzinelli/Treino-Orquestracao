package br.com.ibm.provafinal.conteudo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConteudoAssistidoSaida {
    Long id;
    ConteudoSaida conteudo;
    Long usuarioId;
}
