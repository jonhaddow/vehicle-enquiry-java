package Resource;

import Record.Postgres;
import Record.VehicleRecord;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jon Haddow on 14/01/2016.
 */
@Path("/vehicles")
@Produces(MediaType.APPLICATION_JSON)
public class VehicleEnquiryResource {

    //Constants to connect to postgres database
    private static final String HOST =
            "jdbc:postgresql://horton.elephantsql.com:5432/";
    private static final String DB_NAME = "mbdgctad";
    private static final String USERNAME = "mbdgctad";
    private static final String PASSWORD = "Bh_VwvzWPSQlFUDEwLQ6xbksrxGbt6rg";

    /**
     * This GET Request takes a vehicle reg number and make
     * and returns vehicle information stored by DVLA.
     * @param vrn Vehicle Registration number
     * @param make Make of vehicle
     * @return HTTP Response. Ok if get request is successful.
     */
    @GET
    @Timed
    @ExceptionMetered
    public Response getVehicle(@NotNull @QueryParam("vrn") String vrn,
                               @NotNull @QueryParam("make") String make) {

        //Connect to postgres database
        Postgres postgres = new Postgres(
                HOST,
                DB_NAME,
                USERNAME,
                PASSWORD);

        // Set of data from vehicle database entry when query has been executed
        ResultSet rs;

        // Create vehicle record class to hold vehicle data
        VehicleRecord record = new VehicleRecord();

        try {
            if (postgres.connect()) {

                // Execute SQL Query against postgres database
                rs = postgres.execQuery("SELECT * FROM vehicles WHERE vrn = '" + vrn + "'");

                // If there is a row from vehicles returned
                if (rs.next()) {

                    // If the vehicle make entered, doesn't belong to the vrn...
                    if (!rs.getString(2).equals(make)) {

                        // ...return an unauthorised access response
                        return Response.status(Response.Status.UNAUTHORIZED).build();
                    }

                    // Transfer data from query result to vehicle record
                    record.setRegMark(rs.getString(1));
                    record.setMake(rs.getString(2));
                    record.setIsValidTax(rs.getBoolean(3));
                    record.setDateOfLiability(rs.getDate(4));
                    record.setIsValidMot(rs.getBoolean(5));
                    record.setMotExpiryDt(rs.getDate(6));
                    record.setFirstRegDt(rs.getDate(7));
                    record.setManufactureYr(rs.getInt(8));
                    record.setCylinderCapacity(rs.getInt(9));
                    record.setCo2Emissions(rs.getInt(10));
                    record.setFuelType(rs.getString(11));
                    record.setStatus(rs.getString(12));
                    record.setColour(rs.getString(13));
                    record.setTypeApproval(rs.getString(14));
                    record.setWheelplan(rs.getString(15));
                    record.setRevenueWeight(rs.getString(16));
                } else {

                    // Respond with a NOT FOUND status
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // Respond with vehicle record json
        return Response.ok(record).build();
    }
}
