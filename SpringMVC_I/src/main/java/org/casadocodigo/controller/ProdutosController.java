package org.casadocodigo.controller;

import java.util.List;

import org.casadocodigo.dao.ProdutoDAO;
import org.casadocodigo.model.Produto;
import org.casadocodigo.model.TipoPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDao;
	
	@RequestMapping("/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		//TipoPreco.values();
		return modelAndView;
	}

	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView grava(Produto produto, RedirectAttributes redirectAttributes) {
		
		produtoDao.gravar(produto);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
		return new ModelAndView("redirect:produtos");

	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView lista() {
		
 		List<Produto> produtos = produtoDao.lista();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
}
