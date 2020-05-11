package br.com.ibm.provafinal.usuario.mapper;

import br.com.ibm.provafinal.usuario.model.UsuarioEntity;
import br.com.ibm.provafinal.usuario.model.UsuarioEntrada;
import br.com.ibm.provafinal.usuario.model.UsuarioSaida;
import br.com.ibm.provafinal.usuario.templates.UsuarioEntityTemplate;
import br.com.ibm.provafinal.usuario.templates.UsuarioEntradaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UsuarioMapperTest {
    private UsuarioMapper usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.ibm.provafinal.usuario.templates");
    }
    @Test
    public void deveConverterEntradaParaEntity(){
        UsuarioEntrada usuarioEntrada = Fixture.from(UsuarioEntrada.class).gimme(UsuarioEntradaTemplate.USUARIO_ENTRADA_VALIDO);
        UsuarioEntity usuarioEntity = usuarioMapper.mapToEntity(usuarioEntrada);
        Assert.assertNotNull(usuarioEntity);
        Assert.assertEquals(usuarioEntrada.getNome(), usuarioEntity.getNome());
    }

    @Test
    public void deveConverterEntityParaSaida(){
        UsuarioEntity usuarioEntity = Fixture.from(UsuarioEntity.class).gimme(UsuarioEntityTemplate.USUARIO_ENTITY_VALIDO);
        UsuarioSaida usuarioSaida = usuarioMapper.mapToSaida(usuarioEntity);
        Assert.assertNotNull(usuarioSaida);
        Assert.assertEquals(usuarioEntity.getId(), usuarioSaida.getId());
        Assert.assertEquals(usuarioEntity.getNome(), usuarioSaida.getNome());
    }
}
