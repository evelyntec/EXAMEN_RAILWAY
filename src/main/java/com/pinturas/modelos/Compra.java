package com.pinturas.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pintura_id")
    private Pintura pintura;

    public Compra() {}

    public Compra(Usuario usuario, Pintura pintura) {
        this.usuario = usuario;
        this.pintura = pintura;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Pintura getPintura() { return pintura; }
    public void setPintura(Pintura pintura) { this.pintura = pintura; }
}