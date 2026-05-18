package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.global.enums.Food;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @OneToMany(mappedBy = "store")
    private List<Mission> missions = new ArrayList<>();

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "food_type")
    private Food food;
}
