package br.com.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.loja.models.CarrinhoCompras;
import br.com.loja.models.PaymentData;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinho;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public ModelAndView finalizar(RedirectAttributes attributes) {

		String url = "http://book-payment.herokuapp.com/payment";
		String response = null;
		try {
			response = restTemplate.postForObject(url, new PaymentData(carrinho.getTotal()), String.class);
			System.out.println("" + carrinho.getTotal());
			attributes.addFlashAttribute("status", response);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			attributes.addFlashAttribute("status", "\t Valor maior que o permitido !");
			new ModelAndView("redirect:/produtos");
		}


		return new ModelAndView("redirect:/produtos");
	}
}
