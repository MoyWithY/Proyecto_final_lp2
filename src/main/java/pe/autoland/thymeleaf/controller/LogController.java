package pe.autoland.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pe.autoland.web.model.Usuario;
import pe.autoland.web.service.UserService;

@Controller
public class LogController {
	
	public static String ses;

	@Autowired
	private UserService usuServ;
	
	@GetMapping({"/", "/login"})
	public String loginView(Model model) {
		System.out.println("Mostrando login");
		model.addAttribute("userLogin", new Usuario());
		return "login";
	}
	@PostMapping("/login")
	public String login(@ModelAttribute Usuario usuario, Model model) {
		System.out.println("Validando login");		
		String redirect = "login";
		Usuario u = usuServ.validateUserByNomAndPassword(usuario.getNomusuario(), usuario.getContrasenia());
		if (u != null) {
			System.out.println("Login exitoso");
			model.addAttribute("userLogin", u);
			ses = u.getRol().getNomRol();
			System.out.println(ses);
			
			switch(ses) {
				case "Admin":
					System.out.println("Home Admin");
					return "redirect:/homeAdmin";
                case "Gestor":
					System.out.println("Vista gestor");
					return "redirect:/listVehiPlus";
                case "Promotor":
					System.out.println("Promotor");
                    return "redirect:/listadoVehiculo";
			}
		} else {
			model.addAttribute("errors", "Usuario o clave incorrectos");
			System.out.println("Login fallido");
			model.addAttribute("userLogin", new Usuario());
		}
		return redirect;
	}
	
	@GetMapping("/logout")
		public String logout(Model model) {
			System.out.println("Cerrando sesión");
			model.addAttribute("userLogin", null);
			ses = null;
			return "redirect:/login";
	 }

	@GetMapping("/homeAdmin")
		public String homeAdmin(Model model) {
			if(ses == null) {
				return "redirect:/login";
			}
			return "homeAdmin";
	}
}
