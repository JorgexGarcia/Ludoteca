package Ludoteca.modelos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Alumno {

    //Variables static
    public static final int MAXPRESTAMOS = 3;
    public static final int PENAPRESTAMOS = 2;

    //Variables
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private int sancion;
    private int numPrestamos;
    private int id;

    //Constructores
    public Alumno(String nombre, String apellidos, String email, String telefono) {
        setNombre(nombre);
        setApellidos(apellidos);
        setEmail(email);
        setTelefono(telefono);
        this.sancion = 0;
        this.id = -1;
        setNumPrestamos(0);
    }

    public Alumno() {
        this.nombre = "";
        this.apellidos = "";
        this.email = "";
        this.telefono = "";
        this.sancion = 0;
        this.id = -1;
        numPrestamos = 0;
    }

    //Set y Get
    public int getNumPrestamos() {
        return numPrestamos;
    }

    public void setNumPrestamos(int num) {
        this.numPrestamos = num;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getSancion() {
        return sancion;
    }

    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        if(nombre.length() > 20){
            this.nombre = nombre.substring(0,20);
        }else{
            this.nombre = nombre;
        }
    }

    public void setApellidos(String apellidos) {
        if(apellidos.length() > 50){
            this.apellidos = apellidos.substring(0,50);
        }else{
            this.apellidos = apellidos;
        }
    }

    public void setEmail(String email) {
        if(email.length() > 50){
            this.email = email.substring(0,50);
        }else{
            this.email = email;
        }
    }

    public void setTelefono(String telefono) {
        if(telefono.length() > 9){
            this.telefono = telefono.substring(0,9);
        }else{
            this.telefono = telefono;
        }
    }

    public void setSancion(int sancion) {
        this.sancion += sancion ;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Métodos propios

    /***
     * Método para calcular la sanción a partir de una fecha
     * @param fecha Fecha deseada
     * @return Los dias de sanción
     */
    public int calculaSancion(LocalDate fecha){
        long dias = ChronoUnit.DAYS.between(fecha,LocalDate.now());
        if ( dias < 0){
            dias *= -1;
        }
        return (int)(dias);
    }
}
