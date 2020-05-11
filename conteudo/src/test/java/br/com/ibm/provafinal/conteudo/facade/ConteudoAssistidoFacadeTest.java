package br.com.ibm.provafinal.conteudo.facade;

import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoEntity;
import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoEntrada;
import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoSaida;
import br.com.ibm.provafinal.conteudo.model.ConteudoEntity;
import br.com.ibm.provafinal.conteudo.repository.ConteudoAssistidoRepository;
import br.com.ibm.provafinal.conteudo.repository.ConteudoRepository;
import br.com.ibm.provafinal.conteudo.templates.ConteudoAssistidoEntityTemplate;
import br.com.ibm.provafinal.conteudo.templates.ConteudoAssistidoEntradaTemplate;
import br.com.ibm.provafinal.conteudo.templates.ConteudoEntityTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class ConteudoAssistidoFacadeTest {
    @InjectMocks
    ConteudoAssistidoFacade conteudoAssistidoFacade;
    @Mock
    ConteudoAssistidoRepository conteudoAssistidoRepository;
    @Mock
    ConteudoRepository conteudoRepository;
    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.ibm.provafinal.conteudo.templates");
    }
    @Test
    public void deveSalvar() throws Exception{
        ConteudoAssistidoEntrada entrada = Fixture.from(ConteudoAssistidoEntrada.class).gimme(ConteudoAssistidoEntradaTemplate.CONTEUDO_ASSISTIDO_ENTRADA_VALIDO);
        ConteudoAssistidoEntity entity = Fixture.from(ConteudoAssistidoEntity.class).gimme(ConteudoAssistidoEntityTemplate.CONTEUDO_ASSISTIDO_ENTITY_VALIDO);
        Mockito.when(conteudoAssistidoRepository.save(Mockito.anyObject())).thenReturn(entity);
        ConteudoEntity conteudoEntity = Fixture.from(ConteudoEntity.class).gimme(ConteudoEntityTemplate.CONTEUDO_ENTITY_VALIDO);
        Mockito.when(conteudoRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(conteudoEntity));
        ConteudoAssistidoSaida saida = conteudoAssistidoFacade.salvar(entrada);
        Assert.assertNotNull(saida);
        Assert.assertEquals(entrada.getUsuarioId(), saida.getUsuarioId());
        Assert.assertEquals(entrada.getConteudo(), saida.getConteudo().getId());
        Assert.assertEquals(entity.getId(),saida.getId());
        Assert.assertEquals(entity.getUsuarioId(), saida.getUsuarioId());
        Assert.assertEquals(entity.getConteudo().getId(), saida.getConteudo().getId());
        Assert.assertEquals(entity.getConteudo().getTitulo(), saida.getConteudo().getTitulo());
        Assert.assertEquals(entity.getConteudo().getCategoria(), saida.getConteudo().getCategoria());
        Assert.assertEquals(entity.getConteudo().getTipo(), saida.getConteudo().getTipo());
    }
    @Test
    public void deveListarPorUsuarioId() throws Exception {
        Long id = 1L;
        List<ConteudoAssistidoEntity> entity = Fixture.from(ConteudoAssistidoEntity.class).gimme(1,ConteudoAssistidoEntityTemplate.CONTEUDO_ASSISTIDO_ENTITY_VALIDO);
        Mockito.when(conteudoAssistidoRepository.findByUsuarioId(Mockito.anyLong())).thenReturn(entity);
        List<ConteudoAssistidoSaida> saida = conteudoAssistidoFacade.listarPorUsuarioId(id);
        Assert.assertNotNull(saida);
        Assert.assertEquals(entity.get(0).getId(), saida.get(0).getId());
        Assert.assertEquals(entity.get(0).getUsuarioId(), saida.get(0).getUsuarioId());
        Assert.assertEquals(entity.get(0).getConteudo().getId(), saida.get(0).getConteudo().getId());
        Assert.assertEquals(entity.get(0).getConteudo().getTipo(), saida.get(0).getConteudo().getTipo());
        Assert.assertEquals(entity.get(0).getConteudo().getCategoria(), saida.get(0).getConteudo().getCategoria());
        Assert.assertEquals(entity.get(0).getConteudo().getTitulo(), saida.get(0).getConteudo().getTitulo());
    }
}
