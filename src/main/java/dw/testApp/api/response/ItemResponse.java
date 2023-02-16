package dw.testApp.api.response;

import dw.testApp.api.beans.Item;
import jakarta.ws.rs.core.Response;


public class ItemResponse {

    private Item item;

    private Response.Status status;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Response.Status getStatus() {
        return status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }
}
