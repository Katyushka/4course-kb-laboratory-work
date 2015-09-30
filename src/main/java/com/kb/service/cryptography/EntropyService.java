package com.kb.service.cryptography;

import com.kb.model.cryptography.dto.EntropyForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 29.09.15.
 */
@Service
public class EntropyService {
    private static final Logger log = LoggerFactory.getLogger(EntropyService.class);

    public EntropyForm calculateProbability(EntropyForm entropyForm) {
        entropyForm.setpC(calculateProbabilityC(entropyForm.getC(), entropyForm.getpM(), entropyForm.getpK(), entropyForm.getCountC()));
        entropyForm.setEntropyM(getEntropy(entropyForm.getpM()));
        entropyForm.setEntropyK(getEntropy(entropyForm.getpK()));
        entropyForm.setEntropyC(getEntropy(entropyForm.getpC()));
        entropyForm.setEntropyKC(entropyForm.getEntropyM()+entropyForm.getEntropyK()-entropyForm.getEntropyC());
        return entropyForm;
    }

    private List<Double> calculateProbabilityC(List<List<String>> tableCryptogram, List<Double> pM, List<Double> pK, int countC) {
        List<Double> pC = Arrays.asList(new Double[countC]);
        Collections.fill(pC, 0d);
        for (int pos = 0; pos < countC; pos++) {
            String s = String.valueOf(pos + 1);
            for (int i = 0; i < pK.size(); i++) {
                for (int j = 0; j < pM.size(); j++) {
                    if (tableCryptogram.get(i).get(j).equals(s)) {
                        pC.set(pos, (pM.get(j) * pK.get(i) + pC.get(pos)));
                    }
                }
            }
        }
        return pC;
    }

    public double getEntropy(List<Double> p) {
        double entropy = 0;
        for (int i = 0; i < p.size(); i++) {
            entropy += (-1) * p.get(i) * (Math.log(p.get(i)) / Math.log(2));
        }
        return entropy;
    }


}
