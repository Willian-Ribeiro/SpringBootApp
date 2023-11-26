package com.industries.lunar.rest.enums;

import lombok.Getter;

@Getter
public enum DeliveryStatusEnum {
    DISPATCHED("DISPATCHED"),
    DELIVERED("DELIVERED");

    private final String deliveryStatus;

    DeliveryStatusEnum(String deliveryStatus)
    {
        this.deliveryStatus = deliveryStatus;
    }
}
