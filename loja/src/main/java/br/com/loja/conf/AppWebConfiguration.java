package br.com.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.loja.controllers.HomeController;
import br.com.loja.dao.ProdutoDAO;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, ProdutoDAO.class})
public class AppWebConfiguration {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}
	
	@Bean
	public MessageSource messageSource(){
		 ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		 reloadableResourceBundleMessageSource.setBasename("WEB-INF/messages");
		 reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
		 reloadableResourceBundleMessageSource.setCacheSeconds(1);
		 return reloadableResourceBundleMessageSource;
	}

}
