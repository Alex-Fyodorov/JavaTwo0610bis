package lesson15;

public class Bike {
    public String model;
    public String serialNo;
    private int year;

    public Bike(String model, String serialNo, int year) {
        this.model = model;
        this.serialNo = serialNo;
        this.year = year;
    }

    public Bike() {
    }

    @SimpleAnnotation
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @SimpleAnnotation
    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @SimpleAnnotation
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @NotSimpleAnnotation(name = "myName", value = 11)
    private void setYearAndModel(int year, String model) {
        this.year = year;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "model='" + model + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", year=" + year +
                '}';
    }
}
