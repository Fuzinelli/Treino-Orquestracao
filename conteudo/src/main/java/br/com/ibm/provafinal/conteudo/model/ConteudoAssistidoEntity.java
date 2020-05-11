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
@Entity(name = "conteudo_assistido")
public class ConteudoAssistidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    ConteudoEntity conteudo;

    @Column(name = "usuarioId")
    Long usuarioId;
}
