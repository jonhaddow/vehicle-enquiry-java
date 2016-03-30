package Resource;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

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

    private Document documentResult;

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

        // Connect to the running MongoDB instance on mLab.com
        MongoClient mongoClient = new MongoClient(
                new MongoClientURI("mongodb://user:password@ds019488.mlab.com:19488/vehicle-enquiry"));

        // Access test database
        MongoDatabase db = mongoClient.getDatabase("vehicle-enquiry");

        // Look at all documents in collection
        FindIterable<Document> iterable = db.getCollection("VehicleEnquiry").find(
                new Document("vrn", vrn).append("make", make));

        // Take and store result as documentResult
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                documentResult = document;
            }
        });

        // If there is no response return response NOT FOUND
        if (documentResult == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Respond with json document
        return Response.ok(documentResult.toJson()).build();
    }
}
