package com.opahit.mschallengebackend.domain.dto;

import com.opahit.mschallengebackend.domain.enums.TypeEnum;
import java.time.LocalDateTime;

public record CashDTO(String clientName, TypeEnum typeEnum, Double value, LocalDateTime date){}
