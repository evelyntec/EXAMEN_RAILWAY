package com.pinturas.controladores;

import com.pinturas.modelos.Pintura;
import com.pinturas.modelos.Usuario;
import com.pinturas.servicios.ServicioCompra;
import com.pinturas.servicios.ServicioPintura;
import com.pinturas.servicios.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorPintura {

    private final ServicioPintura servicioPintura;
    private final ServicioUsuario servicioUsuario;
    private final ServicioCompra servicioCompra;

    public ControladorPintura(ServicioPintura servicioPintura, ServicioUsuario servicioUsuario,
                              ServicioCompra servicioCompra) {
        this.servicioPintura = servicioPintura;
        this.servicioUsuario = servicioUsuario;
        this.servicioCompra = servicioCompra;
    }

    private Usuario usuarioActual(HttpSession sesion) {
        Long id = (Long) sesion.getAttribute("usuarioId");
        if (id == null) {
            return null;
        }
        return servicioUsuario.buscarPorId(id);
    }

    @GetMapping("/pinturas")
    public String index(HttpSession sesion, Model modelo) {
        Usuario actual = usuarioActual(sesion);
        if (actual == null) {
            return "redirect:/login";
        }
        modelo.addAttribute("usuario", actual);
        modelo.addAttribute("pinturas", servicioPintura.obtenerTodas());
        return "index";
    }

    @GetMapping("/pinturas/{id}")
    public String detalle(@PathVariable Long id, HttpSession sesion, Model modelo) {
        Usuario actual = usuarioActual(sesion);
        if (actual == null) {
            return "redirect:/login";
        }
        Pintura pintura = servicioPintura.buscarPorId(id);
        if (pintura == null) {
            return "redirect:/pinturas";
        }
        modelo.addAttribute("usuario", actual);
        modelo.addAttribute("pintura", pintura);
        modelo.addAttribute("compradores", servicioCompra.compradoresDe(pintura));
        return "detalle";
    }

    @GetMapping("/pinturas/nueva")
    public String formularioNueva(@ModelAttribute("pintura") Pintura pintura,
                                  HttpSession sesion, Model modelo) {
        Usuario actual = usuarioActual(sesion);
        if (actual == null) {
            return "redirect:/login";
        }
        modelo.addAttribute("usuario", actual);
        return "agregar";
    }

    @PostMapping("/pinturas/nueva")
    public String crear(@Valid @ModelAttribute("pintura") Pintura pintura,
                        BindingResult resultado, HttpSession sesion, Model modelo) {
        Usuario actual = usuarioActual(sesion);
        if (actual == null) {
            return "redirect:/login";
        }
        if (pintura.getTitulo() != null && servicioPintura.tituloDuplicado(pintura.getTitulo(), 0L)) {
            resultado.rejectValue("titulo", "Coincide", "El título ya existe, debe ser único");
        }
        if (resultado.hasErrors()) {
            modelo.addAttribute("usuario", actual);
            return "agregar";
        }
        pintura.setCreador(actual);
        servicioPintura.guardar(pintura);
        return "redirect:/pinturas";
    }

    @GetMapping("/pinturas/{id}/editar")
    public String formularioEditar(@PathVariable Long id, HttpSession sesion, Model modelo) {
        Usuario actual = usuarioActual(sesion);
        if (actual == null) {
            return "redirect:/login";
        }
        Pintura pintura = servicioPintura.buscarPorId(id);
        if (pintura == null) {
            return "redirect:/pinturas";
        }
        modelo.addAttribute("usuario", actual);
        modelo.addAttribute("pintura", pintura);
        return "editar";
    }

    @PostMapping("/pinturas/{id}/editar")
    public String actualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("pintura") Pintura pintura,
                            BindingResult resultado, HttpSession sesion, Model modelo) {
        Usuario actual = usuarioActual(sesion);
        if (actual == null) {
            return "redirect:/login";
        }
        if (pintura.getTitulo() != null && servicioPintura.tituloDuplicado(pintura.getTitulo(), id)) {
            resultado.rejectValue("titulo", "Coincide", "El título ya existe, debe ser único");
        }
        if (resultado.hasErrors()) {
            pintura.setId(id);
            modelo.addAttribute("usuario", actual);
            return "editar";
        }
        Pintura original = servicioPintura.buscarPorId(id);
        pintura.setId(id);
        pintura.setCreador(original.getCreador());
        servicioPintura.guardar(pintura);
        return "redirect:/pinturas";
    }

    @PostMapping("/pinturas/{id}/eliminar")
    public String eliminar(@PathVariable Long id, HttpSession sesion) {
        Usuario actual = usuarioActual(sesion);
        if (actual == null) {
            return "redirect:/login";
        }
        Pintura pintura = servicioPintura.buscarPorId(id);
        if (pintura != null && pintura.getCreador() != null
                && pintura.getCreador().getId().equals(actual.getId())) {
            servicioPintura.eliminar(id);
        }
        return "redirect:/pinturas";
    }

    @PostMapping("/pinturas/{id}/comprar")
    public String comprar(@PathVariable Long id, HttpSession sesion) {
        Usuario actual = usuarioActual(sesion);
        if (actual == null) {
            return "redirect:/login";
        }
        Pintura pintura = servicioPintura.buscarPorId(id);
        if (pintura != null) {
            servicioCompra.comprar(actual, pintura);
        }
        return "redirect:/compras";
    }

    @GetMapping("/compras")
    public String compras(HttpSession sesion, Model modelo) {
        Usuario actual = usuarioActual(sesion);
        if (actual == null) {
            return "redirect:/login";
        }
        modelo.addAttribute("usuario", actual);
        modelo.addAttribute("pinturas", servicioCompra.comprasDe(actual));
        return "compras";
    }
}