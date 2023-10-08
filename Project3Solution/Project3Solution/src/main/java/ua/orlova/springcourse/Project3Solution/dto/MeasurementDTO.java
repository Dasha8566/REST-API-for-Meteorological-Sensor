package ua.orlova.springcourse.Project3Solution.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class MeasurementDTO {

    @NotEmpty(message = "Value should not be empty")
    @Min(-100)
    @Max(100)
    private double value;

    @NotEmpty(message = "Raining value shoud not be empty")
    private boolean raining;

    @NotEmpty(message = "Sensor should not be empty")
    private SensorDTO sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
