package org.airat.travel.insurance.core.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "agecoefficient")
public class AgeCoefficient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fromage")
    private Integer fromage;

    @Column(name = "toage")
    private Integer toage;

    @Column(name = "coefficient")
    private Double coefficient;

}