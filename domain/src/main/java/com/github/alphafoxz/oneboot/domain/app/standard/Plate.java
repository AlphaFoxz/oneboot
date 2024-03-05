package com.github.alphafoxz.oneboot.domain.app.standard;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

/**
 * 车牌
 */
@Data
@AllArgsConstructor
public class Plate {
    private String number;

    @Override
    public boolean equals(Object other) {
        if (other instanceof Plate plate) {
            return this.number != null && this.number.equals(plate.getNumber());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
