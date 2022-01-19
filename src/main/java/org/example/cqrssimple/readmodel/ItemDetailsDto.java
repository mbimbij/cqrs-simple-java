package org.example.cqrssimple.readmodel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDetailsDto {
    String uuid;
    String name;
    int quantity;
}
