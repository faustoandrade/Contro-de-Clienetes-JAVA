package mx.com.gm.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data //Anotacion que reemplaza un java bin o sea las clases de tipo dominio (los getters and setter, to String .....)       
@Entity
@Table(name="persona")
public class Persona implements Serializable{
    
    private static final long serialVersionUID= 1L; 
    
    //Vamos agregar el mapeo de la llave primaria
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) //Forma de generar la llave primaria
    private Long idPersona;
    
    @NotEmpty //Validación de cadena vacia
    private String nombre;
    
    @NotEmpty
    private String apellido; 
    
    @NotEmpty
    @Email
    private String email;
   
    private String telefono;

    @NotNull //Validacion de tipo numerico no vacio 
    private Double saldo;
}
