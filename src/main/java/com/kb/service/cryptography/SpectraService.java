package com.kb.service.cryptography;

import com.kb.model.cryptography.dto.SpectraForm;
import com.kb.model.cryptography.dto.TableSpectraForm;
import com.kb.web.controllers.cryptography.SpectraController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 30.09.15.
 */
@Service
public class SpectraService {
    private static final Logger log = LoggerFactory.getLogger(SpectraController.class);

    public SpectraForm calculateSpectrumFourier(SpectraForm spectraForm) {
        spectraForm.setSpectrumFourier(getSpectrumFourier(spectraForm.getVectorBF()));
        spectraForm.setSpectrumWalshAdamar(getSpectrumWalshAdamar(spectraForm.getVectorBF()));
        spectraForm.setParsevalEquality(getParsevalEquality(getSpectrumWalshAdamar(spectraForm.getVectorBF()), spectraForm.getVectorBF()));
        return spectraForm;
    }


    public TableSpectraForm calculatingTable(TableSpectraForm tableSpectraForm){
        tableSpectraForm.setTable(setAllFields());
        return tableSpectraForm;
    }


    private List<List<String>> setAllFields(){
        List<String> values1 = setsValues(16);
        List<String> list = Arrays.asList("", "", "", "", "","");
        List<List<String>> table = new ArrayList<>();
        List<String> spectrumFourier1 = new ArrayList<>();
        List<String> spectrumWalshAdamar1 = new ArrayList<>();
        List<String> nonlinearity1 = new ArrayList<>();
        List<String> weightHamming1 = new ArrayList<>();
        weightHamming1 = getWeightHamming(16);
        List<String> data = new ArrayList<>();
        for (int i =0; i<16; i++){
            data.add("f"+i);
        }
        for (int i = 0; i<values1.size(); i++){
            table.add(list);
        }
        for (int i = 0; i<values1.size(); i++){
            spectrumFourier1.add(String.valueOf(getSpectrumFourier(values1.get(i))));
            spectrumWalshAdamar1.add(String.valueOf(getSpectrumWalshAdamar(values1.get(i))));
            nonlinearity1.add(String.valueOf(getNonlinearity(values1.get(i),2)));

        }
        for (int i =0; i<values1.size(); i++){
            List<String> tmp = new ArrayList<>();
            tmp.add(data.get(i));
            tmp.add(values1.get(i));
            tmp.add(spectrumFourier1.get(i));
            tmp.add(spectrumWalshAdamar1.get(i));
            tmp.add(nonlinearity1.get(i));
            tmp.add(weightHamming1.get(i));
            //table.get(i).set(0, values.get(i));
            //table.get(i).set(1,spectrumFourier.get(i));
            //table.get(i).set(2,spectrumWalshAdamar.get(i));
            //table.get(i).set(3,nonlinearity.get(i));
            //table.get(i).set(4,weightHamming.get(i));
            table.set(i,tmp);
        }


        List<String> values = setsValues(256);
        List<String> spectrumFourier = new ArrayList<>();
        List<String> spectrumWalshAdamar = new ArrayList<>();
        List<String> nonlinearity = new ArrayList<>();
        List<String> weightHamming = new ArrayList<>();
        weightHamming = getWeightHamming(256);
        List<String> _data = new ArrayList<>();
        for (int i =0; i<256; i++){
            _data.add("f"+i);
        }
        for (int i = 0; i<248; i++){
            table.add(list);
        }
        for (int i = 0; i<values.size(); i++){
            spectrumFourier.add(String.valueOf(getSpectrumFourier(values.get(i))));
            spectrumWalshAdamar.add(String.valueOf(getSpectrumWalshAdamar(values.get(i))));
            nonlinearity.add(String.valueOf(getNonlinearity(values.get(i),3)));

        }
        for (int i =8; i<values.size()+8; i++){
            List<String> tmp = new ArrayList<>();
            tmp.add(_data.get(i-8));
            tmp.add(values.get(i-8));
            tmp.add(spectrumFourier.get(i-8));
            tmp.add(spectrumWalshAdamar.get(i-8));
            tmp.add(nonlinearity.get(i-8));
            tmp.add(weightHamming.get(i-8));
            //table.get(i).set(0, values.get(i));
            //table.get(i).set(1,spectrumFourier.get(i));
            //table.get(i).set(2,spectrumWalshAdamar.get(i));
            //table.get(i).set(3,nonlinearity.get(i));
            //table.get(i).set(4,weightHamming.get(i));
            table.set(i,tmp);
        }



        return table;
    }

    private List<String> setsValues(int n) {
        int m = Integer.toBinaryString(n).length() - 1;
        List<String> values = Arrays.asList(new String[n]);
        List<String> exponents = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            exponents.add(String.valueOf(Math.pow(2, i)));
        }

        for (int i = 0; i < n; i++) {
            String tmp = Integer.toBinaryString(i);
            values.set(i, "");
            for (int j = 0; j < m - tmp.length(); j++)
                values.set(i, values.get(i) + "0");
            values.set(i, values.get(i) + tmp);
        }

        return values;

    }


    private List<String> setsOfValues(String vectorBF) {
        int n = vectorBF.length();
        int m = Integer.toBinaryString(n).length() - 1;
        List<String> values = Arrays.asList(new String[n]);
        List<String> exponents = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            exponents.add(String.valueOf(Math.pow(2, i)));
        }

        for (int i = 0; i < n; i++) {
            String tmp = Integer.toBinaryString(i);
            values.set(i, "");
            for (int j = 0; j < m - tmp.length(); j++)
                values.set(i, values.get(i) + "0");
            values.set(i, values.get(i) + tmp);
        }

        return values;

    }


    private List<Integer> getSpectrumFourier(String vectorBF) {
        List<String> values = setsOfValues(vectorBF);
        List<Integer> spectrumFourier = new ArrayList<>();
        int wf = 0;
        for (int i = 0; i < values.size(); i++) {
            wf = 0;
            for (int j = 0; j < values.size(); j++) {
                int pow = 0;
                for (int k = 0; k < values.get(i).length(); k++) {
                    pow += (Integer.valueOf(String.valueOf(values.get(j).charAt(k))) * Integer.valueOf(String.valueOf(values.get(i).charAt(k))));
                }
                wf += (Integer.valueOf(String.valueOf(vectorBF.charAt(j))) * Math.pow(-1, pow));
            }
            spectrumFourier.add(wf);
        }
        return spectrumFourier;
    }


    private List<Integer> getSpectrumWalshAdamar(String vectorBF) {
        List<String> values = setsOfValues(vectorBF);
        List<Integer> spectrumWalshAdamar = new ArrayList<>();
        int wf = 0;
        for (int i = 0; i < values.size(); i++) {
            wf = 0;
            for (int j = 0; j < values.size(); j++) {
                int pow = 0;
                for (int k = 0; k < values.get(i).length(); k++) {
                    pow += (Integer.valueOf(String.valueOf(values.get(j).charAt(k))) * Integer.valueOf(String.valueOf(values.get(i).charAt(k))));
                }
                pow += Integer.valueOf(String.valueOf(vectorBF.charAt(j)));
                wf += Math.pow(-1, pow);
            }
            spectrumWalshAdamar.add(wf);
        }
        return spectrumWalshAdamar;
    }

    private String getParsevalEquality(List<Integer> wf, String vectorBF) {
        int sum = 0;
        int n = vectorBF.length();
        int m = Integer.toBinaryString(n).length() - 1;
        for (int i = 0; i < wf.size(); i++) {
            sum += (wf.get(i) * wf.get(i));
        }
        String s = sum + " = " + String.valueOf(Math.pow(2.0, 2.0 * m));
        return s;
    }


    private int getNonlinearity(String vectorBF, int n){
        List<Integer> spectrumWalshAdamar = getSpectrumWalshAdamar(vectorBF);
        int max=Math.abs(spectrumWalshAdamar.get(0));
        for (int i =1; i<spectrumWalshAdamar.size();i++){
            if (Math.abs(spectrumWalshAdamar.get(i))>max) {
                max = Math.abs(spectrumWalshAdamar.get(i));
            }
        }
        return (int)(Math.pow(2,n-1)-max*0.5);
    }

    private List<String> getWeightHamming(int n){
        List<String> weightHamming = new ArrayList<>();
        for (int i =0; i<n; i++){
            weightHamming.add(String.valueOf(Integer.bitCount(i)));
        }
        return weightHamming;
    }

}
