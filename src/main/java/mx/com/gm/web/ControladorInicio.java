package mx.com.gm.web;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//Para que Spring reconozca esta clase se agregará la anotacion (@Controller)
//La agregará al contenedor de Spring y asi vamos a poder utilizar esta clase detro del frameWork
@Controller //Anotación de tipo spring MVC
@Slf4j  //Anotación para el manejo de logging
public class ControladorInicio {

    @Autowired
    private PersonaService personaService;   //Inyectamos la instancia de la clase personaServiceImpl

    //Vamos a indicarle al navegador que este metodo es el que se debe de ejecutar
    //lo mapeamos a un path.
    @GetMapping("/") //Responde a la ruta de inicio http://localhost:8080
    public String inicio(Model model, @AuthenticationPrincipal User user) {

        var personas = personaService.listar();
        log.info("Ejecutando el controlador de tipo Spring MVC");
        log.info("Usuario que hizo Login: " + user);
        model.addAttribute("personas", personas);
        var saldoTotal = 0D;
        for(var p: personas){
            saldoTotal += p.getSaldo();
        }
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());
        return "index"; //llevará el mismo nombre de la pagina html que se creó
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona) {
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errors) { //Validaciones con la anotacion @Valid
        if (errors.hasErrors()) {
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model) {
        persona = personaService.encontrar(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }

    @GetMapping("/eliminar")
    public String eliminar(Persona persona) {
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
