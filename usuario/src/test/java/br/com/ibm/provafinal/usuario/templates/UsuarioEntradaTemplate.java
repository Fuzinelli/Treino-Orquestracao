package br.com.ibm.provafinal.usuario.templates;

import br.com.ibm.provafinal.usuario.model.UsuarioEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class UsuarioEntradaTemplate implements TemplateLoader {
    public static final String USUARIO_ENTRADA_VALIDO = "usuario entrada valido";
    @Override
    public void load() {
        Fixture.of(UsuarioEntrada.class).addTemplate(USUARIO_ENTRADA_VALIDO, new Rule(){{
            add("nome", "gustavo");
        }});
    }
}
