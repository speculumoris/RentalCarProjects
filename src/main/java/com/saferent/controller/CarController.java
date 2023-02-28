package com.saferent.controller;

import com.saferent.dto.*;
import com.saferent.dto.response.*;
import com.saferent.service.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // !!! SaveCar
    @PostMapping("/admin/{imageId}/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SfResponse> saveCar(
            @PathVariable String imageId, @Valid @RequestBody CarDTO carDTO){
        carService.saveCar(imageId,carDTO);

        SfResponse response = new SfResponse(
                ResponseMessage.CAR_SAVED_RESPONSE_MESSAGE,true);
        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }
}
