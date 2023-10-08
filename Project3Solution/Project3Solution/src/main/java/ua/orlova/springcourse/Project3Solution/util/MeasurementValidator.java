package ua.orlova.springcourse.Project3Solution.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.orlova.springcourse.Project3Solution.models.Measurement;
import ua.orlova.springcourse.Project3Solution.services.SensorService;

@Component
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement=(Measurement) target;
if(sensorService.findByName(measurement.getSensor().getName()).isEmpty()){
    errors.rejectValue("sensor", "There are no sensors registered with this name");
}
    }
}
