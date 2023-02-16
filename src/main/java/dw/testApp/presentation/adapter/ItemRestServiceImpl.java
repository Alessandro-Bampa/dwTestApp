package dw.testApp.presentation.adapter;

import dw.testApp.api.response.ItemResponse;
import dw.testApp.application.port.ItemManager;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Path("item")
public class ItemRestServiceImpl {
    final private ItemManager itemManager;


    private static final Logger logger = Logger.getLogger(ItemRestServiceImpl.class);
    @Inject
    public ItemRestServiceImpl(final ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ItemResponse getItemById(@PathParam("id") String itemId) {
        ItemResponse res = new ItemResponse();
        try {
            return itemManager.getItem(itemId);
        } catch (Exception e) {
            logger.error("catching exception: " + itemId, e);
        }
        return res;
    }

    @Path("test")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String test() {
        return itemManager.getTest();
    }
}

