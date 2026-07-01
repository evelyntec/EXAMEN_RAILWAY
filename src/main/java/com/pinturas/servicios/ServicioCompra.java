package com.pinturas.servicios;

import com.pinturas.modelos.Compra;
import com.pinturas.modelos.Pintura;
import com.pinturas.modelos.Usuario;
import com.pinturas.repositorios.RepositorioCompra;
import com.pinturas.repositorios.RepositorioPintura;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioCompra {

    private final RepositorioCompra repositorioCompra;
    private final RepositorioPintura repositorioPintura;

    public ServicioCompra(RepositorioCompra repositorioCompra, RepositorioPintura repositorioPintura) {
        this.repositorioCompra = repositorioCompra;
        this.repositorioPintura = repositorioPintura;
    }

    public void comprar(Usuario usuario, Pintura pintura) {
        if (pintura.getCantidad() != null && pintura.getCantidad() > 0) {
            pintura.setCantidad(pintura.getCantidad() - 1);
            repositorioPintura.save(pintura);
            repositorioCompra.save(new Compra(usuario, pintura));
        }
    }

    public List<Pintura> comprasDe(Usuario usuario) {
        return repositorioCompra.findByUsuario(usuario)
                .stream()
                .map(Compra::getPintura)
                .collect(Collectors.toList());
    }

    public List<Usuario> compradoresDe(Pintura pintura) {
        return repositorioCompra.findByPintura(pintura)
                .stream()
                .map(Compra::getUsuario)
                .distinct()
                .collect(Collectors.toList());
    }
}