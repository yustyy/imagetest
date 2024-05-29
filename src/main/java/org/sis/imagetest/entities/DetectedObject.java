package org.sis.imagetest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "detected_objects")
public class DetectedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "cls")
    private String cls;

    @Column(name = "landing_status")
    private String landingStatus;

    @Column(name = "top_left_x")
    private float topLeftX;

    @Column(name = "top_left_y")
    private float topLeftY;

    @Column(name = "bottom_right_x")
    private float bottomRightX;

    @Column(name = "bottom_right_y")
    private float bottomRightY;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
