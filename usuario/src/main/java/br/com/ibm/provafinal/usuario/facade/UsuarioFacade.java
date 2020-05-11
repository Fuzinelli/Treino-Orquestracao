package br.com.ibm.provafinal.usuario.facade;

import br.com.ibm.provafinal.usuario.mapper.UsuarioMapper;
import br.com.ibm.provafinal.usuario.model.UsuarioEntity;
import br.com.ibm.provafinal.usuario.model.UsuarioEntrada;
import br.com.ibm.provafinal.usuario.model.UsuarioSaida;
import br.com.ibm.provafinal.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioFacade {
    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioSaida salvar(UsuarioEntrada usuarioEntrada){
        UsuarioEntity usuarioEntity = UsuarioMapper.INSTANCE.mapToEntity(usuarioEntrada);
        usuarioEntity = usuarioRepository.save(usuarioEntity);
        UsuarioSaida saida = UsuarioMapper.INSTANCE.mapToSaida(usuarioEntity);
        return saida;
    }

    public List<UsuarioSaida> listar(){
        List<UsuarioEntity> usuariosBanco = usuarioRepository.findAll();
        List<UsuarioSaida> listaSaida = new ArrayList<>();
        for (UsuarioEntity saida : usuariosBanco){
            listaSaida.add(UsuarioMapper.INSTANCE.mapToSaida(saida));
        }
        return listaSaida;
    }

    public UsuarioSaida alterarNome(Long id, UsuarioEntrada usuarioEntrada) throws Exception {
        UsuarioEntity usuarioEntity;
        Optional<UsuarioEntity> usuarioBanco = usuarioRepository.findById(id);
        if (!usuarioBanco.isPresent()) {
        throw new Exception("usuario nao encontrado");
        }
        usuarioEntity = usuarioBanco.get();
        usuarioEntity.setNome(usuarioEntrada.getNome());
        usuarioEntity = usuarioRepository.save(usuarioEntity);
        return UsuarioMapper.INSTANCE.mapToSaida(usuarioEntity);
    }
    public UsuarioSaida buscarPorId(Long id) throws Exception {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            usuario.get();
        }else {
            throw new Exception("Cliente não encontrado.");
        }
        UsuarioEntity usuarioEntity = usuario.get();
        UsuarioSaida saida = UsuarioMapper.INSTANCE.mapToSaida(usuarioEntity);
        return saida;
    }
}