package org.example.cqrssimple.readmodel;

import lombok.Value;

@Value
public class ItemDetailsDto {
    String uuid;
    String name;
    int quantity;
}
