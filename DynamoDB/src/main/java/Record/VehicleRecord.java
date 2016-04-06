package Record;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

/**
 * Created by Jon Haddow on 14/01/2016.
 */
public class VehicleRecord {

    public VehicleRecord() {}

    @JsonProperty
    private String regMark;

    @JsonProperty
    private String make;

    @JsonProperty
    private boolean validTax;

    @JsonProperty
    private String dateOfLiability;

    @JsonProperty
    private boolean validMot;

    @JsonProperty
    private String motExpiryDt;

    @JsonProperty
    private String firstRegDt;

    @JsonProperty
    private int manufactureYr;

    @JsonProperty
    private int cylinderCapacity;

    @JsonProperty
    private int co2Emissions;

    @JsonProperty
    private String fuelType;

    @JsonProperty
    private String status;

    @JsonProperty
    private String colour;

    @JsonProperty
    private String typeApproval;

    @JsonProperty
    private String wheelplan;

    @JsonProperty
    private String revenueWeight;

    public void setRegMark(String regMark) {
        this.regMark = regMark;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setIsValidTax(boolean isValidTax) {
        this.validTax = isValidTax;
    }

    public void setDateOfLiability(String dateOfLiability) {
        this.dateOfLiability = dateOfLiability;
    }

    public void setIsValidMot(boolean isValidMot) {
        this.validMot = isValidMot;
    }

    public void setMotExpiryDt(String motExpiryDt) {
        this.motExpiryDt = motExpiryDt;
    }

    public void setFirstRegDt(String firstRegDt) {
        this.firstRegDt = firstRegDt;
    }

    public void setManufactureYr(int manufactureYr) {
        this.manufactureYr = manufactureYr;
    }

    public void setCylinderCapacity(int cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }

    public void setCo2Emissions(int co2Emissions) {
        this.co2Emissions = co2Emissions;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setTypeApproval(String typeApproval) {
        this.typeApproval = typeApproval;
    }

    public void setWheelplan(String wheelplan) {
        this.wheelplan = wheelplan;
    }

    public void setRevenueWeight(String revenueWeight) {
        this.revenueWeight = revenueWeight;
    }

}
