package br.com.ibm.provafinal.conteudo.mapper;

import br.com.ibm.provafinal.conteudo.model.ConteudoEntity;
import br.com.ibm.provafinal.conteudo.model.ConteudoEntrada;
import br.com.ibm.provafinal.conteudo.model.ConteudoSaida;
import br.com.ibm.provafinal.conteudo.templates.ConteudoEntityTemplate;
import br.com.ibm.provafinal.conteudo.templates.ConteudoEntradaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ConteudoMappaerTest {
    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.ibm.provafinal.conteudo.templates");
    }
    @Test
    public void deveConverterEntradaParaEntity() {
        ConteudoEntrada conteudoEntrada = Fixture.from(ConteudoEntrada.class).gimme(ConteudoEntradaTemplate.CONTEUDO_ENTRADA_VALIDO);
        ConteudoEntity conteudoEntity = ConteudoMapper.INSTANCE.mapToEntity(conteudoEntrada);
        Assert.assertNotNull(conteudoEntity);
        Assert.assertEquals(conteudoEntrada.getTitulo(), conteudoEntity.getTitulo());
        Assert.assertEquals(conteudoEntrada.getCategoria(), conteudoEntity.getCategoria());
        Assert.assertEquals(conteudoEntrada.getTipo(), conteudoEntity.getTipo());
    }
    @Test
    public void deveConverterEntityParaSaida(){
        ConteudoEntity conteudoEntity = Fixture.from(ConteudoEntity.class).gimme(ConteudoEntityTemplate.CONTEUDO_ENTITY_VALIDO);
        ConteudoSaida conteudoSaida = ConteudoMapper.INSTANCE.mapToSaida(conteudoEntity);
        Assert.assertNotNull(conteudoSaida);
        Assert.assertEquals(conteudoEntity.getId(), conteudoSaida.getId());
        Assert.assertEquals(conteudoEntity.getTitulo(), conteudoSaida.getTitulo());
        Assert.assertEquals(conteudoEntity.getTipo(), conteudoSaida.getTipo());
        Assert.assertEquals(conteudoEntity.getCategoria(), conteudoSaida.getCategoria());
    }
}
