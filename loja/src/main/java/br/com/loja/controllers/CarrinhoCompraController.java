package br.com.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.loja.dao.ProdutoDAO;
import br.com.loja.models.CarrinhoCompras;
import br.com.loja.models.CarrinhoItem;
import br.com.loja.models.Produto;
import br.com.loja.models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoCompraController {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private CarrinhoCompras carrinho;

	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco){
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
		carrinho.add(carrinhoItem);
		
		return modelAndView;		
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens(){
		return new ModelAndView("/carrinho/itens");
	}
	
	@RequestMapping(value="/remover", method=RequestMethod.POST)
	public ModelAndView remover(Integer produtoId, TipoPreco tipoPreco){
		carrinho.remover(produtoId, tipoPreco);
		 
		return new ModelAndView("redirect:/carrinho");
	}
	
	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDAO.find(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);
		
		return carrinhoItem;
	}
	
	
	
	

}
