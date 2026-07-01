package com.pinturas.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Por favor proporciona tu nombre")
    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    private String nombre;

    @NotEmpty(message = "Por favor proporciona tu apellido")
    @Size(min = 3, message = "El apellido debe tener al menos 3 caracteres")
    private String apellido;

    @NotEmpty(message = "Por favor ingresa un correo válido")
    @Email(message = "Por favor ingresa un correo válido")
    @Column(unique = true)
    private String correo;

    @NotEmpty(message = "Por favor ingresa una contraseña")
    @Size(min = 8, message = "La contraseña necesita tener al menos 8 caracteres")
    private String password;

    @Transient
    private String confirmarPassword;

    @OneToMany(mappedBy = "creador", fetch = FetchType.LAZY)
    private List<Pintura> pinturasCreadas;

    public Usuario() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getConfirmarPassword() { return confirmarPassword; }
    public void setConfirmarPassword(String confirmarPassword) { this.confirmarPassword = confirmarPassword; }

    public List<Pintura> getPinturasCreadas() { return pinturasCreadas; }
    public void setPinturasCreadas(List<Pintura> pinturasCreadas) { this.pinturasCreadas = pinturasCreadas; }
}