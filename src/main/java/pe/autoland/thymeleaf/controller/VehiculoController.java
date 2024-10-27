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
import pe.autoland.web.model.Vehiculo;
import pe.autoland.web.repository.IAutomovilRepository;

@Controller
public class VehiculoController {
	
	@Autowired
	private IAutomovilRepository autoRepo;
	
	@GetMapping("/listadoVehiculo")
	public String listadoVehiculo (Model model) {
		if(LogController.ses == null) {
			return "redirect:/login";
		}
		System.out.println("Listar vehículos");	
		try {
			model.addAttribute("ltsAuto", autoRepo.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listadoVehiculo";
	}
	
	@GetMapping("/listVehiPlus")
	public String listVehiPlus (Model model) {
		if(LogController.ses == null) {
			return "redirect:/login";
		}
		model.addAttribute("sesion", LogController.ses);
		System.out.println("Listar vehículos");	
		try {
			model.addAttribute("ltsAuto", autoRepo.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listVehiPlus";
	}
	
	@GetMapping("/updInsVehiculo")
	public String updInsVehiculo(Model model) {
		System.out.println("Iniciando crear vehiculo");
		model.addAttribute("objVehiculo", new Vehiculo());
		return "updInsVehiculo";
	}
	@PostMapping("/updInsVehiculo")
	public String updInsVehiculo(@ModelAttribute Vehiculo vehiculo, Model model) {
		System.out.println("Guardando vehiculo");
		autoRepo.save(vehiculo);
		return "redirect:/listVehiPlus";
	}
	@GetMapping("/updInsVehiculo/{idvehiculo}")
	public String updInsVehiculo(@PathVariable String idvehiculo, Model model) {
		System.out.println("Ejecutando actualizar vehiculo " + idvehiculo);
		Vehiculo vehiculo = autoRepo.getById(Integer.parseInt(idvehiculo));
		model.addAttribute("objVehiculo", vehiculo);
		return "updInsVehiculo";
	}
	
	@GetMapping("/deleteVehiculo/{idvehiculo}")
	public String deletePerson(@PathVariable String idvehiculo, Model model) {
		System.out.println("Ejecutando eliminar vehiculo " + idvehiculo);
		try {
			autoRepo.deleteById(Integer.parseInt(idvehiculo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listVehiPlus";
	}
	
	@RequestMapping(value = "/VehiculoReport", method = RequestMethod.GET)
	public void personaReporte(HttpServletResponse response) throws JRException, IOException {
		System.out.println("Generando reporte de empleados");
		InputStream jasperStream = this.getClass().getResourceAsStream("/reporte/Simple_Blue.jasper");
		System.out.println(jasperStream);
		Map<String, Object> params = new HashMap<String, Object>();
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, MySQLDataSource.getMySQLConnection());
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=vehiculo-report.pdf");
		final OutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}
}
