package dw.testApp.repository.adapter.postgers.daos;

import dw.testApp.api.beans.Item;
import dw.testApp.model.postgres.ItemEntity;
import dw.testApp.repository.port.postgres.ItemRepository;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;
import java.util.Optional;

public interface ItemsDAO {
    static final String tableName = "items";
    @SqlQuery("SELECT * FROM " + tableName)
    @RegisterBeanMapper(Item.class)
    List<ItemEntity> itemEntityList();

    @SqlQuery("SELECT * FROM " + tableName + " WHERE token = :itemId")
    @RegisterBeanMapper(Item.class)
    Optional<ItemEntity> getItem(@Bind("itemId") int itemId);
}
