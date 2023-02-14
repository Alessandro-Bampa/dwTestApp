package dw.testApp.api.response;

import dw.testApp.api.beans.ItemsEntity;
import jakarta.ws.rs.core.Response;

public class ItemResponse {

    private ItemsEntity item;

    private Response.Status status;

    public ItemsEntity getItem() {
        return item;
    }

    public void setItem(ItemsEntity item) {
        this.item = item;
    }

    public Response.Status getStatus() {
        return status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }
}
