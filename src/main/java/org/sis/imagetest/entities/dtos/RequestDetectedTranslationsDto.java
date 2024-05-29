package org.sis.imagetest.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDetectedTranslationsDto {


    private float translation_x;

    private float translation_y;
}
