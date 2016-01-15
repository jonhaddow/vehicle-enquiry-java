import Health.VehicleEnquiryHealthCheck;
import Resource.VehicleEnquiryResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Created by Jon Haddow on 14/01/2016.
 */
public class VehicleEnquiryApplication extends Application<VehicleEnquiryConfig> {

    public static void main(String[] args) throws Exception {
        new VehicleEnquiryApplication().run(args);
    }

    @Override
    public void run(VehicleEnquiryConfig config, Environment environment) {

        final HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(false);

        VehicleEnquiryResource resource = new VehicleEnquiryResource(htmlUnitDriver);

        // REGISTER HEALTH CHECKS
        environment.healthChecks().register("vehicleenquiry",new VehicleEnquiryHealthCheck());

        // REGISTER RESOURCES
        environment.jersey().register(resource);
    }
}
