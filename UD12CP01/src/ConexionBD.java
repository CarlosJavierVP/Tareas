import java.sql.*;

/**
 * Clase conectar a la Base de datos
 *
 * @author Carlos Javier Valenzuela Pintanel
 */
public class ConexionBD {
    public static Connection conexion = null;
    //public static Statement statement = null;
    private final static String className = "com.mysql.cj.jdbc.Driver";
    private final static String url = "jdbc:mysql://localhost/proyecto_ja?useSSL=FALSE&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    private final static String user = "root";
    private final static String password = "123456";

    public static void conexion() {
        try {
            Class.forName(className);
            conexion = DriverManager.getConnection(url, user, password);
            // Se usa para ejecutar una sentencia SQL
            //statement = conexion.createStatement();


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Driver no encontrado | Error de SQL");
        }

        // No cierro el flujo porque se cierra al llamar al método desconexion()
    }

    public static void desconexion() {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión");
        }
    }
/*
    public static ResultSet consulta(String sql) {
        ResultSet rs = null;
        try {
            statement = conexion.createStatement();
            // el statement está inicializado en la clase, y creado a través del método conexion()
            // el siguiente método se usa para ejecutar la sentencia y el ResultSet es el resultado de ejecutar una sentencia
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error al hacer la consulta");
        }
        return rs;
    }
*/
    /*
    public static int actualiza(String sql) {
        int resultado= 0;
        try {
            statement = conexion.createStatement();
            resultado = statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error al actualizar la BD");
        }
        return resultado;
    }
    */
}
