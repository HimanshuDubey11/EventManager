package project.himanshu.com.eventmanager;

import java.util.Date;

public class Events {

    private String name;
    private String description;
    private Date startDate;
    private String type;
    private double fee;


    public Events(String name, String description, Date startDate, String type, double fee) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.type = type;
        this.fee = fee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
