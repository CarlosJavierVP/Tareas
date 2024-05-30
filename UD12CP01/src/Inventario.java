import java.util.Date;

/**
 * Clase Inventario
 *
 * @author Carlos Javier Valenzuela Pintanel
 */
public class Inventario {
    // attributes
    private int id;
    private String nombre;
    private int cantidad;
    private String comentario;
    private Date fecha;


    // constructor

    public Inventario() {
    }

    public Inventario(int id, String nombre, int cantidad, String comentario, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    // getter & setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // methods
    @Override
    public String toString() {
        return "[Id:" + this.getId() + " Nombre:" + this.getNombre() + " Cantidad:" + this.getCantidad() + " Comentario:" + this.getComentario() + " Fecha:" + this.getFecha() + "]";
    }
}
