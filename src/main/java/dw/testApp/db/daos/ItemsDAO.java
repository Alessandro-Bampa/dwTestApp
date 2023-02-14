package dw.testApp.db.daos;

import dw.testApp.api.beans.ItemsEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;
import java.util.Optional;

public interface ItemsDAO {
    static final String tableName = "items";
    @SqlQuery("SELECT * FROM " + tableName)
    @RegisterBeanMapper(ItemsEntity.class)
    List<ItemsEntity> itemEntityList();

    @SqlQuery("SELECT * FROM " + tableName + " WHERE token = :itemId")
    @RegisterBeanMapper(ItemsEntity.class)
    Optional<ItemsEntity> itemEntity(@Bind("itemId") int itemId);
}
