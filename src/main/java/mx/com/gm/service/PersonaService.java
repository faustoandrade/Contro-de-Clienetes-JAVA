package mx.com.gm.service;

import java.util.List;
import mx.com.gm.domain.Persona;

public interface PersonaService {
    
    public List<Persona>listar();
    public void guardar(Persona persona);
    public void eliminar(Persona persona);
    public Persona encontrar(Persona persona);
    
}
