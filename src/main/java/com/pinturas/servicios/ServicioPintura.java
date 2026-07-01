package com.pinturas.servicios;

import com.pinturas.modelos.Pintura;
import com.pinturas.repositorios.RepositorioPintura;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicioPintura {

    private final RepositorioPintura repositorioPintura;

    public ServicioPintura(RepositorioPintura repositorioPintura) {
        this.repositorioPintura = repositorioPintura;
    }

    public List<Pintura> obtenerTodas() {
        return repositorioPintura.findAllByOrderByTituloAsc();
    }

    public Pintura buscarPorId(Long id) {
        return repositorioPintura.findById(id).orElse(null);
    }

    public Pintura guardar(Pintura pintura) {
        return repositorioPintura.save(pintura);
    }

    public void eliminar(Long id) {
        repositorioPintura.deleteById(id);
    }

    public boolean tituloDuplicado(String titulo, Long idActual) {
        Pintura existente = repositorioPintura.findByTitulo(titulo);
        return existente != null && !existente.getId().equals(idActual);
    }
}