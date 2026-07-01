package com.pinturas.servicios;

import com.pinturas.modelos.Usuario;
import com.pinturas.repositorios.RepositorioUsuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.Optional;

@Service
public class ServicioUsuario {

    private final RepositorioUsuario repositorioUsuario;
    private final BCryptPasswordEncoder codificador;

    public ServicioUsuario(RepositorioUsuario repositorioUsuario, BCryptPasswordEncoder codificador) {
        this.repositorioUsuario = repositorioUsuario;
        this.codificador = codificador;
    }

    public Usuario registrar(Usuario usuario, BindingResult resultado) {
        if (repositorioUsuario.findByCorreo(usuario.getCorreo()).isPresent()) {
            resultado.rejectValue("correo", "Coincide", "El correo ya existe en la base de datos");
        }
        if (usuario.getPassword() != null && !usuario.getPassword().equals(usuario.getConfirmarPassword())) {
            resultado.rejectValue("confirmarPassword", "Coincide", "Las contraseñas no coinciden");
        }
        if (resultado.hasErrors()) {
            return null;
        }
        usuario.setPassword(codificador.encode(usuario.getPassword()));
        return repositorioUsuario.save(usuario);
    }

    public Usuario iniciarSesion(String correo, String password) {
        Optional<Usuario> posible = repositorioUsuario.findByCorreo(correo);
        if (posible.isEmpty()) {
            return null;
        }
        Usuario usuario = posible.get();
        if (codificador.matches(password, usuario.getPassword())) {
            return usuario;
        }
        return null;
    }

    public Usuario buscarPorId(Long id) {
        return repositorioUsuario.findById(id).orElse(null);
    }
}