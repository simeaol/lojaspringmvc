package br.com.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.loja.dao.ProdutoDAO;
import br.com.loja.infra.FileSaver;
import br.com.loja.models.Produto;
import br.com.loja.models.TipoPreco;
import br.com.loja.validations.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoDAO produtoDao;
	
	ProdutoValidation ValidationUtils;
	
	@Autowired
	private FileSaver fileSaver;

	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		return modelAndView;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new ProdutoValidation());		
	}

	@RequestMapping(method=RequestMethod.POST)
	@CacheEvict(value="produtosHome", allEntries=true)
	public ModelAndView save(MultipartFile sumario ,@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {
		System.out.println("File name: "+sumario.getOriginalFilename());
		/*if(result.hasErrors()){
			return form(produto);
			//return new ModelAndView("produtos/form");
		}*/
		String path = fileSaver.write("arquivos-sumario", sumario);
		produto.setSumario(path);
		
		produtoDao.save(produto);	
		
		redirectAttributes.addFlashAttribute("status", "Produto cadastrado com sucesso");
		return new ModelAndView("redirect:produtos");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView show(){
		ModelAndView view = new ModelAndView("produtos/lista");
		List<Produto> listaProdutos = produtoDao.findAll();
		view.addObject("produtos", listaProdutos);
		return view;
		
	}
	
	@RequestMapping(value="/detalhe/{id}", method=RequestMethod.GET)
	public ModelAndView detalhe(@PathVariable("id") Integer id){
		ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
		Produto produto = produtoDao.find(id);
		modelAndView.addObject("produto", produto);
		return modelAndView;
	}

}
