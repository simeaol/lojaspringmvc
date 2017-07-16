package br.com.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.loja.dao.ProdutoDAO;
import br.com.loja.models.Produto;
import br.com.loja.models.TipoPreco;

@Controller
//@RequestMapping("produtos")
public class ProdutoController {

	@Autowired
	private ProdutoDAO produtoDao;

	@RequestMapping("/form")
	public ModelAndView form() {
		System.out.println("Exibindo o formulário.");
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		return modelAndView;
	}

	@RequestMapping(value = "/produtos", method=RequestMethod.POST)
	public ModelAndView save(Produto produto) {
		System.out.println(produto);
		produtoDao.save(produto);
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
