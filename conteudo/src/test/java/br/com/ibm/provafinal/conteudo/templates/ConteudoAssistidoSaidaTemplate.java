package br.com.ibm.provafinal.conteudo.templates;

import br.com.ibm.provafinal.conteudo.model.ConteudoAssistidoSaida;
import br.com.ibm.provafinal.conteudo.model.ConteudoSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ConteudoAssistidoSaidaTemplate implements TemplateLoader {
    public static final String CONTEUDO_ASSISTIDO_SAIDA_VALIDO = "conteudo assistido saida valido";
    @Override
    public void load() {
        Fixture.of(ConteudoAssistidoSaida.class).addTemplate(CONTEUDO_ASSISTIDO_SAIDA_VALIDO, new Rule(){{
            add("id", 1L);
            add("conteudo", one(ConteudoSaida.class, ConteudoSaidaTemplate.CONTEUDO_SAIDA_VALIDO));
            add("usuarioId", 1l);
        }});
    }
}
