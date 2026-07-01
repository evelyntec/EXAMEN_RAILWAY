package com.pinturas.repositorios;

import com.pinturas.modelos.Usuario;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface RepositorioUsuario extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
}