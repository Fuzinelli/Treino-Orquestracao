package br.com.ibm.provafinal.conteudo.controller;

import br.com.ibm.provafinal.conteudo.facade.ConteudoAssistidoFacade;
import br.com.ibm.provafinal.conteudo.facade.ConteudoFacade;
import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoEntrada;
import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoSaida;
import br.com.ibm.provafinal.conteudo.model.ConteudoEntity;
import br.com.ibm.provafinal.conteudo.model.ConteudoSaida;
import br.com.ibm.provafinal.conteudo.templates.ConteudoAssistidoEntradaTemplate;
import br.com.ibm.provafinal.conteudo.templates.ConteudoAssistidoSaidaTemplate;
import br.com.ibm.provafinal.conteudo.templates.ConteudoEntityTemplate;
import br.com.ibm.provafinal.conteudo.templates.ConteudoSaidaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class ConteudoAssistidoControllerTest {
    @InjectMocks
    ConteudoAssistidoController conteudoAssistidoController;
    @Mock
    ConteudoAssistidoFacade conteudoAssistidoFacade;
    @Mock
    ConteudoFacade conteudoFacade;
    private MockMvc mockMvc;
    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(conteudoAssistidoController).build();
        FixtureFactoryLoader.loadTemplates("br.com.ibm.provafinal.conteudo.templates");
    }
    @Test
    public void deveSalvar() throws Exception {
        ConteudoAssistidoSaida conteudoAssistidoSaida = Fixture.from(ConteudoAssistidoSaida.class).gimme(ConteudoAssistidoSaidaTemplate.CONTEUDO_ASSISTIDO_SAIDA_VALIDO);
        ConteudoAssistidoEntrada entrada = Fixture.from(ConteudoAssistidoEntrada.class).gimme(ConteudoAssistidoEntradaTemplate.CONTEUDO_ASSISTIDO_ENTRADA_VALIDO);
        Mockito.when(conteudoAssistidoFacade.salvar(Mockito.any(ConteudoAssistidoEntrada.class))).thenReturn(conteudoAssistidoSaida);
        MvcResult result = mockMvc.perform(post("/conteudoAssistido")
                .content(asJsonString(entrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        ConteudoAssistidoSaida saida = new ObjectMapper().readValue(json, ConteudoAssistidoSaida.class);
        Assert.assertNotNull(saida);
        Assert.assertEquals(entrada.getUsuarioId(), conteudoAssistidoSaida.getUsuarioId());
        Assert.assertEquals(entrada.getConteudo(), conteudoAssistidoSaida.getConteudo().getId());
        Assert.assertEquals(conteudoAssistidoSaida.getId(), saida.getId());
        Assert.assertEquals(conteudoAssistidoSaida.getConteudo().getId(),saida.getConteudo().getId());
        Assert.assertEquals(conteudoAssistidoSaida.getUsuarioId(), saida.getUsuarioId());
        Assert.assertEquals(conteudoAssistidoSaida.getConteudo().getTitulo(), saida.getConteudo().getTitulo());
        Assert.assertEquals(conteudoAssistidoSaida.getConteudo().getCategoria(), saida.getConteudo().getCategoria());
        Assert.assertEquals(conteudoAssistidoSaida.getConteudo().getTipo(), saida.getConteudo().getTipo());
    }
   /* @Test
    public void deveListarPorUsuarioId()throws Exception {
        List<ConteudoAssistidoSaida> conteudoAssistidoSaida = Fixture.from(ConteudoAssistidoSaida.class).gimme(1,ConteudoAssistidoSaidaTemplate.CONTEUDO_ASSISTIDO_SAIDA_VALIDO);
        ConteudoAssistidoEntrada entrada = Fixture.from(ConteudoAssistidoEntrada.class).gimme(ConteudoAssistidoEntradaTemplate.CONTEUDO_ASSISTIDO_ENTRADA_VALIDO);
        ConteudoSaida conteudoSaida = Fixture.from(ConteudoSaida.class).gimme(ConteudoSaidaTemplate.CONTEUDO_SAIDA_VALIDO);
        Long id = 1L;
        Mockito.when(conteudoFacade.buscarPorId(Mockito.anyLong())).thenReturn(conteudoSaida);
        Mockito.when(conteudoAssistidoFacade.listarPorUsuarioId(Mockito.anyLong())).thenReturn(conteudoAssistidoSaida);
        MvcResult result = mockMvc.perform(get("/conteudoAssistido/"+id)
                .content(asJsonString(entrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        List<ConteudoSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, ConteudoSaida.class));
        Assert.assertNotNull(saida);
    }*/
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
