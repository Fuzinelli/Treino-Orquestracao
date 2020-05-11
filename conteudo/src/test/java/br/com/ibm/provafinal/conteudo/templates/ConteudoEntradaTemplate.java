package br.com.ibm.provafinal.conteudo.templates;

import br.com.ibm.provafinal.conteudo.model.ConteudoEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ConteudoEntradaTemplate implements TemplateLoader {
    public static final String CONTEUDO_ENTRADA_VALIDO = "conteudo entrada valido";
    @Override
    public void load() {
        Fixture.of(ConteudoEntrada.class).addTemplate(CONTEUDO_ENTRADA_VALIDO, new Rule(){{
            add("titulo", "la casa de papel");
            add("tipo", "serie");
            add("categoria", "acao");
        }});

    }
}
