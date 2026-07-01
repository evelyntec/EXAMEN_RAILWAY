package com.pinturas.repositorios;

import com.pinturas.modelos.Compra;
import com.pinturas.modelos.Pintura;
import com.pinturas.modelos.Usuario;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface RepositorioCompra extends CrudRepository<Compra, Long> {
    List<Compra> findByUsuario(Usuario usuario);
    List<Compra> findByPintura(Pintura pintura);
}