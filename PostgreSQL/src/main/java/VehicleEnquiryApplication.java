import Health.VehicleEnquiryHealthCheck;
import Resource.VehicleEnquiryResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Created by Jon Haddow on 14/01/2016.
 */
public class VehicleEnquiryApplication extends Application<VehicleEnquiryConfig> {

    public static void main(String[] args) throws Exception {
        new VehicleEnquiryApplication().run(args);
    }

    @Override
    public void run(VehicleEnquiryConfig config, Environment environment) {

        setupCORS(environment);

        VehicleEnquiryResource resource = new VehicleEnquiryResource();

        // REGISTER HEALTH CHECKS
        environment.healthChecks().register("vehicleenquiry",new VehicleEnquiryHealthCheck());

        // REGISTER RESOURCES
        environment.jersey().register(resource);

    }

    //This method allows cross origin resource sharing from Javascript sites.
    private void setupCORS(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");
    }
}
