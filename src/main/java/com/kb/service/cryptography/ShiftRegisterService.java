package com.kb.service.cryptography;

import com.kb.model.cryptography.dto.ShiftRegisterForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by user on 25.10.15.
 */

@Service
public class ShiftRegisterService {
    private static final Logger log = LoggerFactory.getLogger(ShiftRegisterService.class);

    public ShiftRegisterForm generateSequance(ShiftRegisterForm shiftRegisterForm){
        shiftRegisterForm.setOrderPolynom(6 - 1);
        shiftRegisterForm.setOrderSequence(5);
        shiftRegisterForm.setResultVector(generatingSeq(shiftRegisterForm.getN(), shiftRegisterForm.getInitialVector()));
        return shiftRegisterForm;
    }


    private List<Integer> generatingSeq(int n, List<Integer> init){
        List<Integer> seq = new ArrayList<>();

        for (int i =0; i<100; i++){
            seq.add(i+1);
        }
        return seq;
    }

}
