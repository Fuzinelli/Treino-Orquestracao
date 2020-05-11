package br.com.ibm.provafinal.gateway.conteudo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConteudoEntity {
    Long id;
    String titulo;
    String tipo;
    String categoria;
}
