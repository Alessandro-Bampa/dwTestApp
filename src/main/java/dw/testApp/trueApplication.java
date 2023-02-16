package dw.testApp;

import dw.testApp.health.TemplateHealthCheck;
import dw.testApp.presentation.HelloWorldResource;

import dw.testApp.presentation.adapter.ItemRestServiceImpl;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.jdbi3.JdbiFactory;

import org.jdbi.v3.core.Jdbi;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class trueApplication extends Application<TrueConfiguration> {

    public static void main(final String[] args) throws Exception {
        new trueApplication().run(args);
    }

    @Override
    public String getName() {
        return "testApp";
    }

    @Override
    public void initialize(Bootstrap<TrueConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)));
/*
        GuiceBundle<TrueConfiguration> guiceBundle = GuiceBundle.builder().enableAutoConfig(getClass().getPackage().getName()).modules(
                new ConfigModule()).build();
        bootstrap.addBundle(guiceBundle);

 */
    }

    @Override
    public void run(final TrueConfiguration configuration,
                    final Environment environment) throws Exception {
        // TODO: implement application
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);

        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

        final ItemRestServiceImpl itemRestService = new ItemRestServiceImpl();
        environment.jersey().register(itemRestService);

    }

}
