package br.com.ibm.provafinal.conteudo.templates;

import br.com.ibm.provafinal.conteudo.model.ConteudoEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ConteudoEntityTemplate implements TemplateLoader {
    public static final String CONTEUDO_ENTITY_VALIDO = "conteudo entity valido";
    @Override
    public void load() {
        Fixture.of(ConteudoEntity.class).addTemplate(CONTEUDO_ENTITY_VALIDO, new Rule(){{
            add("id", 1L);
            add("titulo", "la casa de papel");
            add("tipo", "serie");
            add("categoria", "acao");
        }});

    }
}
