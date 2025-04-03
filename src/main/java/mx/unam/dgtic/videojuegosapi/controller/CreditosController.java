package mx.unam.dgtic.videojuegosapi.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/creditos")
public class CreditosController {

    @GetMapping
    public String mostrarCreditos(Model model) {
        model.addAttribute("nombre", "José de Jesús Castillo Nolasco");
        return "creditos";
    }
}
