package br.com.ibm.provafinal.conteudo.facade;

import br.com.ibm.provafinal.conteudo.mapper.ConteudoMapper;
import br.com.ibm.provafinal.conteudo.model.ConteudoEntity;
import br.com.ibm.provafinal.conteudo.model.ConteudoEntrada;
import br.com.ibm.provafinal.conteudo.model.ConteudoSaida;
import br.com.ibm.provafinal.conteudo.repository.ConteudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConteudoFacade {
    @Autowired
    ConteudoRepository conteudoRepository;

    public ConteudoSaida salvar(ConteudoEntrada conteudoEntrada){
        ConteudoEntity conteudoEntity = ConteudoMapper.INSTANCE.mapToEntity(conteudoEntrada);
        conteudoEntity = conteudoRepository.save(conteudoEntity);
        ConteudoSaida conteudoSaida = ConteudoMapper.INSTANCE.mapToSaida(conteudoEntity);
        return conteudoSaida;
    }

    public List<ConteudoSaida> listar(){
        List<ConteudoEntity> conteudosBanco = conteudoRepository.findAll();
        List<ConteudoSaida> listaSaida = new ArrayList<>();
        for(ConteudoEntity saida : conteudosBanco){
            listaSaida.add(ConteudoMapper.INSTANCE.mapToSaida(saida));
        }
        return listaSaida;
    }

    public ConteudoSaida alterarTitulo(Long id, ConteudoEntrada conteudoEntrada) throws Exception {
        ConteudoEntity conteudoEntity;
        Optional<ConteudoEntity> conteudoBanco = conteudoRepository.findById(id);
        if (!conteudoBanco.isPresent()) {
            throw new Exception("conteudo nao encontrado");
        }
        conteudoEntity = conteudoBanco.get();
        conteudoEntity.setTitulo(conteudoEntrada.getTitulo());
        conteudoEntity = conteudoRepository.save(conteudoEntity);
        return ConteudoMapper.INSTANCE.mapToSaida(conteudoEntity);
    }
    public ConteudoSaida buscarPorId(Long id) throws Exception {
        Optional<ConteudoEntity> conteudo = conteudoRepository.findById(id);
        if(conteudo.isPresent()){
            conteudo.get();
        }else {
            throw new Exception("Conteudo não encontrado.");
        }
        ConteudoEntity conteudoEntity = conteudo.get();
        ConteudoSaida saida = ConteudoMapper.INSTANCE.mapToSaida(conteudoEntity);
        return saida;
    }
}
