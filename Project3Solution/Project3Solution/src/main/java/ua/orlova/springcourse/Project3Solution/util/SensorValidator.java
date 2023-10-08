package ua.orlova.springcourse.Project3Solution.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.orlova.springcourse.Project3Solution.models.Sensor;
import ua.orlova.springcourse.Project3Solution.services.SensorService;

import java.util.Optional;

@Component
public class SensorValidator implements Validator
{
    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
Sensor sensor=(Sensor)target;
Optional<Sensor> foundSensor=sensorService.findByName(sensor.getName());
if(foundSensor.isPresent()){
    errors.rejectValue("name", "There is already the sensor with same name");
}
    }
}
