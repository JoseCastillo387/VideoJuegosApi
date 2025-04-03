package mx.unam.dgtic.videojuegosapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Videojuego {

    private Long id;

    @NotBlank(message = "El titulo no puede estar vacio")
    private String titulo;
    @NotBlank(message = "La plataforma es obligatoria")
    private String plataforma;
    @Size(min = 3, message = "El genero debe tener al menos 3 caracteres")
    private String genero;

}
