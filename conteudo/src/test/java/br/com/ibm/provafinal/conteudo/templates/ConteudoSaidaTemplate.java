package br.com.ibm.provafinal.conteudo.templates;

import br.com.ibm.provafinal.conteudo.model.ConteudoSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ConteudoSaidaTemplate implements TemplateLoader {
    public static final String CONTEUDO_SAIDA_VALIDO = "conteudo saida valido";
    @Override
    public void load() {
        Fixture.of(ConteudoSaida.class).addTemplate(CONTEUDO_SAIDA_VALIDO, new Rule(){{
            add("id", 1L);
            add("titulo", "la casa de papel");
            add("tipo", "serie");
            add("categoria", "acao");
        }});

    }
}
