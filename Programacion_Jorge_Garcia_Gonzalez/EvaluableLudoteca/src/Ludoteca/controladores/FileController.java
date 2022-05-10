package Ludoteca.controladores;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileController {

    //Variables
    private static final String FILE = "prestados.txt";
    private static final File FICHERO = new File(FILE);

    /***
     * Método para añadir una fila al fichero
     * @param linea Contenido para añadir
     * @throws IOException Devuelve una posible excepción
     */
    public static void anyadirFichero(String linea) throws IOException {

        FileWriter escritor = null;
        escritor = new FileWriter(FICHERO, true);
        PrintWriter pw = new PrintWriter(escritor);
        pw.println(linea);
        if(escritor != null){
            escritor.close();
        }
    }

    /***
     * Método para escribir un fichero por completo
     * @param contenido Contenido a escribir
     * @throws IOException Devuelve una posible excepción
     */
    public static void escribirFichero(ArrayList<String> contenido) throws IOException {

        FileWriter escritor = null;
        escritor = new FileWriter(FICHERO);
        PrintWriter pw = new PrintWriter(escritor);
        for (String s:contenido) {
            pw.println(s);
        }
        if(escritor != null){
            escritor.close();
        }
    }

    /***
     * Método para leer un fichero
     * @return Devuelve un conjunto de líneas
     * @throws IOException Devuelve una posible excepción
     */
    public static ArrayList<String> leerFichero() throws IOException {
        ArrayList<String> contenido = new ArrayList<>();
        File file = new File(FILE);
        FileReader lector = new FileReader(file);
        BufferedReader bf = new BufferedReader(lector);
        String linea;
        while ((linea = bf.readLine()) != null){
            contenido.add(linea);
        }
        if(lector != null){
            lector.close();
        }
        return contenido;
    }
}
