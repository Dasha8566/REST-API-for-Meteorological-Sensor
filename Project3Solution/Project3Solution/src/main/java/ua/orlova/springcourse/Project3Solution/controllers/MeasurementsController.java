package ua.orlova.springcourse.Project3Solution.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.orlova.springcourse.Project3Solution.dto.MeasurementDTO;
import ua.orlova.springcourse.Project3Solution.dto.MeasurementsResponse;
import ua.orlova.springcourse.Project3Solution.models.Measurement;
import ua.orlova.springcourse.Project3Solution.services.MeasurementService;
import ua.orlova.springcourse.Project3Solution.util.MeasurementErrorResponse;
import ua.orlova.springcourse.Project3Solution.util.MeasurementException;
import ua.orlova.springcourse.Project3Solution.util.MeasurementValidator;

import java.util.stream.Collectors;

import static ua.orlova.springcourse.Project3Solution.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

private final MeasurementValidator measurementValidator;
private final ModelMapper modelMapper;
private final MeasurementService measurementService;

    @Autowired
    public MeasurementsController(MeasurementValidator measurementValidator, ModelMapper modelMapper, MeasurementService measurementService) {
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
        this.measurementService=measurementService;
    }




    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
Measurement measurement=convertToMeasurement(measurementDTO);
measurementValidator.validate(measurement, bindingResult);
if(bindingResult.hasErrors()){
    returnErrorsToClient(bindingResult);
}
measurementService.addMeasurement(measurement);
return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public MeasurementsResponse getMeasurements(){
        return new MeasurementsResponse(measurementService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount(){
        return measurementService.findAll().stream().filter(Measurement::isRaining).count();
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
          return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e){
        MeasurementErrorResponse response=new MeasurementErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
