package pe.autoland.thymeleaf.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.autoland.web.db.MySQLDataSource;
import pe.autoland.web.model.Empleado;
import pe.autoland.web.service.EmpleadoService;

@Controller
public class EmpleadoController {

	@Autowired
	private EmpleadoService empService;
	
	@GetMapping("/listadoEmpleado")
	public String listadoEmpleado (Model model) {
		if(LogController.ses == null) {
			return "redirect:/login";
		}
		System.out.println("Listar empleados");	
		try {
			model.addAttribute("ltsEmp", empService.listarEmpleado());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listadoEmpleado";
	}
	
	@GetMapping("/updInsEmpleado")
	public String updInsEmpleado(Model model) {
		System.out.println("Iniciando crear empleado");
		model.addAttribute("objEmpleado", new Empleado());
		return "updInsEmpleado";
	}
	@PostMapping("/updInsEmpleado")
	public String updInsEmpleado(@ModelAttribute Empleado empleado, Model model) {
		System.out.println("Guardando empleado");
		empService.guardarEmpleado(empleado);
		return "redirect:/listadoEmpleado";
	}
	@GetMapping("/updInsEmpleado/{idempleado}")
	public String updInsEmpleado(@PathVariable String idempleado, Model model) {
		System.out.println("Ejecutando actualizar empleado " + idempleado);
		Empleado empleado = empService.buscarEmpleado(idempleado);
		model.addAttribute("objEmpleado", empleado);
		return "updInsEmpleado";
	}
	
	@GetMapping("/deleteEmpleado/{idempleado}")
	public String deleteEmpleado(@PathVariable String idempleado, Model model) throws Exception {
		System.out.println("Ejecutando eliminar empleado " + idempleado);
		try {
			this.empService.eliminarEmpleado(idempleado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listadoEmpleado";
	}
	
	@RequestMapping(value = "/EmpleadoReport", method = RequestMethod.GET)
	public void personaReporte(HttpServletResponse response) throws JRException, IOException {
		System.out.println("Generando reporte de empleados");
		InputStream jasperStream = this.getClass().getResourceAsStream("/reporte/Leaf_Red.jasper");
		System.out.println(jasperStream);
		Map<String, Object> params = new HashMap<String, Object>();
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, MySQLDataSource.getMySQLConnection());
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=empleado-report.pdf");
		final OutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}
}
