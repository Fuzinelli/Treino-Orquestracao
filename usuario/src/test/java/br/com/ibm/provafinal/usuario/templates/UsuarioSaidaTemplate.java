package br.com.ibm.provafinal.usuario.templates;

import br.com.ibm.provafinal.usuario.model.UsuarioSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class UsuarioSaidaTemplate implements TemplateLoader {
    public static final String USUARIO_SAIDA_VALIDO = "usuario saida valido";
    @Override
    public void load() {
        Fixture.of(UsuarioSaida.class).addTemplate(USUARIO_SAIDA_VALIDO, new Rule(){{
            add("id", 1L);
            add("nome", "gustavo");
        }});
    }
}
