package br.com.ibm.provafinal.conteudo.mapper;

import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoEntity;
import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoEntrada;
import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoSaida;
import br.com.ibm.provafinal.conteudo.model.ConteudoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConteudoAssistidoMapper {
    ConteudoAssistidoMapper INSTANCE = Mappers.getMapper(ConteudoAssistidoMapper.class);
    @Mappings({
            @Mapping(source = "conteudo", target = "conteudo"),
    })

    ConteudoAssistidoEntity mapToEntity(ConteudoAssistidoEntrada conteudoAssistidoEntrada, ConteudoEntity conteudo);
    ConteudoAssistidoSaida mapToSaida(ConteudoAssistidoEntity conteudoAssistidoEntity);
}
