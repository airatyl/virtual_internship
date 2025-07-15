package org.airat.travel.insurance.core.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "agreement_person_risks")
public class AgreementPersonRisk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agreement_person_id", nullable = false)
    private AgreementPerson agreementPerson;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "risk", nullable = false)
    private RiskType risk;

    @Column(name = "premium", nullable = false, precision = 10, scale = 2)
    private BigDecimal premium;

}