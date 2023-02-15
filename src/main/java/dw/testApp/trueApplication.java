package dw.testApp;

import configuration.DependencyInjectionBundle;
import dw.testApp.health.TemplateHealthCheck;
import dw.testApp.presentation.HelloWorldResource;

import dw.testApp.presentation.ItemsResource;
import dw.testApp.presentation.UserResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.jdbi3.JdbiFactory;

import org.jdbi.v3.core.Jdbi;

public class trueApplication extends Application<trueConfiguration> {

    public static void main(final String[] args) throws Exception {
        new trueApplication().run(args);
    }

    @Override
    public String getName() {
        return "testApp";
    }

    @Override
    public void initialize(final Bootstrap<trueConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final trueConfiguration configuration,
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

        //environment.jersey().register(new UserResource(jdbi));

        final ItemsResource itemsResource = new ItemsResource(jdbi);
        environment.jersey().register(itemsResource);

        final DependencyInjectionBundle dependencyInjectionBundle = new DependencyInjectionBundle();
        dependencyInjectionBundle.run(configuration.getInjectionConfiguration(), environment);
    }

}
