package Record;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    private String manufactureYr;

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


    public String getRegMark() {
        return regMark;
    }

    public void setRegMark(String regMark) {
        this.regMark = regMark;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public boolean isValidTax() {
        return validTax;
    }

    public void setIsValidTax(boolean isValidTax) {
        this.validTax = isValidTax;
    }

    public String getDateOfLiability() {
        return dateOfLiability;
    }

    public void setDateOfLiability(String dateOfLiability) {
        this.dateOfLiability = dateOfLiability;
    }

    public boolean isValidMot() {
        return validMot;
    }

    public void setIsValidMot(boolean isValidMot) {
        this.validMot = isValidMot;
    }

    public String getMotExpiryDt() {
        return motExpiryDt;
    }

    public void setMotExpiryDt(String motExpiryDt) {
        this.motExpiryDt = motExpiryDt;
    }

    public String getFirstRegDt() {
        return firstRegDt;
    }

    public void setFirstRegDt(String firstRegDt) {
        this.firstRegDt = firstRegDt;
    }

    public String getManufactureYr() {
        return manufactureYr;
    }

    public void setManufactureYr(String manufactureYr) {
        this.manufactureYr = manufactureYr;
    }

    public int getCylinderCapacity() {
        return cylinderCapacity;
    }

    public void setCylinderCapacity(int cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }

    public int getCo2Emissions() {
        return co2Emissions;
    }

    public void setCo2Emissions(int co2Emissions) {
        this.co2Emissions = co2Emissions;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getTypeApproval() {
        return typeApproval;
    }

    public void setTypeApproval(String typeApproval) {
        this.typeApproval = typeApproval;
    }

    public String getWheelplan() {
        return wheelplan;
    }

    public void setWheelplan(String wheelplan) {
        this.wheelplan = wheelplan;
    }

    public String getRevenueWeight() {
        return revenueWeight;
    }

    public void setRevenueWeight(String revenueWeight) {
        this.revenueWeight = revenueWeight;
    }

    @Override
    public String toString() {
        return "VehicleRecord{" +
                "regMark='" + regMark + '\'' +
                ", make='" + make + '\'' +
                ", isValidTax=" + validTax +
                ", dateOfLiability='" + dateOfLiability + '\'' +
                ", isValidMot=" + validMot +
                ", motExpiryDt='" + motExpiryDt + '\'' +
                ", firstRegDt='" + firstRegDt + '\'' +
                ", manufactureYr='" + manufactureYr + '\'' +
                ", cylinderCapacity=" + cylinderCapacity +
                ", co2Emissions=" + co2Emissions +
                ", fuelType='" + fuelType + '\'' +
                ", status='" + status + '\'' +
                ", colour='" + colour + '\'' +
                ", typeApproval='" + typeApproval + '\'' +
                ", wheelplan='" + wheelplan + '\'' +
                ", revenueWeight='" + revenueWeight + '\'' +
                '}';
    }

}
