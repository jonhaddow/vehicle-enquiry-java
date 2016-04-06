package Health;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by Jon Haddow on 14/01/2016.
 */
public class VehicleEnquiryHealthCheck extends HealthCheck {

    public VehicleEnquiryHealthCheck() {

    }

    protected HealthCheck.Result check() throws Exception {
        return HealthCheck.Result.healthy();
    }

}
