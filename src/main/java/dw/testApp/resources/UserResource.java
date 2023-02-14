package dw.testApp.resources;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.config.ConfigRegistry;

public class UserResource {

    private ConfigRegistry config;
    public UserResource(Jdbi jdbi){
        this.config = jdbi.getConfig();
    }

    public ConfigRegistry getConfig() {
        return config;
    }

    public void setConfig(ConfigRegistry config) {
        this.config = config;
    }
}
