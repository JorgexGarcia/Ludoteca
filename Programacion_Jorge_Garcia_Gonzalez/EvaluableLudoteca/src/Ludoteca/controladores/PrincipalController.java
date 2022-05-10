package Ludoteca.controladores;

import Ludoteca.modelos.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class PrincipalController {

    //Botones
    @FXML private Button btnAddAlumno;
    @FXML private Button btnAddAlumnoGuardar;
    @FXML private Button btnAddJuego;
    @FXML private Button btnAddJuegoGuardar;
    @FXML private Button btnListaAlumnos;
    @FXML private Button btnListaJuegos;
    @FXML private Button btnListaAlquiler;
    @FXML private Button btnVerAlumno;
    @FXML private Button btnVerAlumnoGuardar;
    @FXML private Button btnVerAlumnoElminar;
    @FXML private Button btnVerJuegoGuardar;
    @FXML private Button btnVerJuegoElminar;
    @FXML private Button btnVerAlumnoPrestamos;
    @FXML private Button btnAddPrestamoGuardar;
    @FXML private Button btnAddPrestamo;
    @FXML private Button btnVerAlumnoPrestamo1Ampliacion;
    @FXML private Button btnVerAlumnoPrestamo2Ampliacion;
    @FXML private Button btnVerAlumnoPrestamo3Ampliacion;
    @FXML private Button btnVerAlumnoPrestamo1Devolver;
    @FXML private Button btnVerAlumnoPrestamo2Devolver;
    @FXML private Button btnVerAlumnoPrestamo3Devolver;
    @FXML private Button btnSalir;

    //DatePicker
    @FXML private DatePicker dtpVerAlumnoPrestamo1FeSalida;
    @FXML private DatePicker dtpVerAlumnoPrestamo2FeSalida;
    @FXML private DatePicker dtpVerAlumnoPrestamo3FeSalida;
    @FXML private DatePicker dtpVerAlumnoPrestamo1FeEntrega;
    @FXML private DatePicker dtpVerAlumnoPrestamo2FeEntrega;
    @FXML private DatePicker dtpVerAlumnoPrestamo3FeEntrega;

    //Cajas de texto
    @FXML private TextField txtAddAlumnoNombre;
    @FXML private TextField txtAddAlumnoApellidos;
    @FXML private TextField txtAddAlumnoEmail;
    @FXML private TextField txtAddAlumnoTelefono;
    @FXML private TextField txtAddJuegoNombre;
    @FXML private TextField txtAddJuegoTematica;
    @FXML private TextField txtAddJuegoNJugadores;
    @FXML private TextField txtVerAlumnoId;
    @FXML private TextField txtVerAlumnoNombre;
    @FXML private TextField txtVerAlumnoApellidos;
    @FXML private TextField txtVerAlumnoEmail;
    @FXML private TextField txtVerAlumnoTelefono;
    @FXML private TextField txtVerAlumnoSancion;
    @FXML private TextField txtAddPrestamoIdAlumno;
    @FXML private TextField txtAddPrestamoIdJuego;
    @FXML private TextField txtVerAlumnoPrestamo1Nombre;
    @FXML private TextField txtVerAlumnoPrestamo2Nombre;
    @FXML private TextField txtVerAlumnoPrestamo3Nombre;
    @FXML private TextField txtVerJuegoId;
    @FXML private TextField txtVerJuegoNombre;
    @FXML private TextField txtVerJuegoNumJugadores;
    @FXML private TextField txtVerJuegoTematica;
    @FXML private TextField txtVerJuegoTipo;
    @FXML private TextField txtVerJuegoDisponible;

    //Paneles
    @FXML private AnchorPane apAddAlumno;
    @FXML private AnchorPane apAddJuego;
    @FXML private AnchorPane apListaAlumno;
    @FXML private AnchorPane apListaJuegos;
    @FXML private AnchorPane apVerAlumno;
    @FXML private AnchorPane apAddPrestamo;
    @FXML private AnchorPane apListaPrestamo;
    @FXML private AnchorPane apVerJuego;

    //Botones de Radio
    @FXML private RadioButton rdbAddJuegoMesa;
    @FXML private RadioButton rdbAddJuegoVideojuego;

    //Listas
    @FXML private TableView<Alumno> tbvListaAlumnos;
    @FXML private TableColumn<Alumno,Integer> tbcIdAlumno;
    @FXML private TableColumn<Alumno,String> tbcNombreAlumno;
    @FXML private TableColumn<Alumno,String> tbcApellidosAlumno;
    @FXML private TableColumn<Alumno,String> tbcTelefonoAlumno;
    @FXML private TableColumn<Alumno,String> tbcEmailAlumno;
    @FXML private TableColumn<Alumno,Integer> tbcSancionAlumno;
    @FXML private TableColumn<Alumno,String> tbcNPrestamosAlumno;
    @FXML private TableView<Juego> tbvListaJuegos;
    @FXML private TableColumn<Juego,Integer> tbcIdJuego;
    @FXML private TableColumn<Juego,String> tbcNombreJuego;
    @FXML private TableColumn<Juego,String> tbcNumJugJuego;
    @FXML private TableColumn<Juego,String> tbcTematicaJuego;
    @FXML private TableColumn<Juego,String> tbcTipoJuego;
    @FXML private TableColumn<Juego,String> tbcDisponibleJuego;
    @FXML private TableView<Prestamo> tbvListaPrestamos;
    @FXML private TableColumn<Prestamo,Integer> tbcIdPrestamo;
    @FXML private TableColumn<Prestamo,Integer> tbcIdJuegoPrestamo;
    @FXML private TableColumn<Prestamo,Integer> tbcIDAlumnoPrestamo;
    @FXML private TableColumn<Prestamo,LocalDate> tbcFechaEntrega;
    @FXML private TableColumn<Prestamo,LocalDate> tbcFechaDevolucion;
    @FXML private TableColumn<Prestamo,String> tbcAmpliacion;

    //Checkbox
    @FXML private CheckBox chkAddPrestamoAmpliacion;
    @FXML private CheckBox chkVerAlumnoPrestamo1Ampliacion;
    @FXML private CheckBox chkVerAlumnoPrestamo2Ampliacion;
    @FXML private CheckBox chkVerAlumnoPrestamo3Ampliacion;

    //VBox
    @FXML private VBox vbxVerAlumnoPrestamos;

    //Variables
    private PrestamoConNombreJuego[] prestamos;
    private ArrayList<String> contenidoFichero;

    //Métodos

    /**
     * Método para esconder todas las ventanas
     */
    public void limpiarVentana(){
        apAddAlumno.visibleProperty().setValue(false);
        apAddJuego.visibleProperty().setValue(false);
        apListaAlumno.visibleProperty().setValue(false);
        apListaJuegos.visibleProperty().setValue(false);
        apVerAlumno.visibleProperty().setValue(false);
        apAddPrestamo.visibleProperty().setValue(false);
        vbxVerAlumnoPrestamos.visibleProperty().setValue(false);
        apListaPrestamo.visibleProperty().setValue(false);
        apVerJuego.visibleProperty().setValue(false);
    }

    /***
     * Método cuando pulsas añadir un alumno, pone visible la ventana
     */
    public void onbtnAddAlumnoClicked(MouseEvent mouseEvent){
        limpiarVentana();
        apAddAlumno.visibleProperty().setValue(true);
    }

    /***
     * Método para guardar un alumno, comprueba que estan todos los campos rellenados
     * @param mouseEvent Evento
     */
    public void onbtnAddAlumnoGuardarClicked(MouseEvent mouseEvent){
        if (txtAddAlumnoNombre.getText().compareToIgnoreCase("") != 0 && 
                txtAddAlumnoTelefono.getText().compareToIgnoreCase("") != 0 &&
                txtAddAlumnoApellidos.getText().compareToIgnoreCase("") != 0 &&
                txtAddAlumnoEmail.getText().compareToIgnoreCase("") != 0){
            Alumno alumno = rellenaAlumno();
            try {
                if (BDController.insertarAlumno(alumno,BDController.getConexion())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("COMPLETADO");
                    alert.setContentText("SE HA GUARDADO EL ALUMNO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR AL ESCRIBIR");
                    alert.setContentText("NO SE HA PODIDO GUARDAR EL ALUMNO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR AL ESCRIBIR");
                alert.setContentText("NO SE HA PODIDO GUARDAR EL ALUMNO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            limpiarapAddAlumno();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falta Información");
            alert.setContentText("Hay que rellenar todos los campos");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    /***
     * Método para recoger los datos rellenados y crear un Alumno
     * @return Alumno recogido de la ventan
     */
    private Alumno rellenaAlumno(){
        String nombre = txtAddAlumnoNombre.getText().trim().toLowerCase(Locale.ROOT);
        String apellidos = txtAddAlumnoApellidos.getText().trim().toLowerCase(Locale.ROOT);
        String email = txtAddAlumnoEmail.getText().trim().toLowerCase(Locale.ROOT);
        String telefono = txtAddAlumnoTelefono.getText().trim().toLowerCase(Locale.ROOT);
        return new Alumno(nombre,apellidos,email,telefono);
    }

    /***
     * Método para limpiar los datos de la ventana y esconderla cuando añades un alumno
     */
    private void limpiarapAddAlumno(){
        txtAddAlumnoNombre.clear();
        txtAddAlumnoApellidos.clear();
        txtAddAlumnoTelefono.clear();
        txtAddAlumnoEmail.clear();
        apAddAlumno.visibleProperty().setValue(false);
    }

    /***
     * Método cuando pulsas añadir un juego, pone visible la ventana
     * @param mouseEvent Evento
     */
    public void onbtnAddJuegoClicked(MouseEvent mouseEvent){
        limpiarVentana();
        inicializaapAddJuego();
        apAddJuego.visibleProperty().setValue(true);
    }

    /***
     * Método para configurar los radioButton de la ventana de añadir un juego
     */
    private void inicializaapAddJuego() {
        ToggleGroup group = new ToggleGroup();
        rdbAddJuegoMesa.setToggleGroup(group);
        rdbAddJuegoVideojuego.setToggleGroup(group);
        rdbAddJuegoVideojuego.selectedProperty().setValue(true);
    }

    /***
     * Método para guardar un juego en la Base de datos, comprueba que estan todos los campos introducidos
     * @param mouseEvent Evento
     */
    public void onbtnAddJuegoGuardarClicked(MouseEvent mouseEvent){
        if (txtAddJuegoNombre.getLength() > 0 &&
                txtAddJuegoTematica.getLength() > 0 &&
                txtAddJuegoNJugadores.getLength() > 0){
            Juego juego = rellenaJuego();
            try {
                if (BDController.insertarJuego(juego,BDController.getConexion())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("COMPLETADO");
                    alert.setContentText("SE HA GUARDADO EL JUEGO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR AL ESCRIBIR");
                    alert.setContentText("NO SE HA PODIDO GUARDAR EL JUEGO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR AL ESCRIBIR");
                alert.setContentText("NO SE HA PODIDO GUARDAR EL JUEGO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            limpiarapAddJuego();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falta Información");
            alert.setContentText("Hay que rellenar todos los campos");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    /***
     * Método para recoger los datos de la ventana y crear un Juego
     * @return Juego recogido de la ventana
     */
    private Juego rellenaJuego() {
        String nombre = txtAddJuegoNombre.getText();
        String numeroJugadores = txtAddJuegoNJugadores.getText();
        String tematica = txtAddJuegoTematica.getText();
        String tipo = "videojuego";
        if(rdbAddJuegoMesa.isSelected()){
            tipo = "mesa";
        }
        return new Juego(nombre, numeroJugadores, tematica, tipo);
    }

    /***
     * Método para limpiar la ventana despúes de crear un juego
     */
    private void limpiarapAddJuego(){
        txtAddJuegoNombre.clear();
        txtAddJuegoNJugadores.clear();
        txtAddJuegoTematica.clear();
        apAddJuego.visibleProperty().setValue(false);
    }

    /***
     * Método cuando pulsas añadir un prestamo, pone visible la ventana
     * @param mouseEvent Evento
     */
    public void onbtnAddPrestamoClicked(MouseEvent mouseEvent){
        limpiarVentana();
        apAddPrestamo.visibleProperty().setValue(true);
    }

    /***
     * Método para guardar un prestamo en la Base de datos, comprueba que estan todos los campos introducidos
     * @param mouseEvent Evento
     */
    public void onbtnAddPrestamoGuardarClicked(MouseEvent mouseEvent){
        if(comprobarUsuarioValido(Integer.parseInt(txtAddPrestamoIdAlumno.getText())) &&
                comprobarJuegoValido(Integer.parseInt(txtAddPrestamoIdJuego.getText()))){
            Prestamo prestamo = rellenaPrestamo();
            try {
                if (BDController.insertarPrestamo(prestamo,BDController.getConexion())){
                    incluirPrestamoFichero(prestamo);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("COMPLETADO");
                    alert.setContentText("SE HA GUARDADO EL PRESTAMO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR AL ESCRIBIR");
                    alert.setContentText("NO SE HA PODIDO GUARDAR EL PRESTAMO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR AL ESCRIBIR");
                alert.setContentText("NO SE HA PODIDO GUARDAR EL PRESTAMO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            limpiarapAddPrestamo();
        }
    }

    /***
     * Método para añadir un préstamo al fichero
     * @param prestamo Préstamo para incluir en el fichero
     */
    private void incluirPrestamoFichero(Prestamo prestamo) {
        try {
            Alumno a = BDController.getOneAlumno(BDController.getConexion(),prestamo.getIdAlumno());
            Juego j = BDController.getOneJuego(BDController.getConexion(),prestamo.getIdJuego());
            String linea = a.getNombre() + ";" + a.getApellidos() + ";" + j.getNombre() + ";"
                    + prestamo.getFechaEntrega().toString() + ";" + prestamo.getFechaDevolucion().toString();
            FileController.anyadirFichero(linea);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL ESCRIBIR");
            alert.setContentText("NO SE HA PODIDO GUARDAR EL PRESTAMO EN FICHERO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    /***
     * Método para comprobar si el juego es válido para prestar
     * @param num Id del juego para obtener su información
     * @return Si el juego se puede alquilar
     */
    private boolean comprobarJuegoValido(int num) {
        try {
            Juego j = BDController.getOneJuego(BDController.getConexion(), num);
            if (j != null){
                if(j.isDisponible()){
                    return true;
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setContentText("JUEGO NO DISPONIBLE");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("JUEGO NO VALIDO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL LEER");
            alert.setContentText("NO SE HA PODIDO LEER EL JUEGO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        return false;
    }

    /***
     * Método para comprobar si el alumno puede prestar
     * @param num Id del alumno para obtener su información
     * @return Si el alumno puede alquilar
     */
    private boolean comprobarUsuarioValido(int num) {
        try {
            Alumno a = BDController.getOneAlumno(BDController.getConexion(), num);
            if (a != null){
                if(a.getNumPrestamos()<Alumno.MAXPRESTAMOS && a.getSancion()<=0){
                    return true;
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setContentText("ALUMNO NO PUEDE ALQUILAR");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("ALUMNO NO VALIDO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL LEER");
            alert.setContentText("NO SE HA PODIDO LEER EL ALUMNO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        return false;
    }

    /***
     * Método para recoger los datos de la ventana y crear un Prestamo
     * @return Préstamo recogido de la ventana
     */
    private Prestamo rellenaPrestamo(){
        int idAlumno = Integer.parseInt(txtAddPrestamoIdAlumno.getText().trim());
        int idJuego = Integer.parseInt(txtAddPrestamoIdJuego.getText().trim());
        boolean prestamo = chkAddPrestamoAmpliacion.isSelected();
        LocalDate fecha = LocalDate.now();
        LocalDate fechaEntrega ;
        if(prestamo){
            fechaEntrega = fecha.plusDays(6);
        }else{
            fechaEntrega = fecha.plusDays(3);
        }
        return new Prestamo(prestamo,fecha,fechaEntrega,idAlumno,idJuego);
    }

    /***
     * Método para limpiar la ventana despúes de crear un prestamo
     */
    private void limpiarapAddPrestamo(){
        txtAddPrestamoIdAlumno.clear();
        txtAddPrestamoIdJuego.clear();
        chkAddPrestamoAmpliacion.selectedProperty().setValue(false);
        apAddPrestamo.visibleProperty().setValue(false);
    }

    /***
     * Método para visualizar una lista de préstamos, pone visible la ventana
     * @param mouseEvent Evento
     */
    public void onbtnListaAlquilerClicked(MouseEvent mouseEvent){
        limpiarVentana();
        inicializaapListaPrestamo();
        apListaPrestamo.visibleProperty().setValue(true);
    }

    /***
     * Método para rellenar la ventana del listado de préstamos
     */
    private void inicializaapListaPrestamo() {
        ArrayList<Prestamo> prestamos = null;
        try {
             prestamos = BDController.getAllPrestamos(BDController.getConexion());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL LEER");
            alert.setContentText("NO SE HA PODIDO LEER EN LA BASE DE DATOS");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        tbcIdPrestamo.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbcIdPrestamo.setStyle( "-fx-alignment: CENTER;");
        tbcIdJuegoPrestamo.setCellValueFactory(new PropertyValueFactory<>("idJuego"));
        tbcIdJuegoPrestamo.setStyle( "-fx-alignment: CENTER;");
        tbcIDAlumnoPrestamo.setCellValueFactory(new PropertyValueFactory<>("idAlumno"));
        tbcIDAlumnoPrestamo.setStyle( "-fx-alignment: CENTER;");
        tbcFechaEntrega.setCellValueFactory(new PropertyValueFactory<>("fechaEntrega"));
        tbcFechaEntrega.setStyle( "-fx-alignment: CENTER;");
        tbcFechaDevolucion.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
        tbcFechaDevolucion.setStyle( "-fx-alignment: CENTER;");
        tbcAmpliacion.setCellValueFactory(new PropertyValueFactory<>("ampliacionString"));
        tbcAmpliacion.setStyle( "-fx-alignment: CENTER;");
        tbvListaPrestamos.setItems(FXCollections.observableArrayList(prestamos));
    }

    /***
     * Método para visualizar una lista de juegos, pone visible la ventana
     * @param mouseEvent Evento
     */
    public void onbtnListaJuegosClicked(MouseEvent mouseEvent){
        limpiarVentana();
        inicializaapListaJuegos();
        apListaJuegos.visibleProperty().setValue(true);
    }

    /***
     * Método para rellenar la ventana del listado de juegos
     */
    private void inicializaapListaJuegos() {
        ArrayList<Juego> juegos = null;
        try {
            juegos = BDController.getAllJuego(BDController.getConexion());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL LEER");
            alert.setContentText("NO SE HA PODIDO LEER EN LA BASE DE DATOS");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        tbcIdJuego.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbcIdJuego.setStyle( "-fx-alignment: CENTER;");
        tbcNombreJuego.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcNombreJuego.setStyle( "-fx-alignment: CENTER;");
        tbcNumJugJuego.setCellValueFactory(new PropertyValueFactory<>("numJugadores"));
        tbcNumJugJuego.setStyle( "-fx-alignment: CENTER;");
        tbcTematicaJuego.setCellValueFactory(new PropertyValueFactory<>("tematica"));
        tbcTematicaJuego.setStyle( "-fx-alignment: CENTER;");
        tbcTipoJuego.setCellValueFactory(new PropertyValueFactory<>("tipoJuego"));
        tbcTipoJuego.setStyle( "-fx-alignment: CENTER;");
        tbcDisponibleJuego.setCellValueFactory(new PropertyValueFactory<>("disponibleString"));
        tbcDisponibleJuego.setStyle( "-fx-alignment: CENTER;");
        tbvListaJuegos.setItems(FXCollections.observableArrayList(juegos));
    }

    /***
     * Método para visualizar una lista de alumnos, pone visible la ventana
     * @param mouseEvent Evento
     */
    public void onbtnListaAlumnosClicked(MouseEvent mouseEvent){
        limpiarVentana();
        inicializaapListaAlumno();
        apListaAlumno.visibleProperty().setValue(true);
    }

    /***
     * Método para rellenar la ventana del listado de alumnos
     */
    private void inicializaapListaAlumno() {
        ArrayList<Alumno> alumnos = null;
        try {
            alumnos = BDController.getAllAlumno(BDController.getConexion());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL LEER");
            alert.setContentText("NO SE HA PODIDO LEER EN LA BASE DE DATOS");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        tbcIdAlumno.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbcIdAlumno.setStyle( "-fx-alignment: CENTER;");
        tbcNombreAlumno.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcNombreAlumno.setStyle( "-fx-alignment: CENTER;");
        tbcApellidosAlumno.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        tbcApellidosAlumno.setStyle( "-fx-alignment: CENTER;");
        tbcEmailAlumno.setCellValueFactory(new PropertyValueFactory<>("email"));
        tbcEmailAlumno.setStyle( "-fx-alignment: CENTER;");
        tbcTelefonoAlumno.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tbcTelefonoAlumno.setStyle( "-fx-alignment: CENTER;");
        tbcSancionAlumno.setCellValueFactory(new PropertyValueFactory<>("sancion"));
        tbcSancionAlumno.setStyle( "-fx-alignment: CENTER;");
        tbcNPrestamosAlumno.setCellValueFactory(new PropertyValueFactory<>("numPrestamos"));
        tbcNPrestamosAlumno.setStyle( "-fx-alignment: CENTER;");
        tbvListaAlumnos.setItems(FXCollections.observableArrayList(alumnos));
    }

    /***
     * Método para enseñar la información de un juego, primero pide por pantalla el id del juego
     * @param mouseEvent Evento
     */
    public void onbtnVerJuegoClicked(MouseEvent mouseEvent){
        limpiarVentana();
        int id = obtenerID("Escribe el ID del Juego");
        if(id >0){
            if(inicializaapVerJuego(id)){
                apVerJuego.visibleProperty().setValue(true);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("ID NO VALIDO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    /***
     * Método para inicializar la ventana de información del Juego
     * @param id Id del Juego a visualizar
     * @return Devuelve si ha podido encontrar un Juego en la Base de Datos
     */
    private boolean inicializaapVerJuego(int id) {
        try {
            Juego j = BDController.getOneJuego(BDController.getConexion(), id);
            if (j != null){
                mostrarJuego(j);
                return true;
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR AL LEER");
                alert.setContentText("NO SE HA PODIDO LEER EL JUEGO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL LEER");
            alert.setContentText("NO SE HA PODIDO LEER EL JUEGO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        return false;
    }

    /***
     * Método para rellenar la ventana con la información del juego
     * @param j Juego para mostrar la información
     */
    private void mostrarJuego(Juego j) {
        txtVerJuegoId.setText(String.valueOf(j.getId()));
        txtVerJuegoNombre.setText(j.getNombre());
        txtVerJuegoNumJugadores.setText(j.getNumJugadores());
        txtVerJuegoTematica.setText(j.getTematica());
        txtVerJuegoTipo.setText(j.getTipoJuego());
        txtVerJuegoDisponible.setText(j.getDisponibleString());
    }

    /***
     * Método para modificar los datos del juego, los datos de id, tipo y disponible no se pueden modificar,
     * Primero se comprueba que no hay ningún campo sin rellenar
     * @param mouseEvent Evento
     */
    public void onbtnVerJuegoGuardarClicked(MouseEvent mouseEvent){
        if (txtVerJuegoNombre.getText().compareToIgnoreCase("") != 0 &&
                txtVerJuegoNumJugadores.getText().compareToIgnoreCase("") != 0 &&
                txtVerJuegoTematica.getText().compareToIgnoreCase("") != 0){
            Juego j = rellenaJuegoVerJuego();
            try {
                if (BDController.modificaJuego(BDController.getConexion(), j)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("COMPLETADO");
                    alert.setContentText("SE HA GUARDADO EL JUEGO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR AL ESCRIBIR");
                    alert.setContentText("NO SE HA PODIDO GUARDAR EL JUEGO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR AL ESCRIBIR");
                alert.setContentText("NO SE HA PODIDO GUARDAR EL JUEGO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            limpiarapVerJuego();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falta Información");
            alert.setContentText("Hay que rellenar todos los campos");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    /***
     *  Método para limpiar la ventana de información de un juego
     */
    private void limpiarapVerJuego() {
        txtVerJuegoId.clear();
        txtVerJuegoNombre.clear();
        txtVerJuegoNumJugadores.clear();
        txtVerJuegoTematica.clear();
        txtVerJuegoTipo.clear();
        txtVerJuegoDisponible.clear();
        apVerJuego.visibleProperty().setValue(false);
    }

    /***
     * Método para recoger los datos de la ventana y crear un juego
     * @return Juego recogido de la ventana
     */
    private Juego rellenaJuegoVerJuego() {
        Juego j = new Juego();
        j.setId(Integer.parseInt(txtVerJuegoId.getText()));
        j.setNombre(txtVerJuegoNombre.getText());
        j.setNumJugadores(txtVerJuegoNumJugadores.getText());
        j.setTematica(txtVerJuegoTematica.getText());
        j.setTipoJuego(txtVerJuegoTipo.getText());
        if(txtVerJuegoDisponible.getText().equalsIgnoreCase("SI")){
            j.setDisponible(true);
        }else{
            j.setDisponible(false);
        }
        return j;
    }

    /***
     * Método para eliminar un juego de la base de datos
     * @param mouseEvent Evento
     */
    public void onbtnVerJuegoElminarClicked(MouseEvent mouseEvent){
        if(txtVerJuegoDisponible.getText().equalsIgnoreCase("SI")){
            try {
                if (BDController.eliminarJuego(BDController.getConexion(), Integer.parseInt(txtVerJuegoId.getText()))){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("COMPLETADO");
                    alert.setContentText("SE HA ELIMINADO EL JUEGO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR AL ESCRIBIR");
                    alert.setContentText("NO SE HA PODIDO ELIMINAR EL JUEGO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR AL ESCRIBIR");
                alert.setContentText("NO SE HA PODIDO ELIMINAR EL JUEGO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            limpiarapVerJuego();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("NO SE PUEDE ELIMINAR UN JUEGO SI NO ESTA DISPONIBLE");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    /***
     * Método para enseñar la información de un alumno, primero pide por pantalla el id del alumno
     * @param mouseEvent Evento
     */
    public void onbtnVerAlumnoClicked(MouseEvent mouseEvent){
        limpiarVentana();
        int id = obtenerID("Escribe el ID del Alumno");
        if(id >0){
            if(inicializaapVerAlumno(id)){
                apVerAlumno.visibleProperty().setValue(true);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("ID NO VALIDO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    /***
     * Método para recoger la id introducida
     * @return Id introducida
     */

    private int obtenerID(String texto) {
        TextInputDialog txtId = new TextInputDialog(texto);
        txtId.showAndWait();
        try{
            return Integer.parseInt(txtId.getEditor().getText());
        }catch (Exception e){
            return 0;
        }
    }

    /***
     * Método para inicializar la ventana de información del alumno
     * @param id Id del alumno a visualizar
     * @return Devuelve si ha podido encontrar un alumno en la Base de Datos
     */
    private boolean inicializaapVerAlumno(int id) {
        try {
            Alumno a = BDController.getOneAlumno(BDController.getConexion(), id);
            if (a != null){
                mostrarAlumno(a);
                return true;
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR AL LEER");
                alert.setContentText("NO SE HA PODIDO LEER EL ALUMNO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL LEER");
            alert.setContentText("NO SE HA PODIDO LEER EL ALUMNO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        return false;
    }

    /***
     * Método para rellenar la ventana con la información del alumno
     * @param a Alumno para mostrar la información
     */
    private void mostrarAlumno(Alumno a) {
        txtVerAlumnoId.setText(String.valueOf(a.getId()));
        txtVerAlumnoNombre.setText(a.getNombre());
        txtVerAlumnoApellidos.setText(a.getApellidos());
        txtVerAlumnoEmail.setText(a.getEmail());
        txtVerAlumnoTelefono.setText(a.getTelefono());
        txtVerAlumnoSancion.setText(String.valueOf(a.getSancion()));
    }

    /***
     * Método para modificar los datos del alumno, los datos de id y de sanción no se pueden modificar,
     * Primero se comprueba que no hay ningún campo sin rellenar
     * @param mouseEvent Evento
     */
    public void onbtnVerAlumnoGuardarClicked(MouseEvent mouseEvent){
        if (txtVerAlumnoNombre.getText().compareToIgnoreCase("") != 0 &&
                txtVerAlumnoApellidos.getText().compareToIgnoreCase("") != 0 &&
                txtVerAlumnoTelefono.getText().compareToIgnoreCase("") != 0 &&
                txtVerAlumnoEmail.getText().compareToIgnoreCase("") != 0){
            Alumno a = rellenaAlumnoVerAlumno();
            try {
                if (BDController.modificaAlumno(BDController.getConexion(), a) > 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("COMPLETADO");
                    alert.setContentText("SE HA GUARDADO EL ALUMNO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR AL ESCRIBIR");
                    alert.setContentText("NO SE HA PODIDO GUARDAR EL ALUMNO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR AL ESCRIBIR");
                alert.setContentText("NO SE HA PODIDO GUARDAR EL ALUMNO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            limpiarapVerAlumno();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falta Información");
            alert.setContentText("Hay que rellenar todos los campos");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    /***
     * Método para recoger los datos de la ventana y crear un alumno
     * @return Alumno recogido de la ventana
     */
    private Alumno rellenaAlumnoVerAlumno() {
        String nombre = txtVerAlumnoNombre.getText().trim().toLowerCase(Locale.ROOT);
        String apellidos = txtVerAlumnoApellidos.getText().trim().toLowerCase(Locale.ROOT);
        String email = txtVerAlumnoEmail.getText().trim().toLowerCase(Locale.ROOT);
        String telefono = txtVerAlumnoTelefono.getText().trim().toLowerCase(Locale.ROOT);
        int id = Integer.parseInt(txtVerAlumnoId.getText());
        Alumno a = new Alumno(nombre,apellidos,email,telefono);
        a.setId(id);
        return a;
    }

    /***
     * Método para limpiar la ventana de información de un alumno
     */
    private void limpiarapVerAlumno(){
        txtVerAlumnoNombre.clear();
        txtVerAlumnoApellidos.clear();
        txtVerAlumnoEmail.clear();
        txtVerAlumnoTelefono.clear();
        txtVerAlumnoId.clear();
        txtVerAlumnoSancion.clear();
        apVerAlumno.visibleProperty().setValue(false);
        vbxVerAlumnoPrestamos.visibleProperty().setValue(false);
    }

    /***
     * Método para eliminar un alumno de la base de datos
     * @param mouseEvent Evento
     */
    public void onbtnVerAlumnoElminarClicked(MouseEvent mouseEvent){
        Alumno a = null;
        try {
            a = BDController.getOneAlumno(BDController.getConexion(), Integer.parseInt(txtVerAlumnoId.getText()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL LEER");
            alert.setContentText("NO SE HA PODIDO LEER EL ALUMNO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        if( a!= null){
            if(a.getNumPrestamos() <= 0){
                try {
                    if (BDController.eliminarAlumno(BDController.getConexion(), Integer.parseInt(txtVerAlumnoId.getText())) > 0){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("COMPLETADO");
                        alert.setContentText("SE HA ELIMINADO EL ALUMNO");
                        alert.setHeaderText(null);
                        alert.showAndWait();
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR AL ESCRIBIR");
                        alert.setContentText("NO SE HA PODIDO ELIMINAR EL ALUMNO");
                        alert.setHeaderText(null);
                        alert.showAndWait();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR AL ESCRIBIR");
                    alert.setContentText("NO SE HA PODIDO ELIMINAR EL ALUMNO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
                limpiarapVerAlumno();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("NO SE PUEDE ELIMINAR UN ALUMNO CON PRESTAMOS");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
    }

    /***
     * Método para ver los préstamos de un alumno
     * @param mouseEvent Evento
     */
    public void onbtnVerAlumnoPrestamosClicked(MouseEvent mouseEvent){
        vbxVerAlumnoPrestamos.visibleProperty().setValue(true);
        verAlumnoPrestamos();
    }

    /***
     * Método para rellenar el formulario con los préstamos de un alumno
     */
    private void verAlumnoPrestamos() {
        mostrarContenidoPrestamos(getPrestamoConNombreJuegos());
    }

    /***
     * Método para pedir todos los préstamos de un alumno
     * @return Conjunto de préstamos del alumno
     */
    private PrestamoConNombreJuego[] getPrestamoConNombreJuegos() {
        prestamos = new PrestamoConNombreJuego[Alumno.MAXPRESTAMOS];
        try {
            prestamos = BDController.getAllPrestamosAlumno(BDController.getConexion(), Integer.parseInt(txtVerAlumnoId.getText()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL LEER");
            alert.setContentText("NO SE HA PODIDO LEER DEL ALUMNO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        return prestamos;
    }

    /***
     * Método para mostrar el contenido de los préstamos
     * @param prestamos Conjunto de préstamos del alumno
     */
    private void mostrarContenidoPrestamos(PrestamoConNombreJuego[] prestamos) {
        mostrarContenidoPrestamo1(prestamos[0]);
        mostrarContenidoPrestamo2(prestamos[1]);
        mostrarContenidoPrestamo3(prestamos[2]);
    }

    /***
     * Método para mostrar el primer préstamo
     * @param prestamo Préstamo del alumno
     */
    private void mostrarContenidoPrestamo1(PrestamoConNombreJuego prestamo) {
        if(prestamo != null){
            dtpVerAlumnoPrestamo1FeSalida.disableProperty().setValue(false);
            dtpVerAlumnoPrestamo1FeEntrega.disableProperty().setValue(false);
            txtVerAlumnoPrestamo1Nombre.setText(prestamo.getNombreJuego());
            dtpVerAlumnoPrestamo1FeSalida.setValue(prestamo.getFechaEntrega());
            dtpVerAlumnoPrestamo1FeEntrega.setValue(prestamo.getFechaDevolucion());
            chkVerAlumnoPrestamo1Ampliacion.selectedProperty().setValue(prestamo.isAmpliacion());
            if(!prestamo.isAmpliacion() && prestamo.getFechaDevolucion().compareTo(LocalDate.now()) >= 0
                    && txtVerAlumnoSancion.getText().equals("0")){
                btnVerAlumnoPrestamo1Ampliacion.disableProperty().setValue(false);
            }else{
                btnVerAlumnoPrestamo1Ampliacion.disableProperty().setValue(true);
            }
            btnVerAlumnoPrestamo1Devolver.disableProperty().setValue(false);
        }else{
            txtVerAlumnoPrestamo1Nombre.setText("");
            dtpVerAlumnoPrestamo1FeSalida.disableProperty().setValue(true);
            dtpVerAlumnoPrestamo1FeEntrega.disableProperty().setValue(true);
            chkVerAlumnoPrestamo1Ampliacion.disableProperty().setValue(true);
            btnVerAlumnoPrestamo1Devolver.disableProperty().setValue(true);
            btnVerAlumnoPrestamo1Ampliacion.disableProperty().setValue(true);
        }
    }

    /***
     * Método para modificar el primer préstamo del alumno
     * @param mouseEvent Evento
     */
    public void onbtnVerAlumnoPrestamo1AmpliacionClicked(MouseEvent mouseEvent){
        prestamos = getPrestamoConNombreJuegos();
        prestamos[0].setAmpliacion(true);
        prestamos[0].setFechaDevolucion(prestamos[0].getFechaDevolucion().plusDays(3));
        try {
            if (BDController.modificaPrestamo(BDController.getConexion(),prestamos[0]) > 0){
                modificarPrestamoFichero(prestamos[0]);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("COMPLETADO");
                alert.setContentText("SE HA GUARDADO EL PRESTAMO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR AL ESCRIBIR");
                alert.setContentText("NO SE HA PODIDO GUARDAR EL PRESTAMO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL LEER");
            alert.setContentText("NO SE HA PODIDO LEER DEL ALUMNO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        verAlumnoPrestamos();
    }

    /***
     * Método para modificar un préstamo en fichero
     * @param prestamo Préstamo del alumno
     */
    private void modificarPrestamoFichero(PrestamoConNombreJuego prestamo) {
        try {
            contenidoFichero = FileController.leerFichero();
            Alumno a = BDController.getOneAlumno(BDController.getConexion(),prestamo.getIdAlumno());
            Juego j = BDController.getOneJuego(BDController.getConexion(),prestamo.getIdJuego());
            for (int i = 0; i < contenidoFichero.size(); i++) {
                String[] trozos = contenidoFichero.get(i).split(";");
                if(trozos[0].equalsIgnoreCase(a.getNombre()) && trozos[2].equalsIgnoreCase(j.getNombre())){
                    trozos[4] = prestamo.getFechaDevolucion().toString();
                    String s = trozos[0] + ";" + trozos[1] + ";" + trozos[2] + ";" + trozos[3] +
                            ";" + trozos[4];
                    contenidoFichero.remove(i);
                    contenidoFichero.add(i,s);
                }
            }
            FileController.escribirFichero(contenidoFichero);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("NO SE HA PODIDO MODIFICAR EL FICHERO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    /***
     * Método para devolver el segundo préstamo
     * @param mouseEvent Evento
     */
    public void onbtnVerAlumnoPrestamo1DevolverClicked(MouseEvent mouseEvent){
        prestamos = getPrestamoConNombreJuegos();
        Alumno a;
        try {
            a = BDController.getOneAlumno(BDController.getConexion(), Integer.parseInt(txtVerAlumnoId.getText()));
            if(dtpVerAlumnoPrestamo1FeEntrega.getValue().compareTo(LocalDate.now()) < 0){
                a.setSancion(a.calculaSancion(dtpVerAlumnoPrestamo1FeEntrega.getValue())* Alumno.PENAPRESTAMOS);
            }else{
                a.setSancion(0);
            }
            try {
                if(BDController.eliminarPrestamo(BDController.getConexion(), prestamos[0].getId(), a.getId(), a.getSancion()) != 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR AL LEER");
                    alert.setContentText("NO SE HA PODIDO ElIMINAR EL PRESTAMO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }else{
                    eliminarPrestamoFichero(prestamos[0]);
                    limpiarapVerAlumno();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("COMPLETADO");
                    alert.setContentText("SE HA ELIMINADO EL PRESTAMO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR AL LEER");
                alert.setContentText("NO SE HA PODIDO ElIMINAR EL PRESTAMO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL LEER");
            alert.setContentText("NO SE HA PODIDO LEER DEL ALUMNO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    /***
     * Método para eliminar un préstamo del fichero
     * @param prestamo Préstamo del alumno
     */
    private void eliminarPrestamoFichero(PrestamoConNombreJuego prestamo) {
        try {
            contenidoFichero = FileController.leerFichero();
            Alumno a = BDController.getOneAlumno(BDController.getConexion(),prestamo.getIdAlumno());
            Juego j = BDController.getOneJuego(BDController.getConexion(),prestamo.getIdJuego());
            for (int i = 0; i < contenidoFichero.size(); i++) {
                String[] trozos = contenidoFichero.get(i).split(";");
                if(trozos[0].equalsIgnoreCase(a.getNombre()) && trozos[2].equalsIgnoreCase(j.getNombre())){
                    contenidoFichero.remove(i);
                    break;
                }
            }
            FileController.escribirFichero(contenidoFichero);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("NO SE HA PODIDO ELIMINAR EL FICHERO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    /***
     * Método para mostrar el segundo préstamo
     * @param prestamo Préstamo del alumno
     */
    private void mostrarContenidoPrestamo2(PrestamoConNombreJuego prestamo) {
        if(prestamo != null){
            dtpVerAlumnoPrestamo2FeSalida.disableProperty().setValue(false);
            dtpVerAlumnoPrestamo2FeEntrega.disableProperty().setValue(false);
            txtVerAlumnoPrestamo2Nombre.setText(prestamo.getNombreJuego());
            dtpVerAlumnoPrestamo2FeSalida.setValue(prestamo.getFechaEntrega());
            dtpVerAlumnoPrestamo2FeEntrega.setValue(prestamo.getFechaDevolucion());
            chkVerAlumnoPrestamo2Ampliacion.selectedProperty().setValue(prestamo.isAmpliacion());
            if(!prestamo.isAmpliacion() && prestamo.getFechaDevolucion().compareTo(LocalDate.now()) >= 0
                    && txtVerAlumnoSancion.getText().equals("0")){
                btnVerAlumnoPrestamo2Ampliacion.disableProperty().setValue(false);
            }else{
                btnVerAlumnoPrestamo2Ampliacion.disableProperty().setValue(true);
            }
            btnVerAlumnoPrestamo2Devolver.disableProperty().setValue(false);
        }else{
            txtVerAlumnoPrestamo2Nombre.setText("");
            dtpVerAlumnoPrestamo2FeSalida.disableProperty().setValue(true);
            dtpVerAlumnoPrestamo2FeEntrega.disableProperty().setValue(true);
            chkVerAlumnoPrestamo2Ampliacion.disableProperty().setValue(true);
            btnVerAlumnoPrestamo2Devolver.disableProperty().setValue(true);
            btnVerAlumnoPrestamo2Ampliacion.disableProperty().setValue(true);
        }
    }

    /***
     * Método para modificar el segundo préstamo del alumno
     * @param mouseEvent Evento
     */
    public void onbtnVerAlumnoPrestamo2AmpliacionClicked(MouseEvent mouseEvent){
        prestamos = getPrestamoConNombreJuegos();
        prestamos[1].setAmpliacion(true);
        prestamos[1].setFechaDevolucion(prestamos[1].getFechaDevolucion().plusDays(3));
        try {
            if (BDController.modificaPrestamo(BDController.getConexion(),prestamos[1]) > 0){
                modificarPrestamoFichero(prestamos[1]);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("COMPLETADO");
                alert.setContentText("SE HA GUARDADO EL PRESTAMO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR AL ESCRIBIR");
                alert.setContentText("NO SE HA PODIDO GUARDAR EL PRESTAMO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL LEER");
            alert.setContentText("NO SE HA PODIDO LEER DEL ALUMNO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        verAlumnoPrestamos();
    }

    /***
     * Método para devolver el segundo préstamo
     * @param mouseEvent Evento
     */
    public void onbtnVerAlumnoPrestamo2DevolverClicked(MouseEvent mouseEvent){
        prestamos = getPrestamoConNombreJuegos();
        Alumno a;
        try {
            a = BDController.getOneAlumno(BDController.getConexion(), Integer.parseInt(txtVerAlumnoId.getText()));
            if(dtpVerAlumnoPrestamo2FeEntrega.getValue().compareTo(LocalDate.now()) < 0){
                a.setSancion(a.calculaSancion(dtpVerAlumnoPrestamo2FeEntrega.getValue())* Alumno.PENAPRESTAMOS);
            }else{
                a.setSancion(0);
            }
            try {
                if(BDController.eliminarPrestamo(BDController.getConexion(), prestamos[1].getId(), a.getId(), a.getSancion()) != 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR AL LEER");
                    alert.setContentText("NO SE HA PODIDO ElIMINAR EL PRESTAMO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }else{
                    eliminarPrestamoFichero(prestamos[1]);
                    limpiarapVerAlumno();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("COMPLETADO");
                    alert.setContentText("SE HA ELIMINADO EL PRESTAMO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR AL LEER");
                alert.setContentText("NO SE HA PODIDO ElIMINAR EL PRESTAMO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL LEER");
            alert.setContentText("NO SE HA PODIDO LEER DEL ALUMNO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    /***
     * Método para mostrar el tercer préstamo
     * @param prestamo Préstamo del alumno
     */
    private void mostrarContenidoPrestamo3(PrestamoConNombreJuego prestamo) {
        if(prestamo != null){
            dtpVerAlumnoPrestamo3FeSalida.disableProperty().setValue(false);
            dtpVerAlumnoPrestamo3FeEntrega.disableProperty().setValue(false);
            txtVerAlumnoPrestamo3Nombre.setText(prestamo.getNombreJuego());
            dtpVerAlumnoPrestamo3FeSalida.setValue(prestamo.getFechaEntrega());
            dtpVerAlumnoPrestamo3FeEntrega.setValue(prestamo.getFechaDevolucion());
            chkVerAlumnoPrestamo3Ampliacion.selectedProperty().setValue(prestamo.isAmpliacion());
            if(!prestamo.isAmpliacion() && prestamo.getFechaDevolucion().compareTo(LocalDate.now()) >= 0
                        && txtVerAlumnoSancion.getText().equals("0")){
                btnVerAlumnoPrestamo3Ampliacion.disableProperty().setValue(false);
            }else{
                btnVerAlumnoPrestamo3Ampliacion.disableProperty().setValue(true);
            }
            btnVerAlumnoPrestamo3Devolver.disableProperty().setValue(false);
        }else{
            txtVerAlumnoPrestamo3Nombre.setText("");
            dtpVerAlumnoPrestamo3FeSalida.disableProperty().setValue(true);
            dtpVerAlumnoPrestamo3FeEntrega.disableProperty().setValue(true);
            chkVerAlumnoPrestamo3Ampliacion.disableProperty().setValue(true);
            btnVerAlumnoPrestamo3Devolver.disableProperty().setValue(true);
            btnVerAlumnoPrestamo3Ampliacion.disableProperty().setValue(true);
        }
    }

    /***
     * Método para modificar el tercer préstamo del alumno
     * @param mouseEvent Evento
     */
    public void onbtnVerAlumnoPrestamo3AmpliacionClicked(MouseEvent mouseEvent){
        prestamos = getPrestamoConNombreJuegos();
        prestamos[2].setAmpliacion(true);
        prestamos[2].setFechaDevolucion(prestamos[2].getFechaDevolucion().plusDays(3));
        try {
            if (BDController.modificaPrestamo(BDController.getConexion(),prestamos[2]) > 0){
                modificarPrestamoFichero(prestamos[2]);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("COMPLETADO");
                alert.setContentText("SE HA GUARDADO EL PRESTAMO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR AL ESCRIBIR");
                alert.setContentText("NO SE HA PODIDO GUARDAR EL PRESTAMO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL LEER");
            alert.setContentText("NO SE HA PODIDO LEER DEL ALUMNO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        verAlumnoPrestamos();
    }

    /***
     * Método para devolver el tercer préstamo
     * @param mouseEvent Evento
     */
    public void onbtnVerAlumnoPrestamo3DevolverClicked(MouseEvent mouseEvent){
        prestamos = getPrestamoConNombreJuegos();
        Alumno a ;
        try {
            a = BDController.getOneAlumno(BDController.getConexion(), Integer.parseInt(txtVerAlumnoId.getText()));
            if(dtpVerAlumnoPrestamo3FeEntrega.getValue().compareTo(LocalDate.now()) < 0){
                a.setSancion(a.calculaSancion(dtpVerAlumnoPrestamo3FeEntrega.getValue())* Alumno.PENAPRESTAMOS);
            }else{
                a.setSancion(0);
            }
            try {
                if(BDController.eliminarPrestamo(BDController.getConexion(), prestamos[2].getId(), a.getId(), a.getSancion()) != 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR AL LEER");
                    alert.setContentText("NO SE HA PODIDO ElIMINAR EL PRESTAMO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }else{
                    eliminarPrestamoFichero(prestamos[2]);
                    limpiarapVerAlumno();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("COMPLETADO");
                    alert.setContentText("SE HA ELIMINADO EL PRESTAMO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR AL LEER");
                alert.setContentText("NO SE HA PODIDO ElIMINAR EL PRESTAMO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR AL LEER");
            alert.setContentText("NO SE HA PODIDO LEER DEL ALUMNO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    /***
     * Método para salir
     * @param mouseEvent Evento
     */
    public void onbtnSalirClicked(MouseEvent mouseEvent){
        System.exit(0);
    }
}
