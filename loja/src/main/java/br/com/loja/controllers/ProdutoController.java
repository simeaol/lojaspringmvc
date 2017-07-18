package br.com.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.loja.dao.ProdutoDAO;
import br.com.loja.models.Produto;
import br.com.loja.models.TipoPreco;
import br.com.loja.validations.ProdutoValidation;

@Controller
//@RequestMapping("produtos")
public class ProdutoController {

	@Autowired
	private ProdutoDAO produtoDao;
	
	ProdutoValidation ValidationUtils;

	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		System.out.println("Exibindo o formulário.");
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		return modelAndView;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new ProdutoValidation());		
	}

	@RequestMapping(value = "/produtos", method=RequestMethod.POST)
	public ModelAndView save(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()){
			return form(produto);
			//return new ModelAndView("produtos/form");
		}
		produtoDao.save(produto);		
		redirectAttributes.addFlashAttribute("status", "Produto cadastrado com sucesso");
		return new ModelAndView("redirect:produtos");
	}
	
	@RequestMapping(value="/produtos", method=RequestMethod.GET)
	public ModelAndView show(){
		ModelAndView view = new ModelAndView("produtos/lista");
		List<Produto> listaProdutos = produtoDao.listAll();
		view.addObject("produtos", listaProdutos);
		return view;
		
	}

}
