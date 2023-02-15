package dw.testApp.presentation;

import dw.testApp.api.beans.ItemsEntity;
import dw.testApp.api.response.ItemResponse;
import dw.testApp.db.daos.ItemsDAO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jdbi.v3.core.Jdbi;
import java.util.List;

@Path("item")
public class ItemsResource {

    private Jdbi database = null;

    public ItemsResource(Jdbi jdbi) {
        this.database = jdbi;

        /* Default installed by DropWizard
        SqlObjectPlugin objPlugin = new SqlObjectPlugin();
        database.installPlugin(objPlugin); */
    }

    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemsEntity> itemsEntityList(){
        List<ItemsEntity> items = database.withExtension(ItemsDAO.class, dao -> dao.itemEntityList());
        return items;
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ItemResponse getItemById(@PathParam("id") int itemId) {
        ItemResponse response = new ItemResponse();
        try{
            ItemsEntity item =  database.withExtension(ItemsDAO.class, dao -> dao.itemEntity(itemId)).orElseThrow();
            response.setItem(item);
            response.setStatus(Response.Status.OK);
        } catch(Exception e) {
            response.setStatus(Response.Status.NOT_FOUND);
        }
        return response;
    }


}
