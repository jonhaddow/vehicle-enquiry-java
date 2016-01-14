package Resource;

import Record.VehicleRecord;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Jon Haddow on 14/01/2016.
 */
@Path("/vehicles")
@Produces(MediaType.APPLICATION_JSON)
public class VehicleEnquiryResource {

    static {
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.SEVERE);
    }

    private final HtmlUnitDriver htmlUnitDriver;


    public VehicleEnquiryResource(HtmlUnitDriver htmlUnitDriver) {
        this.htmlUnitDriver = htmlUnitDriver;
    }


    @GET
    @Timed
    @ExceptionMetered
    public Response getVehicle(@NotNull @QueryParam("vrm") String vrm,
                               @NotNull @QueryParam("make") String make) {

        htmlUnitDriver.get("https://www.vehicleenquiry.service.gov.uk/");

        htmlUnitDriver.findElementById("MainContent_txtSearchVrm").sendKeys(vrm);

        htmlUnitDriver.findElementById("MainContent_MakeTextBox")
                .sendKeys(make.toUpperCase());

        htmlUnitDriver.findElementById("MainContent_butSearch").submit();

        VehicleRecord vehicleRecord = new VehicleRecord();

        try {

            vehicleRecord.setRegMark(vrm.toUpperCase());
            if (htmlUnitDriver.findElements(By.className("isValidTax")).size() != 0) {
                // We now know that it has valid tax
                vehicleRecord.setIsValidTax(true);
                WebElement tax = htmlUnitDriver.findElementByClassName("isValidTax");
                String dol = tax.findElement(By.tagName("p")).getText();

                if (dol.length() >=9) {
                    vehicleRecord.setDateOfLiability(dol.substring(9));
                } else {
                    vehicleRecord.setDateOfLiability(null);
                }

            } else {
                vehicleRecord.setIsValidTax(false);
                WebElement tax = htmlUnitDriver.findElementByClassName("isInvalidTax");
                vehicleRecord.setDateOfLiability(
                        tax.findElement(By.tagName("p")).getText().substring(9));
            }

            if (htmlUnitDriver.findElements(By.className("isValidMot")).size() != 0) {
                // We now know that it has valid MOT
                vehicleRecord.setIsValidMot(true);
                WebElement mot = htmlUnitDriver.findElementByClassName("isValidMot");
                String motInfo = mot.findElement(By.tagName("p")).getText();
                if (motInfo.contains("No details held")) {
                    vehicleRecord.setMotExpiryDt(null);
                } else {
                    vehicleRecord.setMotExpiryDt(motInfo.substring(9));
                }
            } else {
                vehicleRecord.setIsValidMot(false);
                WebElement mot = htmlUnitDriver.findElementByClassName("isInvalidMot");
                vehicleRecord.setMotExpiryDt(
                        mot.findElement(By.tagName("p")).getText().substring(9));
            }

            WebElement unorderedList = htmlUnitDriver.findElementByClassName("ul-data");
            List<WebElement> newList = unorderedList.findElements(By.tagName("li"));

            for (WebElement element : newList) {

                if (element.findElement(By.tagName("span")).
                        getText().contains("Vehicle make")) {
                    vehicleRecord.setMake(element.findElement(By.tagName("strong")).getText().trim());
                }

                if (element.findElement(By.tagName("span")).
                        getText().contains("first registration")) {
                    vehicleRecord.setFirstRegDt(element.findElement(By.tagName("strong")).getText().trim());
                }

                if (element.findElement(By.tagName("span")).
                        getText().contains("manufacture")) {
                    vehicleRecord.setManufactureYr(element.findElement(By.tagName("strong")).getText().trim());
                }

                if (element.findElement(By.tagName("span")).
                        getText().contains("capacity")) {
                    String tmpCapacity = element.findElement(By.tagName("strong")).getText().trim();
                    String capacity = tmpCapacity.substring(0, tmpCapacity.length()-2);
                    vehicleRecord.setCylinderCapacity(Integer.parseInt(capacity));
                }

                if (element.findElement(By.tagName("span")).
                        getText().contains("Emissions")) {
                    String tmpEmissions = element.findElement(By.tagName("strong")).getText().trim();
                    if (tmpEmissions.equals("Not available")) {
                        vehicleRecord.setCo2Emissions(0);
                    } else {
                        String emissions = tmpEmissions.substring(0, tmpEmissions.length()-5);
                        vehicleRecord.setCo2Emissions(Integer.parseInt(emissions));
                    }
                }

                if (element.findElement(By.tagName("span")).
                        getText().contains("Fuel type")) {
                    vehicleRecord.setFuelType(element.findElement(By.tagName("strong")).getText().trim());
                }

                if (element.findElement(By.tagName("span")).
                        getText().contains("status")) {
                    vehicleRecord.setStatus(element.findElement(By.tagName("strong")).getText().trim());
                }

                if (element.findElement(By.tagName("span")).
                        getText().contains("colour")) {
                    vehicleRecord.setColour(element.findElement(By.tagName("strong")).getText().trim());
                }

                if (element.findElement(By.tagName("span")).
                        getText().contains("type approval")) {
                    vehicleRecord.setTypeApproval(element.findElement(By.tagName("strong")).getText().trim());
                }

                if (element.findElement(By.tagName("span")).
                        getText().contains("Wheelplan")) {
                    vehicleRecord.setWheelplan(element.findElement(By.tagName("strong")).getText().trim());
                }

                if (element.findElement(By.tagName("span")).
                        getText().contains("Revenue weight")) {
                    vehicleRecord.setRevenueWeight(element.findElement(By.tagName("strong")).getText().trim());
                }

            }

        } catch (Exception e) {
            Response response = Response.status(Response.Status.NOT_FOUND).build();
            return response;
        }

        Response response = Response.ok(vehicleRecord).build();

        return response;
    }

}
