package com.saferent.service;

import com.saferent.domain.*;
import com.saferent.dto.*;
import com.saferent.exception.*;
import com.saferent.exception.message.*;
import com.saferent.mapper.*;
import com.saferent.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ImageFileService imageFileService;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, ImageFileService imageFileService, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.imageFileService = imageFileService;
        this.carMapper = carMapper;
    }

    public void saveCar(String imageId, CarDTO carDTO) {
        //!!! image Id , Repo da var mi ??
        ImageFile imageFile = imageFileService.findImageById(imageId);
        //!!! imadeId daha once baska bir arac icin kullanildi mi ???
        Integer usedCarCount = carRepository.findCarCountByImageId(imageFile.getId());
        if(usedCarCount>0) {
            throw new ConflictException(ErrorMessage.IMAGE_USED_MESSAGE);
        }
        //!!! mapperislemi
        Car car = carMapper.carDTOToCar(carDTO);
        //!!! image bilgisini Car a ekliyoruz
        Set<ImageFile> imFiles = new HashSet<>();
        imFiles.add(imageFile);
        car.setImage(imFiles);
        carRepository.save(car);


    }

    public List<CarDTO> getAllCars() {
        List<Car> carList = carRepository.findAll();
        //!!! CarMapper
        return carMapper.map(carList);
    }
}
