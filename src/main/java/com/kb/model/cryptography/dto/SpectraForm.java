package com.kb.model.cryptography.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 30.09.15.
 */
public class SpectraForm implements Serializable {
    private String vectorBF = "";
    private List<Integer> spectrumFourier = new ArrayList<>();
    private List<Integer> spectrumWalshAdamar = new ArrayList<>();
    private String parsevalEquality="";


    public String getVectorBF() {
        return vectorBF;

    }

    public void setVectorBF(String vectorBF) {
        this.vectorBF = vectorBF;
    }

    public List<Integer> getSpectrumFourier() {
        return spectrumFourier;
    }

    public void setSpectrumFourier(List<Integer> spectrumFourier) {
        this.spectrumFourier = spectrumFourier;
    }

    public List<Integer> getSpectrumWalshAdamar() {
        return spectrumWalshAdamar;
    }

    public void setSpectrumWalshAdamar(List<Integer> spectrumWalshAdamar) {
        this.spectrumWalshAdamar = spectrumWalshAdamar;
    }

    public String getParsevalEquality() {
        return parsevalEquality;
    }

    public void setParsevalEquality(String parsevalEquality) {
        this.parsevalEquality = parsevalEquality;
    }
}
