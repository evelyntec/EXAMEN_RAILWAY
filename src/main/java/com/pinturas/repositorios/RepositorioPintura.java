package com.pinturas.repositorios;

import com.pinturas.modelos.Pintura;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface RepositorioPintura extends CrudRepository<Pintura, Long> {
    List<Pintura> findAllByOrderByTituloAsc();
    Pintura findByTitulo(String titulo);
}