package br.com.ibm.provafinal.conteudo.templates;

import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ConteudoAssistidoEntradaTemplate implements TemplateLoader {
    public static final String CONTEUDO_ASSISTIDO_ENTRADA_VALIDO = "conteudo assistido entrada valido";
    @Override
    public void load() {
        Fixture.of(ConteudoAssistidoEntrada.class).addTemplate(CONTEUDO_ASSISTIDO_ENTRADA_VALIDO, new Rule(){{
            add("conteudo", 1L);
            add("usuarioId", 1L);
        }});
    }
}
