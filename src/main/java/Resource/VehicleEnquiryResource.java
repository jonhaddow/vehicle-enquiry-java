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

    public static final String HOST = "jdbc:postgresql://localhost:5432/";
    public static final String DB_NAME = "VehicleEnquiry";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "password";

    public VehicleEnquiryResource() {
    }

    @GET
    @Timed
    @ExceptionMetered
    public Response getVehicle(@NotNull @QueryParam("vrn") String vrn,
                               @NotNull @QueryParam("make") String make) {

        Postgres postgres = new Postgres(
                HOST,
                DB_NAME,
                USERNAME,
                PASSWORD);
        ResultSet rs;
        VehicleRecord record = new VehicleRecord();
        try {
            if (postgres.connect()) {
                System.out.println("\nDatabase Connected\n");
                rs = postgres.execQuery("SELECT * FROM vehicles WHERE vrn = '"
                        + vrn + "'");
                while (rs.next()) {
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
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        Response response = Response.ok(record).build();

        return response;
    }

}
