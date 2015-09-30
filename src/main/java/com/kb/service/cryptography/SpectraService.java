package com.kb.service.cryptography;

import com.kb.model.cryptography.dto.SpectraForm;
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


    private List<String> setsOfValues(String vectorBF){
        int n = vectorBF.length();
        int m = Integer.toBinaryString(n).length()-1;
        List<String> values = Arrays.asList(new String[n]);
        List<String> exponents = new ArrayList<>();
        for (int i =0; i<10; i++){
            exponents.add(String.valueOf(Math.pow(2, i)));
        }

        for (int i =0; i<n; i++){
            String tmp = Integer.toBinaryString(i);
            values.set(i,"");
            for (int j = 0; j < m - tmp.length(); j++)
                values.set(i,values.get(i)+"0" );
            values.set(i, values.get(i) + tmp);
        }

        return values;

    }


    private List<Integer> getSpectrumFourier(String vectorBF){
        List<String> values = setsOfValues(vectorBF);
        List<Integer> spectrumFourier = new ArrayList<>();
        int wf=0;
        for (int i =0; i<values.size(); i++){
            wf=0;
            for (int j =0; j<values.size(); j++){
                int pow=0;
                for (int k =0; k<values.get(i).length(); k++) {
                     pow += (Integer.valueOf(String.valueOf(values.get(j).charAt(k)))*Integer.valueOf(String.valueOf(values.get(i).charAt(k))));
                }
                wf+=(Integer.valueOf(String.valueOf(vectorBF.charAt(j)))*Math.pow(-1, pow));
            }
            spectrumFourier.add(wf);
        }


     return spectrumFourier;
    }


    private List<Integer> getSpectrumWalshAdamar(String vectorBF){
        List<String> values = setsOfValues(vectorBF);
        List<Integer> spectrumWalshAdamar = new ArrayList<>();
        int wf=0;
        for (int i =0; i<values.size(); i++){
            wf=0;
            for (int j =0; j<values.size(); j++){
                int pow=0;
                for (int k =0; k<values.get(i).length(); k++) {
                    pow += (Integer.valueOf(String.valueOf(values.get(j).charAt(k)))*Integer.valueOf(String.valueOf(values.get(i).charAt(k))));
                }
                pow+=Integer.valueOf(String.valueOf(vectorBF.charAt(j)));
                wf+=Math.pow(-1, pow);
            }
            spectrumWalshAdamar.add(wf);
        }


        return spectrumWalshAdamar;
    }

    private String getParsevalEquality(List<Integer> wf, String vectorBF){
        int sum =0;
        int n = vectorBF.length();
        int m = Integer.toBinaryString(n).length()-1;
        for (int i =0; i<wf.size(); i++){
            sum+=(wf.get(i)*wf.get(i));
        }
        String s = sum+" = "+ String.valueOf(Math.pow(2.0, 2.0 * m));
        return s;
    }





}
