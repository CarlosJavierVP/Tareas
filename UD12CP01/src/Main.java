import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import static java.lang.Integer.parseInt;


/**
 * Clase main
 *
 * @author Carlos Javier Valenzuela Pintanel
 */

public class Main {
    public static void main(String[] args){

        System.out.println("COMANDOS PARA MANEJAR LA BASE DE DATOS: ");
        System.out.println("CONNECT --> Conectar a la base de datos"+'\n'+
                "DISCONNECT --> Desconectar de la base de datos"+'\n'+
                "SHOW --> Mostrar los registros de la DB"+'\n'+
                "SEARCH --> Buscar un registro de la DB"+'\n'+
                "INSERT --> Insertar registro nuevo en la DB"+'\n'+
                "DELETE --> Borrar registro"+'\n'+
                "UPDATE --> Actualiza un registro");
        System.out.println("Inserta el comando que quieres utilizar:");
        Scanner sc = new Scanner(System.in);
        String comando = sc.nextLine().toUpperCase();
        while (!comando.equals("EXIT")) {
            // switch case --> comandos que llaman a los métodos
            switch (comando) {
                case "CONNECT":
                    ConexionBD.conexion();
                    System.out.println("---CONECTADO A LA BASE DE DATOS---");
                    comando = sc.nextLine().toUpperCase();
                    break;
                case "DISCONNECT":
                    ConexionBD.desconexion();
                    System.out.println("---DESCONECTADO DE LA BASE DE DATOS---");
                    comando = sc.nextLine().toUpperCase();
                    break;
                case "SHOW":
                    System.out.println("DATOS:" + '\n');
                    InventarioDAO.mostrar();
                    comando = sc.nextLine().toUpperCase();
                    break;
                case "SEARCH":
                    String busca = sc.nextLine();
                    InventarioDAO.buscar(busca);
                    comando = sc.nextLine().toUpperCase();
                    break;
                case "INSERT":
                    String obj;
                        if ((obj = sc.nextLine()) != null){
                            String[] trozos = obj.split(", ");
                            int id = parseInt(trozos[0]);
                            String nombre = trozos[1];
                            int cantidad = parseInt(trozos[2]);
                            String comentario = trozos[3];
                            // Crear formato fecha para luego parsear el string de la fecha a formato Date
                            String formatoFecha = "yyyy-MM-dd";
                            SimpleDateFormat fc = new SimpleDateFormat(formatoFecha);
                            Date fecha;
                            try{
                                fecha = fc.parse(trozos[4]);
                                // Convertir el formato Date en Timestamp con el que trabaja la DB
                                Timestamp f = new Timestamp(fecha.getTime());
                                Inventario nuevo = new Inventario(id, nombre, cantidad, comentario, f);
                                InventarioDAO.insertar(nuevo);
                            } catch (SQLException e){
                                System.out.println("Error se sentencia SQL");
                            } catch (ParseException e) {
                                System.out.println("Error de conversión de fecha");;
                            }
                        }
                    comando = sc.nextLine().toUpperCase();
                        break;
                case "DELETE":
                    String eliminar = sc.nextLine();
                    Inventario objeto = new Inventario();
                    objeto.setId(parseInt(eliminar));
                    InventarioDAO.borrar(objeto);
                    comando = sc.nextLine().toUpperCase();
                    break;
                case "UPDATE":
                    Inventario ob = new Inventario();
                    String actualizar = sc.nextLine();
                    ob.setId(parseInt(actualizar));
                    InventarioDAO.update(ob);
                    comando = sc.nextLine().toUpperCase();
                    break;
            }
        }
    }
}
