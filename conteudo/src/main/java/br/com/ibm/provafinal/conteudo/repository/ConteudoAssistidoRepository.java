package br.com.ibm.provafinal.conteudo.repository;

import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConteudoAssistidoRepository extends JpaRepository<ConteudoAssistidoEntity, Long> {
    public List<ConteudoAssistidoEntity> findByUsuarioId(Long id);
}
