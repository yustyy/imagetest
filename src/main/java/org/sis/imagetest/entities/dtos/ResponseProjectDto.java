package org.sis.imagetest.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseProjectDto {

    private int id;

    private String image_url;

    private float translation_x;

    private float translation_y;

}
