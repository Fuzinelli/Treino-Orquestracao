package br.com.ibm.provafinal.conteudo.facade;

import br.com.ibm.provafinal.conteudo.model.ConteudoEntity;
import br.com.ibm.provafinal.conteudo.model.ConteudoEntrada;
import br.com.ibm.provafinal.conteudo.model.ConteudoSaida;
import br.com.ibm.provafinal.conteudo.repository.ConteudoRepository;
import br.com.ibm.provafinal.conteudo.templates.ConteudoEntityTemplate;
import br.com.ibm.provafinal.conteudo.templates.ConteudoEntradaTemplate;
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
public class ConteudoFacadeTest {
    @InjectMocks
    ConteudoFacade conteudoFacade;

    @Mock
    ConteudoRepository conteudoRepository;

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.ibm.provafinal.conteudo.templates");
    }
    @Test
    public void deveListarConteudos() {
        List<ConteudoEntity> conteudoEntity = Fixture.from(ConteudoEntity.class).gimme(1, ConteudoEntityTemplate.CONTEUDO_ENTITY_VALIDO);
        Mockito.when(conteudoRepository.findAll()).thenReturn(conteudoEntity);
        List<ConteudoSaida> conteudoSaida = conteudoFacade.listar();
        Assert.assertNotNull(conteudoSaida);
        Assert.assertEquals(conteudoEntity.get(0).getId(), conteudoSaida.get(0).getId());
        Assert.assertEquals(conteudoEntity.get(0).getTitulo(), conteudoSaida.get(0).getTitulo());
        Assert.assertEquals(conteudoEntity.get(0).getCategoria(), conteudoSaida.get(0).getCategoria());
        Assert.assertEquals(conteudoEntity.get(0).getTipo(), conteudoSaida.get(0).getTipo());
    }

    @Test
    public void deveSalvarConteudo() {
        ConteudoEntrada conteudoEntrada = Fixture.from(ConteudoEntrada.class).gimme(ConteudoEntradaTemplate.CONTEUDO_ENTRADA_VALIDO);
        ConteudoEntity conteudoEntity = Fixture.from(ConteudoEntity.class).gimme(ConteudoEntityTemplate.CONTEUDO_ENTITY_VALIDO);
        Mockito.when(conteudoRepository.save(Mockito.anyObject())).thenReturn(conteudoEntity);
        ConteudoSaida conteudoSaida = conteudoFacade.salvar(conteudoEntrada);
        Assert.assertNotNull(conteudoSaida);
        Assert.assertEquals(conteudoEntrada.getTitulo(), conteudoSaida.getTitulo());
        Assert.assertEquals(conteudoEntrada.getTipo(), conteudoSaida.getTipo());
        Assert.assertEquals(conteudoEntrada.getCategoria(), conteudoSaida.getCategoria());
        Assert.assertEquals(conteudoEntity.getId(), conteudoSaida.getId());
    }

    @Test
    public void deveAlterarTitulo() throws Exception{
        Long id = 1L;
        ConteudoEntrada conteudoEntrada = Fixture.from(ConteudoEntrada.class).gimme(ConteudoEntradaTemplate.CONTEUDO_ENTRADA_VALIDO);
        ConteudoEntity conteudoEntity = Fixture.from(ConteudoEntity.class).gimme(ConteudoEntityTemplate.CONTEUDO_ENTITY_VALIDO);
        Mockito.when(conteudoRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(conteudoEntity));
        Mockito.when(conteudoRepository.save(Mockito.anyObject())).thenReturn(conteudoEntity);
        ConteudoSaida conteudoSaida = conteudoFacade.alterarTitulo(id, conteudoEntrada);
        Assert.assertNotNull(conteudoSaida);
        Assert.assertEquals(conteudoEntrada.getTitulo(), conteudoSaida.getTitulo());
    }
    @Test
    public void deveObterPorId() throws Exception {
        Long id = 1L;
        ConteudoEntity conteudoEntity = Fixture.from(ConteudoEntity.class).gimme(ConteudoEntityTemplate.CONTEUDO_ENTITY_VALIDO);
        Mockito.when(conteudoRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(conteudoEntity));
        ConteudoSaida conteudoSaida = conteudoFacade.buscarPorId(id);
        Assert.assertNotNull(conteudoSaida);
        Assert.assertEquals(conteudoEntity.getId(), conteudoSaida.getId());
        Assert.assertEquals(conteudoEntity.getTitulo(), conteudoSaida.getTitulo());
        Assert.assertEquals(conteudoEntity.getCategoria(), conteudoSaida.getCategoria());
        Assert.assertEquals(conteudoEntity.getTipo(), conteudoSaida.getTipo());
    }
}
