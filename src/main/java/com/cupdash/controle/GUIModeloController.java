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

import com.cupdash.model.Modelo;
import com.cupdash.service.ModeloI;

@Controller
@RequestMapping(path = "/")
public class GUIModeloController {
	@Autowired
    ModeloI servico;

    @GetMapping("/modelos")
	public String listModelos(Model model) {
		List<Modelo> listModelos = servico.obtemModelos();
		model.addAttribute("listModelos", listModelos);
		return "modelos";
	}
    
    @GetMapping("/modeloCad")
	public String formModelo(Model model) {
        Modelo modelo = new Modelo();
        model.addAttribute("modelo", modelo);
		return "modelo_form";
	}
    
    @PostMapping("/saveModelo")
	public ModelAndView save(@Valid Modelo modelo, BindingResult result, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("modelos");
		if (result.hasErrors()) {
			mv.addObject("modelo", modelo);
			mv.setViewName("modelo_form");
		} else {
			servico.save(modelo);
			mv.setViewName("modelos");
			redirectAttributes.addFlashAttribute("message", "Modelo criado com sucesso!");
			mv.setViewName("redirect:/modelos");
		}
		return mv;
	}
    
    @GetMapping("/alteraMod/{id}")
	public String viewModeloUpdate(@PathVariable("id") int id, Model model) {
    	Modelo modelo = new Modelo();
		modelo = servico.getById(id);
        model.addAttribute("modelo", modelo);
		return "modelo_update";
	}
    
    @PostMapping("/modelos/update/{id}")
	public ModelAndView modeloUpdate(@PathVariable("id") int id, @Valid Modelo modelo, BindingResult result, RedirectAttributes redirectAttributes){
    	ModelAndView mv = new ModelAndView("modelos");
    	if (result.hasErrors()) {
    		modelo.setId(id);
    		mv.addObject("modelo",modelo);
   			mv.setViewName("modelo_update");
   			
   		} else {
   			servico.update(modelo, id);
   			redirectAttributes.addFlashAttribute("message", "Modelo alterado com sucesso!");
   			mv.setViewName("redirect:/modelos");
   		}
   		return mv;
	}
    
    @GetMapping("/modelos/delete/{id}")
	public String deleteModelo(@PathVariable(name = "id") int id,
			Model model,
			RedirectAttributes redirectAttributes){
    	servico.delete(id);
    	redirectAttributes.addFlashAttribute("message", "O modelo com ID " + id + " foi excluído com sucesso!" );
		return "redirect:/modelos";
	}
}
