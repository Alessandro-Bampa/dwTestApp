package dw.testApp;


import com.fasterxml.jackson.annotation.JsonProperty;
import dw.testApp.application.port.ItemManager;
import io.dropwizard.core.Configuration;
import io.dropwizard.db.DataSourceFactory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class TrueConfiguration extends Configuration {
    // TODO: implement service configuration
    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Strangers";

    @Valid
    @NotNull
    private DataSourceFactory postgres = new DataSourceFactory();

    @JsonProperty("itemManager") private ItemManager itemManager;

   /*
    @Valid
    @NotNull
    private MongoDbConfiguration injectionConfiguration;
*/

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.postgres = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return postgres;
    }

    /*
    @JsonProperty("mongo")
    public void setDataSourceFactoryMongo(DataSourceFactory factory) {
        this.mongo = factory;
    }

    @JsonProperty("mongo")
    public DataSourceFactory getDataSourceFactoryMongo() {
        return mongo;
    }
    */

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
}
