package ua.orlova.springcourse.Project3Solution.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.orlova.springcourse.Project3Solution.models.Measurement;
import ua.orlova.springcourse.Project3Solution.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

@Autowired
public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService){
    this.measurementRepository=measurementRepository;
    this.sensorService=sensorService;
}


    @Transactional
    public void addMeasurement(Measurement measurement){
enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    public List<Measurement> findAll(){
    return measurementRepository.findAll();
    }

    private void enrichMeasurement (Measurement measurement){
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
        measurement.setMeasurementDateTime(LocalDateTime.now());
    }
}
