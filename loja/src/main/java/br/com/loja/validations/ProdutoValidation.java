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
	public void validate(Object obj, Errors errors) {

		Produto produto = (Produto) obj;
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
		//ValidationUtils.rejectIfEmpty(errors, "data", "field.required");
		
		if(produto.getPaginas() <=0){
			errors.rejectValue("paginas", "field.required");
		}
	
		
	}
	

}
