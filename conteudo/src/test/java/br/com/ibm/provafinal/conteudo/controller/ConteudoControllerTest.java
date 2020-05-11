package br.com.ibm.provafinal.conteudo.controller;

import br.com.ibm.provafinal.conteudo.facade.ConteudoFacade;
import br.com.ibm.provafinal.conteudo.model.ConteudoEntrada;
import br.com.ibm.provafinal.conteudo.model.ConteudoSaida;
import br.com.ibm.provafinal.conteudo.templates.ConteudoEntradaTemplate;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class ConteudoControllerTest {
    @InjectMocks
    ConteudoController conteudoController;
    @Mock
    ConteudoFacade conteudoFacade;
    MockMvc mockMvc;
    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(conteudoController).build();
        FixtureFactoryLoader.loadTemplates("br.com.ibm.provafinal.conteudo.templates");
    }
    @Test
    public void deveListar() throws Exception{
        List<ConteudoSaida> conteudoSaida = new ArrayList<ConteudoSaida>()
        {{add(Fixture.from(ConteudoSaida.class).gimme(ConteudoSaidaTemplate.CONTEUDO_SAIDA_VALIDO));}};
        ConteudoEntrada conteudoEntrada = Fixture.from(ConteudoEntrada.class).gimme(ConteudoEntradaTemplate.CONTEUDO_ENTRADA_VALIDO);
        Mockito.when(conteudoFacade.listar()).thenReturn(conteudoSaida);
        MvcResult result = mockMvc.perform(get("/conteudo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        List<ConteudoSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, ConteudoSaida[].class));
        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.get(0).getId(), conteudoSaida.get(0).getId());
        Assert.assertEquals(saida.get(0).getTitulo(), conteudoSaida.get(0).getTitulo());
        Assert.assertEquals(saida.get(0).getCategoria(), conteudoSaida.get(0).getCategoria());
        Assert.assertEquals(saida.get(0).getTipo(), conteudoSaida.get(0).getTipo());
        Assert.assertEquals(saida.get(0).getTitulo(), conteudoEntrada.getTitulo());
        Assert.assertEquals(saida.get(0).getCategoria(), conteudoEntrada.getCategoria());
        Assert.assertEquals(saida.get(0).getTipo(), conteudoEntrada.getTipo());

    }

    @Test
    public void deveSalvar() throws Exception {
        ConteudoSaida conteudoSaida = Fixture.from(ConteudoSaida.class).gimme(ConteudoSaidaTemplate.CONTEUDO_SAIDA_VALIDO);
        ConteudoEntrada conteudoEntrada = Fixture.from(ConteudoEntrada.class).gimme(ConteudoEntradaTemplate.CONTEUDO_ENTRADA_VALIDO);
        Mockito.when(conteudoFacade.salvar(Mockito.any(ConteudoEntrada.class))).thenReturn(conteudoSaida);
        MvcResult result = mockMvc.perform(post("/conteudo")
                .content(asJsonString(conteudoEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        ConteudoSaida saida = new ObjectMapper().readValue(json, ConteudoSaida.class);
        Assert.assertNotNull(saida);
        Assert.assertEquals(conteudoEntrada.getTitulo(), conteudoSaida.getTitulo());
        Assert.assertEquals(conteudoEntrada.getCategoria(), conteudoSaida.getCategoria());
        Assert.assertEquals(conteudoEntrada.getTipo(), conteudoSaida.getTipo());
        Assert.assertEquals(conteudoSaida.getId(), saida.getId());
        Assert.assertEquals(conteudoSaida.getTipo(), saida.getTipo());
        Assert.assertEquals(conteudoSaida.getCategoria(), saida.getCategoria());
        Assert.assertEquals(conteudoSaida.getTitulo(), saida.getTitulo());
    }
    @Test
    public void deveAlterarTitulo()throws Exception{
        ConteudoSaida conteudoSaida = Fixture.from(ConteudoSaida.class).gimme(ConteudoSaidaTemplate.CONTEUDO_SAIDA_VALIDO);
        ConteudoEntrada conteudoEntrada = Fixture.from(ConteudoEntrada.class).gimme(ConteudoEntradaTemplate.CONTEUDO_ENTRADA_VALIDO);
        Long id = 1L;
        Mockito.when(conteudoFacade.alterarTitulo(Mockito.anyLong(), Mockito.any(ConteudoEntrada.class))).thenReturn(conteudoSaida);
        MvcResult result = mockMvc.perform(put("/conteudo/alterarTitulo/"+id)
                .content(asJsonString(conteudoEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        ConteudoSaida saida = new ObjectMapper().readValue(json, ConteudoSaida.class);
        Assert.assertNotNull(saida);
        Assert.assertEquals(conteudoEntrada.getTitulo(), conteudoSaida.getTitulo());
        Assert.assertEquals(conteudoSaida.getTitulo(), saida.getTitulo());
        Assert.assertEquals(conteudoEntrada.getTitulo(), saida.getTitulo());

    }
    @Test
    public void deveObterPorId()throws Exception{
        ConteudoSaida conteudoSaida = Fixture.from(ConteudoSaida.class).gimme(ConteudoSaidaTemplate.CONTEUDO_SAIDA_VALIDO);
        ConteudoEntrada conteudoEntrada = Fixture.from(ConteudoEntrada.class).gimme(ConteudoEntradaTemplate.CONTEUDO_ENTRADA_VALIDO);
        Long id = 1L;
        Mockito.when(conteudoFacade.buscarPorId(Mockito.anyLong())).thenReturn(conteudoSaida);
        MvcResult result = mockMvc.perform(get("/conteudo/"+id)
                .content(asJsonString(conteudoEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        ConteudoSaida saida = new ObjectMapper().readValue(json, ConteudoSaida.class);
        Assert.assertNotNull(saida);
        Assert.assertEquals(conteudoEntrada.getTitulo(), conteudoSaida.getTitulo());
        Assert.assertEquals(conteudoEntrada.getTipo(), conteudoSaida.getTipo());
        Assert.assertEquals(conteudoEntrada.getCategoria(), conteudoSaida.getCategoria());
        Assert.assertEquals(conteudoSaida.getId(), saida.getId());
        Assert.assertEquals(conteudoSaida.getTitulo(), saida.getTitulo());
        Assert.assertEquals(conteudoSaida.getCategoria(), saida.getCategoria());
        Assert.assertEquals(conteudoEntrada.getTipo(), saida.getTipo());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
