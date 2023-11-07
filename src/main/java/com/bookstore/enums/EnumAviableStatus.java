package com.bookstore.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor

@NoArgsConstructor
public enum EnumAviableStatus {
    ACTIVE(1), DEACTIVE(0);

    public int value;
}
