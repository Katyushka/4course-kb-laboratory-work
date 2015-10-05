package com.kb.service.parallelprogramming;

import com.kb.model.parallelprogramming.dto.SortingForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by user on 05.10.15.
 */

@Service
public class SortService {
    private static final Logger log = LoggerFactory.getLogger(SortService.class);

    public SortingForm getDefaultSortingForm() {
        SortingForm sortingForm = new SortingForm();
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            Random rand = new Random();
            data.add(rand.nextInt(1000));
        }
        return sortingForm;
    }


    public SortingForm generateData(SortingForm sortingForm) {
        sortingForm.setData(generatingData(sortingForm.getData()));
        return sortingForm;
    }

    private List<Integer> generatingData(List<Integer> data) {
        Random rand = new Random();
        for (int i = 0; i < 50000; i++) {
            data.add(rand.nextInt(1000));
        }
        return data;
    }

    private void qSort(List<Integer> array) {
        int n = array.size();
        int i = 0;
        int j = n - 1;
        Random rand = new Random();
        int x = array.get(rand.nextInt(n));
        while (i <= j) {
            while (array.get(i) < x) {
                i++;
            }
            while (array.get(j) > x) {
                j--;
            }
            if (i <= j) {
                Collections.swap(array, i, j);
                i++;
                j--;
            }
        }
        if (j > 0) {
            qSort(array.subList(0, j + 1));
        }
        if (i < n) {
            qSort(array.subList(i, n));
        }
    }


    public void insertionSort(List<Integer> array) {
        for (int i = 0; i < array.size(); i++) {
            int temp = array.get(i);// запомним i-ый элемент
            int j = i - 1;//будем идти начиная с i-1 элемента
            while (j >= 0 && array.get(j) > temp)
            // пока не достигли начала массива
            // или не нашли элемент больше i-1-го
            // который храниться в переменной temp
            {
                array.set(j + 1, array.get(j));
                //проталкиваем элемент вверх
                j--;
            }
            array.set(j + 1, temp);
            // возвращаем i-1 элемент
        }
    }


}
