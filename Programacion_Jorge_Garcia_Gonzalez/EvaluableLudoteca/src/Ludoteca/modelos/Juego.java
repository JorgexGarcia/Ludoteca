package Ludoteca.modelos;

public class Juego {

    //Variables
    private String nombre;
    private String numJugadores;
    private String tematica;
    private String tipoJuego;
    private boolean disponible;
    private String disponibleString;
    private int id;

    //Contrustores
    public Juego() {
    }

    public Juego(String nombre, String numJugadores, String tematica, String tipoJuego) {
        setNombre(nombre);
        setNumJugadores(numJugadores);
        setTematica(tematica);
        setTipoJuego(tipoJuego);
        id = -1;
        disponible = true;
        setDisponibleString();
    }

    //Set y Get

    public String getDisponibleString() {
        return disponibleString;
    }

    public void setDisponibleString() {
        if (disponible){
            this.disponibleString = "SI";
        }
        else{
            this.disponibleString = "NO";
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre.length() > 20){
            this.nombre = nombre.substring(0,20);
        }else{
            this.nombre = nombre;
        }
    }

    public String getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(String numJugadores) {
        if(numJugadores.length() > 2){
            this.numJugadores = numJugadores.substring(0,2);
        }else{
            this.numJugadores = numJugadores;
        }
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        if(tematica.length() > 20){
            this.tematica = tematica.substring(0,20);
        }else{
            this.tematica = tematica;
        }
    }

    public String getTipoJuego() {
        return tipoJuego;
    }

    public void setTipoJuego(String tipoJuego) {
        if(tipoJuego.length() > 20){
            this.tipoJuego = tipoJuego.substring(0,20);
        }else{
            this.tipoJuego = tipoJuego;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
