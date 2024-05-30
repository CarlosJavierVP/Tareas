import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Gestionar la BD de inventarios
 *
 * @author Carlos Javier Valenzuela Pintanel
 */

public class InventarioDAO {

    public static Statement statement = null;
    public static ResultSet rs = null;
    public static void mostrar() {
        String sql = "SELECT * FROM inventario";
        // añadir los objetos de inventario al arrayList, mediante while-next
        List<Inventario> lista = new ArrayList<>();
        try{
            statement = ConexionBD.conexion.createStatement();
            //hacer consulta  la BD
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Inventario objeto = new Inventario();
                objeto.setId(rs.getInt("id"));
                objeto.setNombre(rs.getString("nombre"));
                objeto.setCantidad(rs.getInt("cantidad"));
                objeto.setComentario(rs.getString("comentario"));
                objeto.setFecha(rs.getDate("Fecha"));
                lista.add(objeto);
            }
        } catch (SQLException e) {
            System.out.println("Error en realizar la consulta");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la consulta");
            }
        }
        // Mostrar la lista en la que se ha añadido los objetos del inventario
        for (Inventario i : lista){
            System.out.println(i);
        }

    }

    public static int insertar(Inventario objeto) throws SQLException {
        StringBuilder sql;
        int resultado;

        sql = new StringBuilder("INSERT INTO inventario (id, nombre, cantidad, comentario, fecha) VALUES ('");
        sql.append(objeto.getId()).append("','");
        sql.append(objeto.getNombre()).append("','");
        sql.append(objeto.getCantidad()).append("','");
        sql.append(objeto.getComentario()).append("','");
        sql.append(objeto.getFecha()).append("')");

        statement = ConexionBD.conexion.createStatement();
        resultado = statement.executeUpdate(String.valueOf(sql));

        return resultado;
    }

    public static void buscar(String nombre) {
        String sql = "SELECT * FROM inventario";
        Inventario resultado = new Inventario();
        List<Inventario> lista = new ArrayList<>();

        try{
            //hacer consulta de la DB
            statement = ConexionBD.conexion.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Inventario objeto = new Inventario();
                objeto.setId(rs.getInt("id"));
                objeto.setNombre(rs.getString("nombre"));
                objeto.setCantidad(rs.getInt("cantidad"));
                objeto.setComentario(rs.getString("comentario"));
                objeto.setFecha(rs.getDate("Fecha"));
                lista.add(objeto);
            }
        } catch (SQLException e) {
            System.out.println("Error en realizar la consulta");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la consulta");
            }
        }
        for (Inventario i : lista) {
            if (i.getNombre().equalsIgnoreCase(nombre)){
                resultado = i;
            }else{
                System.out.println("No se ha encontrado el objeto, inserte bien el nombre");
            }
        }
        System.out.println(resultado);

    }

    public static void borrar (Inventario objeto){
        int resultado = 0;
        String sql = "DELETE FROM inventario WHERE id =";
        sql += objeto.getId()+";";

        try{
            statement = ConexionBD.conexion.createStatement();
            resultado = statement.executeUpdate(sql);
        }catch (SQLException e){
            System.out.println("Error SQL");
        }
        System.out.println(resultado +" registro borrado correctamente");
    }

    public static void update (Inventario objeto){
        int actualizacion = 0;
        boolean modificado = false;
        String sql = "UPDATE inventario SET ";

        if (objeto.getNombre() != null){
            sql += "nombre='"+objeto.getNombre()+"'";
            modificado = true;
        }
        if (objeto.getCantidad() != 0){
            sql += "cantidad='"+objeto.getCantidad()+"'";
            modificado = true;
        }
        if (objeto.getComentario() != null){
            sql += "comentario='"+objeto.getComentario()+"'";
            modificado = true;
        }
        if (objeto.getFecha() != null){
            sql += "fecha='"+objeto.getFecha()+"'";
            modificado = true;
        }

        if (modificado){
            if (objeto.getId() != 0){
                sql += " WHERE id="+objeto.getId()+";";
                try{
                    statement = ConexionBD.conexion.createStatement();
                    actualizacion = statement.executeUpdate(sql);
                }catch (SQLException e){
                    System.out.println("Error SQL");
                }
            }
        }
        System.out.println(actualizacion + " registro actualizado correctamente");
    }

}
