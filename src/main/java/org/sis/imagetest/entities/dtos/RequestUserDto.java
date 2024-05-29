package org.sis.imagetest.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class RequestUserDto {

    private String id;

    private String username;

    private String frame;

    private List<RequestDetectedObjectDto2> detected_objects;

    private List<RequestDetectedTranslationsDto> detected_translations;

}
