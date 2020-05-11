package br.com.ibm.provafinal.usuario.facade;

import br.com.ibm.provafinal.usuario.model.UsuarioEntity;
import br.com.ibm.provafinal.usuario.model.UsuarioEntrada;
import br.com.ibm.provafinal.usuario.model.UsuarioSaida;
import br.com.ibm.provafinal.usuario.repository.UsuarioRepository;
import br.com.ibm.provafinal.usuario.templates.UsuarioEntityTemplate;
import br.com.ibm.provafinal.usuario.templates.UsuarioEntradaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class UsuarioFacadeTest {
    @InjectMocks
    UsuarioFacade usuarioFacade;
    @Mock
    UsuarioRepository usuarioRepository;
    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.ibm.provafinal.usuario.templates");
    }

    @Test
    public void deveListar(){
        List<UsuarioEntity> usuarios = Fixture.from(UsuarioEntity.class).gimme(1, UsuarioEntityTemplate.USUARIO_ENTITY_VALIDO);
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);
        List<UsuarioSaida> saida = usuarioFacade.listar();
        Assert.assertNotNull(saida);
        Assert.assertEquals(usuarios.get(0).getId(), saida.get(0).getId());
        Assert.assertEquals(usuarios.get(0).getNome(), saida.get(0).getNome());
    }
    @Test
    public void deveSalvar(){
        UsuarioEntrada usuarioEntrada = Fixture.from(UsuarioEntrada.class).gimme(UsuarioEntradaTemplate.USUARIO_ENTRADA_VALIDO);
        UsuarioEntity usuarioEntity = Fixture.from(UsuarioEntity.class).gimme(UsuarioEntityTemplate.USUARIO_ENTITY_VALIDO);
        Mockito.when(usuarioRepository.save(Mockito.anyObject())).thenReturn(usuarioEntity);
        UsuarioSaida usuarioSaida = usuarioFacade.salvar(usuarioEntrada);
        Assert.assertNotNull(usuarioSaida);
        Assert.assertEquals(usuarioEntity.getId(), usuarioSaida.getId());
        Assert.assertEquals(usuarioEntity.getNome(), usuarioSaida.getNome());
        Assert.assertEquals(usuarioEntrada.getNome(), usuarioEntity.getNome());
        Assert.assertEquals(usuarioEntrada.getNome(), usuarioSaida.getNome());
    }

    @Test
    public void deveAlterarNome() throws Exception{
        Long id = 1L;
        UsuarioEntrada usuarioEntrada = Fixture.from(UsuarioEntrada.class).gimme(UsuarioEntradaTemplate.USUARIO_ENTRADA_VALIDO);
        UsuarioEntity usuarioEntity = Fixture.from(UsuarioEntity.class).gimme(UsuarioEntityTemplate.USUARIO_ENTITY_VALIDO);
        Mockito.when(usuarioRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(usuarioEntity));
        Mockito.when(usuarioRepository.save(Mockito.anyObject())).thenReturn(usuarioEntity);
        UsuarioSaida usuarioSaida = usuarioFacade.alterarNome(id, usuarioEntrada);
        Assert.assertNotNull(usuarioSaida);
        Assert.assertEquals(usuarioEntrada.getNome(), usuarioSaida.getNome());
        Assert.assertEquals(usuarioEntrada.getNome(), usuarioEntity.getNome());
    }

    @Test
    public void deveObterPorId() throws Exception{
        Long id = 1L;
        UsuarioEntity usuarioEntity = Fixture.from(UsuarioEntity.class).gimme(UsuarioEntityTemplate.USUARIO_ENTITY_VALIDO);
        Mockito.when(usuarioRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(usuarioEntity));
        UsuarioSaida usuarioSaida = usuarioFacade.buscarPorId(id);
        Assert.assertNotNull(usuarioSaida);
        Assert.assertEquals(usuarioEntity.getId(), usuarioSaida.getId());
        Assert.assertEquals(usuarioEntity.getNome(), usuarioSaida.getNome());
    }
}
