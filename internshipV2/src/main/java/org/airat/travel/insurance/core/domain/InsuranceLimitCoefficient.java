package org.airat.travel.insurance.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "insurancelimitcoefficient")
public class InsuranceLimitCoefficient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "insurancelimitfrom")
    private Integer insurancelimitfrom;

    @Column(name = "insurancelimitto")
    private Integer insurancelimitto;

    @Column(name = "coefficient")
    private Double coefficient;

}