package ua.orlova.springcourse.Project3Solution.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.orlova.springcourse.Project3Solution.dto.SensorDTO;
import ua.orlova.springcourse.Project3Solution.models.Sensor;
import ua.orlova.springcourse.Project3Solution.services.SensorService;
import ua.orlova.springcourse.Project3Solution.util.MeasurementErrorResponse;
import ua.orlova.springcourse.Project3Solution.util.MeasurementException;
import ua.orlova.springcourse.Project3Solution.util.SensorValidator;



import static ua.orlova.springcourse.Project3Solution.util.ErrorsUtil.returnErrorsToClient;


@RequestMapping("/sensors")
@RestController
public class SensorsController {
private final SensorService sensorService;
private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorsController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        Sensor sensor=convertToSensor(sensorDTO);
sensorValidator.validate(sensor, bindingResult);
if(bindingResult.hasErrors()){
    returnErrorsToClient(bindingResult);
}
sensorService.register(sensor);
return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
return modelMapper.map(sensorDTO, Sensor.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e){
        MeasurementErrorResponse response=new MeasurementErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
