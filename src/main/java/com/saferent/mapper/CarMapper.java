package com.saferent.mapper;

import com.saferent.domain.Car;
import com.saferent.domain.ImageFile;
import com.saferent.dto.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CarMapper {

    //!!! CarDTO --> Car
    @Mapping(target = "image",ignore = true)
    Car carDTOToCar(CarDTO carDTO);

    //!!! List.CarDTO --> List.Car
    List<CarDTO> map(List<Car> cars);

    //!!! Car -> CarDTO
    @Mapping(source = "image", target ="image", qualifiedByName = "getImageAsString")
    CarDTO carToCarDTO(Car car);

    @Named("getImageAsString")
    public static Set<String> getImageIds(Set<ImageFile> imageFiles) {
        Set<String> imgs = new HashSet<>();
        imgs = imageFiles.stream().
                map(imFile -> imFile.getId().toString()).
                collect(Collectors.toSet());
        return imgs;

    }
}
