package Resource;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Jon Haddow on 14/01/2016.
 */
@Path("/vehicles")
@Produces(MediaType.APPLICATION_JSON)
public class VehicleEnquiryResource {

    /**
     * This GET Request takes a vehicle reg number and make
     * and returns vehicle information stored by DVLA.
     *
     * @param vrn  Vehicle Registration number
     * @param make Make of vehicle
     * @return HTTP Response. Ok if get request is successful.
     */
    @GET
    @Timed
    @ExceptionMetered
    public Response getVehicle(@NotNull @QueryParam("vrn") String vrn,
                               @NotNull @QueryParam("make") String make) {

        AmazonDynamoDBClient client = new AmazonDynamoDBClient(awsCredentials())
                .withEndpoint("http://dynamodb.eu-west-1.amazonaws.com");

        DynamoDB dynamoDB = new DynamoDB(client);

        String tableName = "VehicleEnquiry";

        return Response.ok().build();
    }

    public AWSCredentials awsCredentials()
    {
        return new BasicAWSCredentials("AKIAJI7Z57FX73B7QEMA", "OOpCw/IaT8BOIkLQ9fEopqD0TtpfnehCWDZWYc+7");
    }
}
