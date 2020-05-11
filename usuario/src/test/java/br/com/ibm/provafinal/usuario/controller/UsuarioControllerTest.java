package br.com.ibm.provafinal.usuario.controller;

import br.com.ibm.provafinal.usuario.facade.UsuarioFacade;
import br.com.ibm.provafinal.usuario.model.UsuarioEntrada;
import br.com.ibm.provafinal.usuario.model.UsuarioSaida;
import br.com.ibm.provafinal.usuario.templates.UsuarioEntradaTemplate;
import br.com.ibm.provafinal.usuario.templates.UsuarioSaidaTemplate;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class UsuarioControllerTest {
    @InjectMocks
    UsuarioController usuarioController;

    @Mock
    UsuarioFacade usuarioFacade;

    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
        FixtureFactoryLoader.loadTemplates("br.com.ibm.provafinal.usuario.templates");
    }

    @Test
    public void deveListar() throws Exception{
        List<UsuarioSaida> usuarioSaida = new ArrayList<UsuarioSaida>()
        {{add(Fixture.from(UsuarioSaida.class).gimme(UsuarioSaidaTemplate.USUARIO_SAIDA_VALIDO));}};
        UsuarioEntrada usuarioEntrada = Fixture.from(UsuarioEntrada.class).gimme(UsuarioEntradaTemplate.USUARIO_ENTRADA_VALIDO);
        Mockito.when(usuarioFacade.listar()).thenReturn(usuarioSaida);
        MvcResult result = mockMvc.perform(get("/usuario")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        List<UsuarioSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, UsuarioSaida[].class));
        Assert.assertNotNull(saida);
        Assert.assertEquals(usuarioSaida.get(0).getId(), saida.get(0).getId());
        Assert.assertEquals(usuarioSaida.get(0).getNome(), saida.get(0).getNome());
        Assert.assertEquals(usuarioEntrada.getNome(), usuarioSaida.get(0).getNome());
        Assert.assertEquals(usuarioEntrada.getNome(), saida.get(0).getNome());
    }

    @Test
    public void deveSalvar() throws Exception {
        UsuarioSaida usuarioSaida = Fixture.from(UsuarioSaida.class).gimme(UsuarioSaidaTemplate.USUARIO_SAIDA_VALIDO);

        UsuarioEntrada usuarioEntrada = Fixture.from(UsuarioEntrada.class).gimme(UsuarioEntradaTemplate.USUARIO_ENTRADA_VALIDO);
        Mockito.when(usuarioFacade.salvar(Mockito.any(UsuarioEntrada.class))).thenReturn(usuarioSaida);
        MvcResult result = mockMvc.perform(post("/usuario")
                .content(asJsonString(usuarioEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        UsuarioSaida saida = new ObjectMapper().readValue(json, UsuarioSaida.class);
        Assert.assertNotNull(saida);
        Assert.assertEquals(usuarioEntrada.getNome(), usuarioSaida.getNome());
    }
    @Test
    public void deveAlterarNome()throws Exception{
        UsuarioSaida usuarioSaida = Fixture.from(UsuarioSaida.class).gimme(UsuarioSaidaTemplate.USUARIO_SAIDA_VALIDO);
        UsuarioEntrada usuarioEntrada = Fixture.from(UsuarioEntrada.class).gimme(UsuarioEntradaTemplate.USUARIO_ENTRADA_VALIDO);
        Long id = 1L;
        Mockito.when(usuarioFacade.alterarNome(Mockito.anyLong(), Mockito.any(UsuarioEntrada.class))).thenReturn(usuarioSaida);
        MvcResult result = mockMvc.perform(put("/usuario/alterarNome/"+id)
                .content(asJsonString(usuarioEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        UsuarioSaida saida = new ObjectMapper().readValue(json, UsuarioSaida.class);
        Assert.assertNotNull(saida);
        Assert.assertEquals(usuarioSaida.getNome(), saida.getNome());
        Assert.assertEquals(usuarioSaida.getId(), saida.getId());

    }
    @Test
        public void deveObterPorId()throws Exception{
            UsuarioSaida usuarioSaida = Fixture.from(UsuarioSaida.class).gimme(UsuarioSaidaTemplate.USUARIO_SAIDA_VALIDO);
            UsuarioEntrada usuarioEntrada = Fixture.from(UsuarioEntrada.class).gimme(UsuarioEntradaTemplate.USUARIO_ENTRADA_VALIDO);
            Long id = 1L;
            Mockito.when(usuarioFacade.buscarPorId(Mockito.anyLong())).thenReturn(usuarioSaida);
            MvcResult result = mockMvc.perform(get("/usuario/"+id)
                    .content(asJsonString(usuarioEntrada))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();
            String json = result.getResponse().getContentAsString();
            UsuarioSaida saida = new ObjectMapper().readValue(json, UsuarioSaida.class);
        Assert.assertNotNull(saida);
        Assert.assertEquals(usuarioEntrada.getNome(), usuarioSaida.getNome());
        Assert.assertEquals(usuarioSaida.getNome(),saida.getNome());
        Assert.assertEquals(usuarioSaida.getId(), saida.getId());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
