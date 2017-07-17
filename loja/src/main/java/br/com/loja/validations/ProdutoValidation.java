package br.com.loja.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.loja.models.Produto;

public class ProdutoValidation implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors erros) {

		Produto produto = (Produto) obj;
		ValidationUtils.rejectIfEmpty(erros, "nome", "field.riquired");
		ValidationUtils.rejectIfEmpty(erros, "descricao", "field.riquired");
		
		if(produto.getPaginas() <=0){
			erros.rejectValue("paginas", "field.riquired");
		}
	
		
	}
	

}
