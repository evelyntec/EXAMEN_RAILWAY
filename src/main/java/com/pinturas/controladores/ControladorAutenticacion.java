package com.pinturas.controladores;

import com.pinturas.modelos.Usuario;
import com.pinturas.servicios.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorAutenticacion {

    private final ServicioUsuario servicioUsuario;

    public ControladorAutenticacion(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    @GetMapping("/")
    public String raiz(HttpSession sesion) {
        if (sesion.getAttribute("usuarioId") == null) {
            return "redirect:/login";
        }
        return "redirect:/pinturas";
    }

    @GetMapping("/registro")
    public String formularioRegistro(@ModelAttribute("usuario") Usuario usuario) {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(@Valid @ModelAttribute("usuario") Usuario usuario,
                            BindingResult resultado, HttpSession sesion) {
        Usuario nuevo = servicioUsuario.registrar(usuario, resultado);
        if (resultado.hasErrors() || nuevo == null) {
            return "registro";
        }
        sesion.setAttribute("usuarioId", nuevo.getId());
        return "redirect:/pinturas";
    }

    @GetMapping("/login")
    public String formularioLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String correo,
                        @RequestParam String password,
                        Model modelo, HttpSession sesion) {
        Usuario usuario = servicioUsuario.iniciarSesion(correo, password);
        if (usuario == null) {
            modelo.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
        sesion.setAttribute("usuarioId", usuario.getId());
        return "redirect:/pinturas";
    }

    @GetMapping("/logout")
    public String logout(HttpSession sesion) {
        sesion.invalidate();
        return "redirect:/login";
    }
}