package com.proovikas.ppa.model;
import lombok.Data;


@Data
public class Summation {
    
    Integer x;
    Integer y;
    Integer z;

      public Summation(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        this.z = x + y;
    }
}