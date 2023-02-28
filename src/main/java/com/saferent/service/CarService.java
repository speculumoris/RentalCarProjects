package com.saferent.service;

import com.saferent.dto.CarDTO;
import com.saferent.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    public final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void saveCar(String imageId, CarDTO carDTO) {

    }
}
