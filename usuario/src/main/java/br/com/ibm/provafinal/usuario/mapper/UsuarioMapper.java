package br.com.ibm.provafinal.usuario.mapper;

import br.com.ibm.provafinal.usuario.model.UsuarioEntity;
import br.com.ibm.provafinal.usuario.model.UsuarioEntrada;
import br.com.ibm.provafinal.usuario.model.UsuarioSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioEntity mapToEntity(UsuarioEntrada usuarioEntrada);
    UsuarioSaida mapToSaida(UsuarioEntity usuarioEntity);
}
