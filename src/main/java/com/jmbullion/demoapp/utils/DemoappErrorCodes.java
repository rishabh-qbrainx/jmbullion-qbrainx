package com.jmbullion.demoapp.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DemoappErrorCodes {

    INVALID_ITEM_ID("JMB1001", "Invalid item id: "),
    INVALID_PAYMENT_TYPE("JMB1002", "Invalid Payment Method: "),
    INVALID_QUANTITY("JMB1003", "Invalid Quantity for itemId: ");

    private final String errorCode;
    private final String errorDesc;
}
