package Ludoteca.modelos;

public class PrestamoConNombreJuego extends Prestamo{
    //Variable
    private String nombreJuego;

    //Constructor
    public PrestamoConNombreJuego() {
        super();
    }

    //Set y Get
    public String getNombreJuego() {
        return nombreJuego;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }
}
