package br.com.ibm.provafinal.conteudo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "conteudo")
public class ConteudoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "titulo")
    String titulo;

    @Column(name = "tipo")
    String tipo;

    @Column(name = "categoria")
    String categoria;
}
