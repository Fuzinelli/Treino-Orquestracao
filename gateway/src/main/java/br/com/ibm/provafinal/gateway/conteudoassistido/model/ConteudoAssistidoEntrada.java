package br.com.ibm.provafinal.gateway.conteudoassistido.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConteudoAssistidoEntrada {
    Long conteudo;
    Long usuarioId;
}
