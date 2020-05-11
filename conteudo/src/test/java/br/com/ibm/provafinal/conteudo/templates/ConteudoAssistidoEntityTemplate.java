package br.com.ibm.provafinal.conteudo.templates;

import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoEntity;
import br.com.ibm.provafinal.conteudo.model.ConteudoEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ConteudoAssistidoEntityTemplate implements TemplateLoader {
    public static final String CONTEUDO_ASSISTIDO_ENTITY_VALIDO = "conteudo assistido entity valido";
    @Override
    public void load() {
        Fixture.of(ConteudoAssistidoEntity.class).addTemplate(CONTEUDO_ASSISTIDO_ENTITY_VALIDO, new Rule(){{
            add("id", 1L);
            add("conteudo", one(ConteudoEntity.class, ConteudoEntityTemplate.CONTEUDO_ENTITY_VALIDO));
            add("usuarioId", 1l);
        }});

    }
}
