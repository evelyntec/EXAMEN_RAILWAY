package com.pinturas.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pinturas")
public class Pintura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Por favor proporciona el título")
    @Size(min = 5, message = "El título debe tener al menos 5 caracteres")
    @Column(unique = true)
    private String titulo;

    @NotNull(message = "Por favor proporciona el año")
    @Positive(message = "El año debe ser un número positivo")
    private Integer anio;

    @NotEmpty(message = "Por favor proporciona la descripción")
    @Size(min = 10, message = "La descripción debe tener al menos 10 caracteres")
    @Column(length = 2000)
    private String descripcion;

    @NotEmpty(message = "Por favor proporciona una URL válida con la imagen")
    @Pattern(regexp = "^(https?://).+\\.(jpg|jpeg|png|gif|webp|bmp|svg)$",
             message = "Por favor proporciona una URL válida con la imagen")
    @Column(length = 1000)
    private String urlImagen;

    @NotNull(message = "Por favor proporciona la cantidad de copias")
    @Positive(message = "La cantidad debe ser un número positivo")
    private Integer cantidad;

    @NotNull(message = "Por favor proporciona el precio")
    @Positive(message = "El precio debe ser un número positivo")
    private Double precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creador_id")
    private Usuario creador;

    @OneToMany(mappedBy = "pintura", fetch = FetchType.LAZY)
    private List<Compra> compras = new ArrayList<>();

    public Pintura() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getUrlImagen() { return urlImagen; }
    public void setUrlImagen(String urlImagen) { this.urlImagen = urlImagen; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Usuario getCreador() { return creador; }
    public void setCreador(Usuario creador) { this.creador = creador; }

    public List<Compra> getCompras() { return compras; }
    public void setCompras(List<Compra> compras) { this.compras = compras; }
}