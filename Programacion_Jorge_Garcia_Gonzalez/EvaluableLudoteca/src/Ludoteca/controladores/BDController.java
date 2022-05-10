package Ludoteca.controladores;

import Ludoteca.modelos.Alumno;
import Ludoteca.modelos.Juego;
import Ludoteca.modelos.Prestamo;
import Ludoteca.modelos.PrestamoConNombreJuego;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TimeZone;

public class BDController {
    /***
     * Url de conexión
     */
    private static final String URL = "jdbc:mysql://localhost/jorge_ludoteca?Timezone=";

    /***
     * Método para crear una conexión a la Base de Datos
     * @return Devuelve una conexión
     * @throws SQLException Devuelve una posible excepción
     */
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL+ TimeZone.getDefault(), "root", "");
    }

    /***
     * Método para insertar un alumno
     * @param alumno Alumno a insertar
     * @param conexion Conexión a la base de datos
     * @return Si la operación ha tenido éxito
     * @throws SQLException Devuelve una posible excepción
     */
    public static boolean insertarAlumno(Alumno alumno, Connection conexion) throws SQLException {
        String consulta = "insert into alumno (nombre, apellidos, email, telefono, sancion, numPrestamos) values (?,?,?,?,?,?)";
        PreparedStatement pstm = conexion.prepareStatement(consulta);
        pstm.setString(1, alumno.getNombre());
        pstm.setString(2, alumno.getApellidos());
        pstm.setString(3, alumno.getEmail());
        pstm.setString(4, alumno.getTelefono());
        pstm.setDate(5, Date.valueOf(LocalDate.now().plusDays(alumno.getSancion())));
        pstm.setInt(6, alumno.getNumPrestamos());

        int lineasAfectadas = pstm.executeUpdate();
        return lineasAfectadas > 0;
    }

    /***
     * Método para insertar un juego
     * @param juego Juego a insertar
     * @param conexion Conexión a la base de datos
     * @return Si la operación ha tenido éxito
     * @throws SQLException Devuelve una posible excepción
     */
    public static boolean insertarJuego(Juego juego, Connection conexion) throws SQLException {
        String consulta = "insert into juego (nombre, numJugadores, tematica, tipo, disponible) values (?,?,?,?,?)";
        PreparedStatement pstm = conexion.prepareStatement(consulta);
        pstm.setString(1, juego.getNombre());
        pstm.setString(2, juego.getNumJugadores());
        pstm.setString(3, juego.getTematica());
        pstm.setString(4, juego.getTipoJuego());
        pstm.setBoolean(5, juego.isDisponible());

        int lineasAfectadas = pstm.executeUpdate();
        return lineasAfectadas > 0;
    }

    /***
     * Método para obtener todos los alumnos de la base de datos
     * @param connection Conexión a la base de datos
     * @return Devuelve un conjunto de alumnos
     * @throws SQLException Devuelve una posible excepción
     */
    public static ArrayList<Alumno> getAllAlumno(Connection connection) throws SQLException {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String consulta = "select * from alumno;";
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery(consulta);
        while (rs.next()){
            Alumno a = new Alumno();
            a.setId(rs.getInt(1));
            a.setNombre(rs.getString(2));
            a.setApellidos(rs.getString(3));
            a.setEmail(rs.getString(4));
            LocalDate fecha = rs.getDate(5).toLocalDate();
            if(fecha.compareTo(LocalDate.now()) > 0){
                a.setSancion(a.calculaSancion(fecha));
            }else {
                a.setSancion(0);
            }
            a.setTelefono(rs.getString(6));
            a.setNumPrestamos(rs.getInt(7));
            alumnos.add(a);
        }
        rs.close();
        stm.close();
        return alumnos;
    }

    /***
     * Método para obtener todos los juegos de la base de datos
     * @param conexion Conexión a la base de datos
     * @return Devuelve un conjunto de juegos
     * @throws SQLException Devuelve una posible excepción
     */
    public static ArrayList<Juego> getAllJuego(Connection conexion) throws SQLException {
        ArrayList<Juego> juegos = new ArrayList<>();
        String consulta = "select * from juego;";
        Statement stm = conexion.createStatement();
        ResultSet rs = stm.executeQuery(consulta);
        while (rs.next()){
            Juego j = new Juego();
            j.setId(rs.getInt(1));
            j.setNombre(rs.getString(2));
            j.setNumJugadores(rs.getString(3));
            j.setTematica(rs.getString(4));
            j.setTipoJuego(rs.getString(5));
            j.setDisponible(rs.getBoolean(6));
            j.setDisponibleString();
            juegos.add(j);
        }
        rs.close();
        stm.close();
        return juegos;
    }

    /***
     * Método para obtener todos los préstamos de la base de datos
     * @param conexion Conexión a la base de datos
     * @return Devuelve un conjunto de préstamos
     * @throws SQLException Devuelve una posible excepción
     */
    public static ArrayList<Prestamo> getAllPrestamos(Connection conexion) throws SQLException {
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        String consulta = "select * from prestamo";
        Statement stm = conexion.createStatement();
        ResultSet rs = stm.executeQuery(consulta);
        while (rs.next()){
            Prestamo p = new Prestamo();
            p.setId(rs.getInt(1));
            p.setAmpliacion(rs.getBoolean(2));
            p.setFechaEntrega(rs.getDate(3).toLocalDate());
            p.setFechaDevolucion(rs.getDate(4).toLocalDate());
            p.setIdAlumno(rs.getInt(5));
            p.setIdJuego(rs.getInt(6));
            p.setAmpliacionString();
            prestamos.add(p);
        }
        rs.close();
        stm.close();
        return prestamos;
    }

    /***
     * Método para obtener un alumno de la base de datos con su id
     * @param connection Conexión a la base de datos
     * @param num Id del alumno
     * @return Devuelve un alumno
     * @throws SQLException Devuelve una posible excepción
     */
    public static Alumno getOneAlumno(Connection connection, int num) throws SQLException {
        Alumno alumno = null;
        String consulta = "Select * from alumno where id = ?;";
        PreparedStatement pstm = connection.prepareStatement(consulta);
        pstm.setInt(1, num);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            alumno = new Alumno();
            alumno.setId(resultSet.getInt(1));
            alumno.setNombre(resultSet.getString(2));
            alumno.setApellidos(resultSet.getString(3));
            alumno.setEmail(resultSet.getString(4));
            LocalDate fecha = resultSet.getDate(5).toLocalDate();
            if(fecha.compareTo(LocalDate.now()) > 0){
                alumno.setSancion(alumno.calculaSancion(fecha));
            }else {
                alumno.setSancion(0);
            }
            alumno.setTelefono(resultSet.getString(6));
            alumno.setNumPrestamos(resultSet.getInt(7));
            return alumno;
        }
        return alumno;
    }

    /***
     * Método para modificar un alumno
     * @param connection Conexión a la base de datos
     * @param alumno El alumno a modificar
     * @return Devuelve el número de columnas afectadas
     * @throws SQLException Devuelve una posible excepción
     */
    public static int modificaAlumno(Connection connection, Alumno alumno) throws SQLException {
        String consulta = "Update alumno set nombre = ?, apellidos = ?, email = ?, telefono = ? where id = ?";
        PreparedStatement pstm = connection.prepareStatement(consulta);
        pstm.setString(1, alumno.getNombre());
        pstm.setString(2, alumno.getApellidos());
        pstm.setString(3,alumno.getEmail());
        pstm.setString(4,alumno.getTelefono());
        pstm.setInt(5,alumno.getId());
        return pstm.executeUpdate();
    }

    /***
     * Método para eliminar un alumno
     * @param connection Conexión a la base de datos
     * @param id Id del alumno a eliminar
     * @return Devuelve el número de columnas afectadas
     * @throws SQLException Devuelve una posible excepción
     */
    public static int eliminarAlumno(Connection connection, int id) throws SQLException {
        String consulta = "delete from alumno where id = ?";
        PreparedStatement pstm = connection.prepareStatement(consulta);
        pstm.setInt(1,id);
        return pstm.executeUpdate();
    }

    /***
     * Método para insertar un préstamo
     * @param prestamo Préstamo a insertar
     * @param connection Conexión a la base de datos
     * @return Si la operación ha tenido éxito
     * @throws SQLException Devuelve una posible excepción
     */
    public static boolean insertarPrestamo(Prestamo prestamo, Connection connection) throws SQLException {
        String consulta = "insert into prestamo (ampliacion, fechaEntrega, fechaDevolucion, idAlumno, idJuego) values (?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(consulta);
        pstm.setBoolean(1, prestamo.isAmpliacion());
        pstm.setDate(2, Date.valueOf(prestamo.getFechaEntrega()));
        pstm.setDate(3, Date.valueOf(prestamo.getFechaDevolucion()));
        pstm.setInt(4, prestamo.getIdAlumno());
        pstm.setInt(5, prestamo.getIdJuego());

        int lineasAfectadas = pstm.executeUpdate();
        return lineasAfectadas > 0;
    }

    /***
     * Método para obetener un juego con su id
     * @param conexion Conexión a la base de datos
     * @param num Id del juego a consultar
     * @return Un juego
     * @throws SQLException Devuelve una posible excepción
     */
    public static Juego getOneJuego(Connection conexion, int num) throws SQLException {
        Juego juego = null;
        String consulta = "Select * from juego where id = ?;";
        PreparedStatement pstm = conexion.prepareStatement(consulta);
        pstm.setInt(1, num);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            juego = new Juego();
            juego.setId(resultSet.getInt(1));
            juego.setNombre(resultSet.getString(2));
            juego.setNumJugadores(resultSet.getString(3));
            juego.setTematica(resultSet.getString(4));
            juego.setTipoJuego(resultSet.getString(5));
            juego.setDisponible(resultSet.getBoolean(6));
            juego.setDisponibleString();
            return juego;
        }
        return juego;
    }

    /***
     * Método para obtener los préstamos de un alumno por su id
     * @param conexion Conexión a la base de datos
     * @param num Id del alumno a colsultar
     * @return Un conjunto de préstamos del alumno
     * @throws SQLException Devuelve una posible excepción
     */
    public static PrestamoConNombreJuego[] getAllPrestamosAlumno(Connection conexion, int num) throws SQLException {
        PrestamoConNombreJuego[] prestamos = new PrestamoConNombreJuego[Alumno.MAXPRESTAMOS];
        String consulta = "Select p.*, j.nombre from prestamo p join juego j on j.id = p.idJuego where idAlumno = ?;";
        PreparedStatement pstm = conexion.prepareStatement(consulta);
        pstm.setInt(1, num);
        ResultSet rs = pstm.executeQuery();
        int i = 0;
        while (rs.next()){
            PrestamoConNombreJuego p= new PrestamoConNombreJuego();
            p.setId(rs.getInt(1));
            p.setAmpliacion(rs.getBoolean(2));
            p.setFechaEntrega(rs.getDate(3).toLocalDate());
            p.setFechaDevolucion(rs.getDate(4).toLocalDate());
            p.setIdAlumno(rs.getInt(5));
            p.setIdJuego(rs.getInt(6));
            p.setNombreJuego(rs.getString(7));
            prestamos[i] = p;
            i ++;
        }
        rs.close();
        pstm.close();
        return prestamos;
    }

    /***
     * Modificar un prestamos de un alumno por el id del prestamo
     * @param connection Conexión a la base de datos
     * @param prestamo Préstamo a modificar
     * @return Número de columnas afectadas
     * @throws SQLException Devuelve una posible excepción
     */
    public static int modificaPrestamo(Connection connection, PrestamoConNombreJuego prestamo) throws SQLException {
        String consulta = "Update prestamo set ampliacion = ?, fechaDevolucion = ? where id = ?";
        PreparedStatement pstm = connection.prepareStatement(consulta);
        pstm.setBoolean(1, prestamo.isAmpliacion());
        pstm.setDate(2, Date.valueOf(prestamo.getFechaDevolucion()));
        pstm.setInt(3, prestamo.getId());
        return pstm.executeUpdate();
    }

    /***
     * Método para eliminar un préstamo
     * @param connection Conexión a la base de datos
     * @param idPrestamo Id del préstamo
     * @param idALumno Id del alumno
     * @param sancion La sanción del alumno añadir
     * @return Devuelve el resultado de la operación, si el resultado es 1, se ha producido un error
     * @throws SQLException Devuelve una posible excepción
     */
    public static int eliminarPrestamo(Connection connection, int idPrestamo, int idALumno, int sancion) throws SQLException {
        int resultado = 0;
        String llamada = "{call eliminaPrestamo(?,?,?,?)}";
        CallableStatement cstm = connection.prepareCall(llamada);
        cstm.setInt(1,idPrestamo);
        cstm.setInt(2,idALumno);
        cstm.setDate(3,Date.valueOf(LocalDate.now().plusDays(sancion)));
        cstm.registerOutParameter(4,resultado);
        cstm.execute();
        cstm.close();
        return resultado;
    }

    /***
     * Método para modificar un Juego
     * @param conexion Conexión a la base de datos
     * @param j El Juego a modificar
     * @return Si la operación ha tenido éxito
     * @throws SQLException Devuelve una posible excepción
     */
    public static boolean modificaJuego(Connection conexion, Juego j) throws SQLException {
        String consulta = "Update juego set nombre = ?, numJugadores = ?, tematica = ? where id = ?";
        PreparedStatement pstm = conexion.prepareStatement(consulta);
        pstm.setString(1, j.getNombre());
        pstm.setString(2, j.getNumJugadores());
        pstm.setString(3, j.getTematica());
        pstm.setInt(4, j.getId());
        return pstm.executeUpdate() > 0;
    }

    /***
     * Método para eliminar un Juego
     * @param conexion Conexión a la base de datos
     * @param id ID del Juego a eliminar
     * @return Si la operación ha tenido éxito
     * @throws SQLException Devuelve una posible excepción
     */
    public static boolean eliminarJuego(Connection conexion, int id) throws SQLException {
        String consulta = "delete from juego where id = ?";
        PreparedStatement pstm = conexion.prepareStatement(consulta);
        pstm.setInt(1,id);
        return pstm.executeUpdate() > 0;
    }
}
