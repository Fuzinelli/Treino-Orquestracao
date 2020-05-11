package br.com.ibm.provafinal.conteudo.mapper;

import br.com.ibm.provafinal.conteudo.model.*;
import br.com.ibm.provafinal.conteudo.templates.ConteudoAssistidoEntityTemplate;
import br.com.ibm.provafinal.conteudo.templates.ConteudoAssistidoEntradaTemplate;
import br.com.ibm.provafinal.conteudo.templates.ConteudoEntityTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ConteudoAssistidoMapperTest {
    ConteudoAssistidoMapper conteudoAssistidoMapper = Mappers.getMapper(ConteudoAssistidoMapper.class);

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.ibm.provafinal.conteudo.templates");
    }
    @Test
    public void deveConverterEntradaParaEntity() {
        ConteudoAssistidoEntrada entrada = Fixture.from(ConteudoAssistidoEntrada.class).gimme(ConteudoAssistidoEntradaTemplate.CONTEUDO_ASSISTIDO_ENTRADA_VALIDO);
        ConteudoEntity conteudoEntity = Fixture.from(ConteudoEntity.class).gimme(ConteudoEntityTemplate.CONTEUDO_ENTITY_VALIDO);
        ConteudoAssistidoEntity entity = conteudoAssistidoMapper.mapToEntity(entrada, conteudoEntity);
        Assert.assertNotNull(entity);
        Assert.assertEquals(entrada.getConteudo(), entity.getConteudo().getId());
        Assert.assertEquals(entrada.getUsuarioId(),entity.getUsuarioId());
    }
    @Test
    public void deveConverterClasseEntityParaSaida(){
        ConteudoAssistidoEntity entity = Fixture.from(ConteudoAssistidoEntity.class).gimme(ConteudoAssistidoEntityTemplate.CONTEUDO_ASSISTIDO_ENTITY_VALIDO);
        ConteudoAssistidoSaida saida = conteudoAssistidoMapper.mapToSaida(entity);
        Assert.assertNotNull(saida);
        Assert.assertEquals(entity.getId(), saida.getId());
        Assert.assertEquals(entity.getUsuarioId(), saida.getUsuarioId());
        Assert.assertEquals(entity.getConteudo().getId(), saida.getConteudo().getId());
        Assert.assertEquals(entity.getConteudo().getTipo(), saida.getConteudo().getTipo());
        Assert.assertEquals(entity.getConteudo().getTitulo(), saida.getConteudo().getTitulo());
        Assert.assertEquals(entity.getConteudo().getCategoria(), saida.getConteudo().getCategoria());
    }
}
