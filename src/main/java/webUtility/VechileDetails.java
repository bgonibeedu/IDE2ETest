package webUtility;
public class VechileDetails {
    private String registrationNumber;
    private String make;
    private String colour;

    public VechileDetails(String registrationNumber, String make, String colour) {
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.colour = colour;
    }
    
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
