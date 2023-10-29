package com.tutorials.app.mapper;

import com.tutorials.app.dto.TutorialDto;
import com.tutorials.app.model.Tutorial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")

public interface TutorialMapper {
    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "title",source = "title"),
            @Mapping(target = "description",source ="description"),
            @Mapping(target = "published",source = "published")
    })
    TutorialDto tutorialToDto(Tutorial tutorial);
    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "title",source = "title"),
            @Mapping(target = "description",source ="description"),
            @Mapping(target = "published",source = "published")
    })
    Tutorial dtoToTutorial(TutorialDto tutorialDto);

}
