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

import com.cupdash.model.Funcionario;
import com.cupdash.service.FuncionarioI;

@Controller
@RequestMapping(path = "/")
public class GUIFuncionarioController {
    @Autowired
    FuncionarioI servico;

    @GetMapping("/funcionarios")
	public String listFuncionarios(Model model) {
		List<Funcionario> listFuncionarios = servico.obtemFuncionarios();
		model.addAttribute("listFuncionarios", listFuncionarios);
		return "funcionarios";
	}

    @GetMapping("/funcionario")
	public String formFuncionario(Model model) {
        Funcionario funcionario = new Funcionario();
        model.addAttribute("funcionario",funcionario);
		return "funcionario_form";
	}
    
    @PostMapping("/saveFuncionario")
	public ModelAndView save(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("funcionarios");
		if (result.hasErrors()) {
			mv.addObject("funcionario", funcionario);
			mv.setViewName("funcionario_form");
		} else {
			servico.save(funcionario);
			mv.setViewName("administrativo/funcionarios");
			redirectAttributes.addFlashAttribute("message", "Usuário criado com sucesso!");
			mv.setViewName("redirect:/funcionarios");
		}
		return mv;
	}
    
    @GetMapping("/alteraFunc/{id}")
	public String viewFuncionarioUpdate(@PathVariable("id") int id, Model model) {
        Funcionario funcionario = new Funcionario();
		funcionario = servico.getById(id);
        model.addAttribute("funcionario", funcionario);
		return "funcionario_update";
	}
    
    @PostMapping("/funcionarios/update/{id}")
	public ModelAndView funcionarioUpdate(@PathVariable("id") int id, @Valid Funcionario funcionario, BindingResult result, RedirectAttributes redirectAttributes){
    	ModelAndView mv = new ModelAndView("funcionarios");
    	if (result.hasErrors()) {
    		funcionario.setId(id);
    		mv.addObject("funcionario",funcionario);
   			mv.setViewName("funcionario_update");
   			
   		} else {
   			servico.update(funcionario, id);
   			redirectAttributes.addFlashAttribute("message", "Funcionario alterado com sucesso!");
   			mv.setViewName("redirect:/funcionarios");
   		}
   		return mv;
	}

    @GetMapping("/funcionarios/delete/{id}")
	public String deleteFuncionario(@PathVariable(name = "id") int id,
			Model model,
			RedirectAttributes redirectAttributes){
    	
    	servico.delete(id);
    	redirectAttributes.addFlashAttribute("message", "O funcionário com ID " + id + " foi excluído com sucesso!" );
		return "redirect:/funcionarios";
	}
    
    

}
