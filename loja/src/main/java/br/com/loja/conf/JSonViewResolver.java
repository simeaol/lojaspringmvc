package br.com.loja.conf;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class JSonViewResolver implements ViewResolver {

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		MappingJackson2JsonView converter = new MappingJackson2JsonView();//Classe conversor de objecto para json usando api jackson
		converter.setPrettyPrint(true);//estilisando o json
		return converter;
	}

}
