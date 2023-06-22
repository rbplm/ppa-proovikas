package com.proovikas.ppa.controller;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.*;

import com.proovikas.ppa.model.Summation;

@RestController
@RequestMapping("/calculation")
public class CalculationController {

    CopyOnWriteArrayList<Summation> listOfSummations = new CopyOnWriteArrayList<>();

    @GetMapping("/add")
    public Summation addNumbers(@RequestParam Integer x, @RequestParam Integer y) {
       
        if (x >= 100 || x < 0 || y >= 100 || y < 0) {
            return null;
        } 
        
        Summation sum = new Summation(x, y);
        listOfSummations.add(sum);
        return sum;
    }

    @GetMapping("/search")
    public List<Summation> searchSummations(@RequestParam(required = false) Integer number, @RequestParam String sort) {
  
        Stream<Summation> stream = listOfSummations.stream();

        if (number != null) {
            stream = stream.filter(summation -> summation.getX().equals(number) || summation.getY().equals(number) || summation.getZ().equals(number));
        }

        if (number != null && (number >= 100 || number < 0)) {
            return null;
        } 

        if ("asc".equals(sort)) {
            stream = stream.sorted(Comparator.comparing(Summation::getZ));
        } else if ("desc".equals(sort)) {
            stream = stream.sorted(Comparator.comparing(Summation::getZ).reversed());
        }


        return stream.collect(Collectors.toList());
    }

}
