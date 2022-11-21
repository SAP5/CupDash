package com.cupdash.controle;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cupdash.model.Categoria;
import com.cupdash.model.Cor;
import com.cupdash.model.Modelo;
import com.cupdash.model.Produto;
import com.cupdash.model.ProdutoDTO;
import com.cupdash.model.Tamanho;
import com.cupdash.service.CategoriaI;
import com.cupdash.service.CorI;
import com.cupdash.service.ProdutoI;
import com.cupdash.service.TamanhoI;

@Controller
@RequestMapping(path = "/")
public class GUIProdutoController {

    @Autowired
    ProdutoI servico;
    @Autowired
    CorI servicoCor;
    @Autowired
    TamanhoI servicoTam;
    @Autowired
    CategoriaI servicoCat;
   

    @GetMapping("/produtos")
	public String listProdutos(Model model) {
		List<ProdutoDTO> listProdutos = servico.obtemProdutos();
		model.addAttribute("listProdutos", listProdutos);
		return "produtos";
	}

    @GetMapping("/produtoCad")
	public String formProduto(Model model) {
    	Produto produto = new Produto();
        List<Cor> listCor = servicoCor.obtemCores();
        List<Tamanho> listTamanho = servicoTam.obtemTamanhos();
        List<Categoria> listCategoria = servicoCat.obtemCategoria();
        List<Modelo> listM = servico.obtemModelos();
        
        model.addAttribute("produto",produto);
		model.addAttribute("listCor", listCor);
		model.addAttribute("listTamanho", listTamanho);
		model.addAttribute("listM", listM);
		model.addAttribute("listCategoria", listCategoria);
        
		return "produto_form";
	}

    
    @PostMapping("/saveProduto")
   	public ModelAndView save(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {
   		ModelAndView mv = new ModelAndView("produtos");
   		List<Cor> listCor = servicoCor.obtemCores();
   		List<Tamanho> listTamanho = servicoTam.obtemTamanhos();
   		List<Categoria> listCategoria = servicoCat.obtemCategoria();
   		if (result.hasErrors()) {
   			mv.addObject("produto", produto);
   			mv.addObject("produto",produto);
   			mv.addObject("listCor", listCor);
   			mv.addObject("listTamanho", listTamanho);
   			mv.addObject("listCategoria", listCategoria);
   			mv.setViewName("produto_form");
   		} else {
   			servico.save(produto);
   			mv.setViewName("produtos");
   			redirectAttributes.addFlashAttribute("message", "Produto criado com sucesso!");
   			mv.setViewName("redirect:/produtos");
   		}
   		return mv;
   	}
    
    @GetMapping("/alteraProd/{id}")
	public ModelAndView viewProdutoUpdate(@PathVariable("id") int id) {
    	ModelAndView mv = new ModelAndView("produto_update");
    	ProdutoDTO produto = new ProdutoDTO();
		produto = servico.getById(id);
		
		List<Cor> listCor = servicoCor.obtemCores();
        List<Tamanho> listTamanho = servicoTam.obtemTamanhos();
        List<Categoria> listCategoria = servicoCat.obtemCategoria();
        List<Modelo> listM = servico.obtemModelos();
        
        mv.addObject("produto",produto);
		mv.addObject("listCor", listCor);
		mv.addObject("listTamanho", listTamanho);
		mv.addObject("listCategoria", listCategoria);
		mv.addObject("listM", listM);
		return mv;
	}
    
    @PostMapping("/produtos/update/{id}")
	public ModelAndView updateFuncionario(@PathVariable("id") int id, @Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes){
    	ModelAndView mv = new ModelAndView("produtos");
    	List<Cor> listCor = servicoCor.obtemCores();
   		List<Tamanho> listTamanho = servicoTam.obtemTamanhos();
   		List<Categoria> listCategoria = servicoCat.obtemCategoria();
   		List<Modelo> listM = servico.obtemModelos();
   		
    	if (result.hasErrors()) {
    		produto.setId(id);
    		mv.addObject("produto",produto);
    		mv.addObject("listCor", listCor);
    		mv.addObject("listTamanho", listTamanho);
    		mv.addObject("listCategoria", listCategoria);
    		mv.addObject("listM", listM);
   			mv.setViewName("produto_update");
   			
   		} else {
   			servico.update(produto, id);
   			redirectAttributes.addFlashAttribute("message", "Produto alterado com sucesso!");
   			mv.setViewName("redirect:/produtos");
   		}
   		return mv;
	}
    
    @GetMapping("/produtos/delete/{id}")
	public String deleteCliente(@PathVariable("id") int id,
			Model model,
			RedirectAttributes redirectAttributes){
    	
		servico.delete(id);
		redirectAttributes.addFlashAttribute("message", "O produto de ID " + id + " foi excluído com sucesso!" );
		return "redirect:/produtos";
	}

   
}
