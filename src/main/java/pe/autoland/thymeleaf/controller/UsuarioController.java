package pe.autoland.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pe.autoland.web.model.Usuario;
import pe.autoland.web.repository.IEmpleadoRepository;
import pe.autoland.web.repository.IRolRepository;
import pe.autoland.web.service.UserService;

@Controller
public class UsuarioController {

	@Autowired
	private UserService usuService;
	@Autowired
	private IRolRepository rolRepo;
	@Autowired
	private IEmpleadoRepository empRepo;
	
	@GetMapping("/listadoUsuario")
	public String listadoUsuario (Model model) {
		if(LogController.ses == null) {
			return "redirect:/login";
		}
		System.out.println("Listar usuarios");	
		try {
			model.addAttribute("ltsUsu", usuService.listarUsuario());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listadoUsuario";
	}
	
	@GetMapping("/updInsUsuario")
	public String updInsUsuario(Model model) {
		System.out.println("Iniciando crear usuario");
		model.addAttribute("objUsuario", new Usuario());
		model.addAttribute("listaRoles", rolRepo.findAll());
		model.addAttribute("listaEmpleados", empRepo.findAll());
		return "updInsUsuario";
	}
	@PostMapping("/updInsUsuario")
	public String updInsUsuario(@ModelAttribute Usuario usuario, Model model) {
		System.out.println("Guardando usuario");
		usuService.guardarUsuario(usuario);
		return "redirect:/listadoUsuario";
	}
	@GetMapping("/updInsUsuario/{idusuario}")
	public String updInsUsuario(@PathVariable String idusuario, Model model) {
		System.out.println("Ejecutando actualizar usuario " + idusuario);
		model.addAttribute("listaRoles", rolRepo.findAll());
		model.addAttribute("listaEmpleados", empRepo.findAll());
		Usuario usuario = usuService.buscarUsuario(idusuario);
		model.addAttribute("objUsuario", usuario);
		return "updInsUsuario";
	}
	
	@GetMapping("/deleteUsuario/{idusuario}")
	public String deletePerson(@PathVariable String idusuario, Model model) {
		System.out.println("Ejecutando eliminar usuario " + idusuario);
		try {
			usuService.eliminarUsuario(idusuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listadoUsuario";
	}
}
