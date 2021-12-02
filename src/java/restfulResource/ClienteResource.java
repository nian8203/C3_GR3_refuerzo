/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfulResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import restful.model.ClienteModel;
import restfulService.ClienteService;

/**
 *
 * @author nian
 */
@Path("clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ClienteResource {

    ClienteService cs = new ClienteService();

    @GET
    public List<ClienteModel> getCliente() {
        return cs.getClientes();
    }
    

    @Path("/{ProductoId}")
    @GET
    public ClienteModel getClienteId(@PathParam("ProductoId") int id) {
        return cs.getClienteId(id);
    }
    
    
    @POST
    public ClienteModel addCliente(String JSON){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ClienteModel cm = gson.fromJson(JSON, ClienteModel.class);
        return cs.addCliente(cm);
    }
    
    
     @PUT
    public ClienteModel actualizarCliente(String JSON){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ClienteModel cm = gson.fromJson(JSON, ClienteModel.class);
        return cs.actualizarCliente(cm);
    }
    
    @Path("/{ClienteId}")
    @DELETE
    public String deleteCliente(@PathParam("ClienteId") int id){
        System.out.println("entro metodo");
        return cs.deleteCliente(id);
    }

}
