package br.com.ibm.provafinal.conteudo.facade;

import br.com.ibm.provafinal.conteudo.mapper.ConteudoAssistidoMapper;
import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoEntity;
import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoEntrada;
import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoSaida;
import br.com.ibm.provafinal.conteudo.model.ConteudoEntity;
import br.com.ibm.provafinal.conteudo.repository.ConteudoAssistidoRepository;
import br.com.ibm.provafinal.conteudo.repository.ConteudoRepository;
import org.hibernate.boot.spi.InFlightMetadataCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConteudoAssistidoFacade {
    @Autowired
    ConteudoAssistidoRepository conteudoAssistidoRepository;

    @Autowired
    ConteudoRepository conteudoRepository;

    public ConteudoAssistidoSaida salvar(ConteudoAssistidoEntrada entrada) throws Exception {
        Optional<ConteudoEntity> conteudoEntityOptional = conteudoRepository.findById(entrada.getConteudo());
        if (!conteudoEntityOptional.isPresent()){
            throw  new Exception("Conteudo nao encontrado");
        }
        ConteudoEntity conteudoEntity = conteudoEntityOptional.get();
        ConteudoAssistidoEntity entidade = ConteudoAssistidoMapper.INSTANCE.mapToEntity(entrada, conteudoEntity);
        entidade = conteudoAssistidoRepository.save(entidade);
        ConteudoAssistidoSaida saida = ConteudoAssistidoMapper.INSTANCE.mapToSaida(entidade);
        return saida;
    }

    public List<ConteudoAssistidoSaida> listarPorUsuarioId(Long id){
        List<ConteudoAssistidoEntity> conteudosBanco = conteudoAssistidoRepository.findByUsuarioId(id);
        List<ConteudoAssistidoSaida> listaSaida = new ArrayList<>();
        for(ConteudoAssistidoEntity saida : conteudosBanco){
            listaSaida.add(ConteudoAssistidoMapper.INSTANCE.mapToSaida(saida));
        }
        return listaSaida;
    }

}
