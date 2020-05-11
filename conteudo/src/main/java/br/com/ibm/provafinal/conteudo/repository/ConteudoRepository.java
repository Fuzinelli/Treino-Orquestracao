package br.com.ibm.provafinal.conteudo.repository;


import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoEntity;
import br.com.ibm.provafinal.conteudo.model.ConteudoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConteudoRepository extends JpaRepository<ConteudoEntity, Long> {
}
