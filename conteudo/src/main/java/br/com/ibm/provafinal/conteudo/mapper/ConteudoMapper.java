package br.com.ibm.provafinal.conteudo.mapper;

import br.com.ibm.provafinal.conteudo.model.ConteudoEntity;
import br.com.ibm.provafinal.conteudo.model.ConteudoEntrada;
import br.com.ibm.provafinal.conteudo.model.ConteudoSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConteudoMapper {
    ConteudoMapper INSTANCE = Mappers.getMapper(ConteudoMapper.class);

    ConteudoEntity mapToEntity(ConteudoEntrada conteudoEntrada);
    ConteudoSaida mapToSaida(ConteudoEntity conteudoEntity);
}
