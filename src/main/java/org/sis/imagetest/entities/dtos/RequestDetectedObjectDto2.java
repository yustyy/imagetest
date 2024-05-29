package org.sis.imagetest.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDetectedObjectDto2 {

    private String cls;

    private String landing_status;

    private float top_left_x;

    private float top_left_y;

    private float bottom_right_x;

    private float bottom_right_y;

}
