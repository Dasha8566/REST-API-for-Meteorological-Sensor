package ua.orlova.springcourse.Project3Solution.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@Entity
@Table(name="Measurement")
public class Measurement {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="value")
    @NotEmpty(message = "Value should not be empty")
    @Min(-100)
    @Max(100)
    private double value;

    @Column(name="raining")
    @NotEmpty(message = "Raining value should not be empty")
    private boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    @NotEmpty(message = "Sensor should not be empty")
    private Sensor sensor;

    @Column(name="measurement_date_time")
    @NotEmpty
    private LocalDateTime measurementDateTime;

    public Measurement() {
    }

    public Measurement(double value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getMeasurementDateTime() {
        return measurementDateTime;
    }

    public void setMeasurementDateTime(LocalDateTime measurementDateTime) {
        this.measurementDateTime = measurementDateTime;
    }
}
