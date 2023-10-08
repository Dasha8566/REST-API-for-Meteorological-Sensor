package ua.orlova.springcourse.Project3Solution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.orlova.springcourse.Project3Solution.models.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}
