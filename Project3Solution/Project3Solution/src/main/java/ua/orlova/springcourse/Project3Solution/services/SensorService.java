package ua.orlova.springcourse.Project3Solution.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.orlova.springcourse.Project3Solution.models.Sensor;
import ua.orlova.springcourse.Project3Solution.repositories.SensorRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Optional<Sensor> findByName(String name){
        return sensorRepository.findByName(name);
    }
@Transactional
    public void register(Sensor sensor){
        sensorRepository.save(sensor);
    }
}
