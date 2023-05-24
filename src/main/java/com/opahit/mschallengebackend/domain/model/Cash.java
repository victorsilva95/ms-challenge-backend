package com.opahit.mschallengebackend.domain.model;

import com.opahit.mschallengebackend.domain.dto.CashDTO;
import com.opahit.mschallengebackend.domain.enums.TypeEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Cash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CLIENT_NAME")
    private String clientName;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private TypeEnum type;

    @Column(name = "VALUE")
    private Double value;

    @Column(name = "DATE")
    private LocalDateTime date;

    public Cash() {
    }

    public Cash(String clientName, TypeEnum type, Double value, LocalDateTime date) {
        this.clientName = clientName;
        this.type = type;
        this.value = value;
        this.date = date;
    }

    public Cash(CashDTO cashDTO) {
        this.clientName = cashDTO.clientName();
        this.type = cashDTO.typeEnum();
        this.value = cashDTO.value();
        this.date = cashDTO.date();
    }

    @PrePersist
    public void prePersist() {
        date = date == null ? LocalDateTime.now() : date;
    }

    public Long getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public TypeEnum getType() {
        return type;
    }

    public Double getValue() {
        return value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
