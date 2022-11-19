package com.cupdash.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cupdash.export.ClientePdfExporter;
import com.cupdash.model.Cliente;
import com.cupdash.service.ClienteI;

@Controller
@RequestMapping(path = "/")
public class ADMClienteController {
    @Autowired
    ClienteI servico;

	//CLIENTES
    @GetMapping("/clientes")
	public String listClientes(Model model) {
		List<Cliente> listClientes = servico.obtemClientes();
		model.addAttribute("listClientes", listClientes); 
		return "clientes";
	}

	@GetMapping("/clientes/delete/{id}")
	public String deleteCliente(@PathVariable("id") int id,
			Model model,
			RedirectAttributes redirectAttributes){
		
		servico.delete(id);
		redirectAttributes.addFlashAttribute("message", "O cliente de ID " + id + " foi excluído com sucesso!" );
		return "redirect:/clientes";
	}

	@GetMapping("/alteraCli/{id}")
	public String viewCliente(@PathVariable("id") int id, Model model) {
        Cliente cliente = new Cliente();
		cliente = servico.getById(id);
        model.addAttribute("cliente", cliente);
		return "cliente_update";
	}

	@PostMapping("/clientes/update/{id}")
	public String updateCliente(@PathVariable("id") int id, Cliente cliente){
		servico.update(cliente, id);
		System.out.println("passou");
		return "redirect:/clientes";
	}
	
	@GetMapping("/clientesPdf")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		List<Cliente> listClientes = servico.obtemClientes();
		ClientePdfExporter exporter = new ClientePdfExporter();
		exporter.export(listClientes, response);
	}
	
	
	//FIM CLIENTE
}
