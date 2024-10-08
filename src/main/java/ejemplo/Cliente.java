package ejemplo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cliente {
    
    private int id;
    private String nombre;
    private String dni;
    private String email;
    private String telefono;
    

    public Cliente(int id, String nombre, String dni, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
    }

    public Cliente(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return id + "," + nombre + "," + dni + "," + email + ","
                + telefono ;
    }

   

    
}
