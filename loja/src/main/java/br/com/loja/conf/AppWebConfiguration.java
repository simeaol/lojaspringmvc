package br.com.loja.conf;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.loja.controllers.HomeController;
import br.com.loja.dao.ProdutoDAO;
import br.com.loja.infra.FileSaver;
import br.com.loja.models.CarrinhoCompras;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, ProdutoDAO.class, FileSaver.class, CarrinhoCompras.class})
@EnableCaching
public class AppWebConfiguration {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		
		/**disponibilizando todos os atributos do nosso modelo para jsp */
		//internalResourceViewResolver.setExposeContextBeansAsAttributes(true);
		
		/**restringindo a disponibilidade  dos atributos apenas da classe (Bean) especificada */
		internalResourceViewResolver.setExposedContextBeanNames("carrinhoCompras");
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
	
	@Bean
	public FormattingConversionService conversionService(){
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar dateFormatterRegistrar = new DateFormatterRegistrar();
		dateFormatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		dateFormatterRegistrar.registerFormatters(conversionService);
		return conversionService;		
	}
	
	@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
	}
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
		
	}

	@Bean
	public CacheManager cacheManager(){
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();
		builder.maximumSize(100);//Tamamho máximo de objetos no cache
		builder.expireAfterAccess(5, TimeUnit.MINUTES);//Tempo para expiração do cache após acesso
		
		GuavaCacheManager cacheManager = new GuavaCacheManager();//Gerenciador de Cache <Guava>. Usar @ConcurrentMapCacheManager apenas para teste
		cacheManager.setCacheBuilder(builder);
		
		return cacheManager();
	}
}
