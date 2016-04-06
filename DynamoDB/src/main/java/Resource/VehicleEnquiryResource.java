package Resource;

import Record.VehicleRecord;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

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

        AmazonDynamoDBClient client = new AmazonDynamoDBClient()
                .withRegion(Regions.EU_WEST_1);

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("VehicleEnquiry");

        HashMap<String, String> nameMap = new HashMap<>();
        nameMap.put("#vrn", "vrn");

        HashMap<String, Object> valueMap = new HashMap<>();
        valueMap.put(":vrn", vrn);

        QuerySpec querySpec = new QuerySpec()
                .withKeyConditionExpression("#vrn = :vrn")
                .withNameMap(new NameMap().with("#vrn", "vrn"))
                .withValueMap(valueMap);

        VehicleRecord vehicleRecord = new VehicleRecord();

        try {
            ItemCollection<QueryOutcome> items = table.query(querySpec);
            for (Item item : items) {
                vehicleRecord.setRegMark(item.getString("vrn"));
                vehicleRecord.setMake(item.getString("make"));
                vehicleRecord.setIsValidTax(item.getBoolean("validTax"));
                vehicleRecord.setDateOfLiability(item.getString("dateOfLiability"));
                vehicleRecord.setIsValidMot(item.getBoolean("validMot"));
                vehicleRecord.setFirstRegDt(item.getString("firstRegDt"));
                vehicleRecord.setManufactureYr(item.getInt("manufactureYr"));
                vehicleRecord.setCylinderCapacity(item.getInt("cylinderCapacity"));
                vehicleRecord.setCo2Emissions(item.getInt("co2Emissions"));
                vehicleRecord.setFuelType(item.getString("fuelType"));
                vehicleRecord.setStatus(item.getString("status"));
                vehicleRecord.setColour(item.getString("colour"));
                vehicleRecord.setTypeApproval(item.getString("typeApproval"));
                vehicleRecord.setWheelplan(item.getString("wheelplan"));
            }
        } catch (Exception ignored) {
        }

        return Response.ok(vehicleRecord).build();
    }

}
