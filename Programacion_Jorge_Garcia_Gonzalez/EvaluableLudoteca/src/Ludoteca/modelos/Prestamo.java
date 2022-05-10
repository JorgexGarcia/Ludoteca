package Ludoteca.modelos;

import java.time.LocalDate;

public class Prestamo {
    //Variables
    private boolean ampliacion;
    private LocalDate fechaEntrega;
    private LocalDate fechaDevolucion;
    private int id;
    private int idAlumno;
    private int idJuego;
    private String ampliacionString;

    //Constructor
    public Prestamo(boolean ampliacion, LocalDate fechaEntrega, LocalDate fechaDevolucion, int idAlumno, int idJuego) {
        this.ampliacion = ampliacion;
        this.fechaEntrega = fechaEntrega;
        this.fechaDevolucion = fechaDevolucion;
        this.idAlumno = idAlumno;
        this.idJuego = idJuego;
        setAmpliacionString();
    }

    public Prestamo() {
        ampliacion = false;
        fechaEntrega = LocalDate.now();
        fechaDevolucion = LocalDate.now();
        id = -1;
        idAlumno = -1;
        idJuego = -1;
        setAmpliacionString();
    }

    //Set y Get

    public String getAmpliacionString() {
        return ampliacionString;
    }

    public void setAmpliacionString() {
        if (ampliacion){
            this.ampliacionString = "SI";
        }
        else{
            this.ampliacionString = "NO";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public boolean isAmpliacion() {
        return ampliacion;
    }

    public void setAmpliacion(boolean ampliacion) {
        this.ampliacion = ampliacion;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
