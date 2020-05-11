package br.com.ibm.provafinal.usuario.templates;

import br.com.ibm.provafinal.usuario.model.UsuarioEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class UsuarioEntityTemplate implements TemplateLoader {
    public static final String USUARIO_ENTITY_VALIDO = "usuario entity valido";
    @Override
    public void load() {
        Fixture.of(UsuarioEntity.class).addTemplate(USUARIO_ENTITY_VALIDO, new Rule(){{
            add("id", 1L);
            add("nome", "gustavo");
        }});
    }
}
