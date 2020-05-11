package br.com.ibm.provafinal.usuario.repository;

import br.com.ibm.provafinal.usuario.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
