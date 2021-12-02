package restfulService;
import java.util.ArrayList;
import java.util.List;
import restful.model.ClienteModel;
import restful.model.Conexion;
import java.sql.*;

/**
 *
 * @author nian
 */
public class ClienteService {   

    public List<ClienteModel> getClientes() {

        List<ClienteModel> listaClientes = new ArrayList<>();

        try {

            Connection conn = Conexion.getConnection();
            PreparedStatement pst = conn.prepareStatement("select *from clientes");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ClienteModel cm = new ClienteModel();
                cm.setId(rs.getInt("id"));
                cm.setNombre(rs.getString("nombre"));
                cm.setApellido(rs.getString("apellido"));
                cm.setDireccion(rs.getString("direccion"));
                cm.setTelefono(rs.getString("telefono"));
                cm.setCorreo(rs.getString("correo"));
                listaClientes.add(cm);
            }

        } catch (SQLException e) {
            System.out.println("Error1 = "+e);
        }
        return listaClientes;
    }
    
    
    public ClienteModel getClienteId(int id){
        
        ClienteModel cm = new ClienteModel();
        
        try {
            
            Connection conn = Conexion.getConnection();
            PreparedStatement pst = conn.prepareStatement("select *from clientes where id = ?");
            pst.setInt(1, id);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {                
                cm.setId(rs.getInt("id"));
                cm.setNombre(rs.getString("nombre"));
                cm.setApellido(rs.getString("apellido"));
                cm.setDireccion(rs.getString("direccion"));
                cm.setTelefono(rs.getString("telefono"));
                cm.setCorreo(rs.getString("correo"));
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR2 = "+e);
        }
        return cm;
    }
    
    
    public ClienteModel addCliente(ClienteModel cm){
        
        try {
            
            Connection conn = Conexion.getConnection();
            PreparedStatement pst = conn.prepareStatement("insert into clientes values(?,?,?,?,?,?)");
            pst.setInt(1, cm.getId());
            pst.setString(2, cm.getNombre().trim());
            pst.setString(3, cm.getApellido().trim());
            pst.setString(4, cm.getDireccion().trim());
            pst.setString(5, cm.getTelefono().trim());
            pst.setString(6, cm.getCorreo().trim());
            
            pst.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("ERROR3 = "+e);
            return null;
        }
        
        return cm;
    }
    
    public ClienteModel actualizarCliente(ClienteModel cm){
        
        try {
            
            Connection conn = Conexion.getConnection();
            PreparedStatement pst = conn.prepareStatement("update clientes set  nombre = ?, apellido = ?, direccion = ?, telefono = ?, correo = ? where id = ?");
            
            pst.setString(1, cm.getNombre().trim());
            pst.setString(2, cm.getApellido().trim());
            pst.setString(3, cm.getDireccion().trim());
            pst.setString(4, cm.getTelefono().trim());
            pst.setString(5, cm.getCorreo().trim());
            pst.setInt(6, cm.getId());
            
            pst.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("ERROR3 = "+e.getMessage());
            return null;
        }
        
        return cm;        
        
    }
    
    
    public String deleteCliente(int id){
        System.out.println("id = "+id);
        try {
            Connection conn = Conexion.getConnection();
            PreparedStatement pst = conn.prepareStatement("delete from clientes where id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar"+e.getMessage());
            return "{\"Accion\":\"Error\"}";
        }
        return "{\"Accion\":\"Registro Borrado\"}";
    }
    

}
