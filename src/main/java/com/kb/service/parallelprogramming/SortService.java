package com.kb.service.parallelprogramming;

import com.kb.model.parallelprogramming.dto.SortingData;
import com.kb.model.parallelprogramming.dto.SortingForm;
import org.apache.commons.lang3.SerializationUtils;
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
        return generateData(new SortingForm());
    }

    public SortingForm generateData(SortingForm sortingForm) {
        clearData(sortingForm);
        SortingData sortingData = new SortingData(generatingData(sortingForm.getQuickSortingData().getData()));
        sortingForm.setQuickSortingData(sortingData);
        sortingForm.setInsertionSortingData(new SortingData(new ArrayList<Integer>(sortingData.getData())));
        return sortingForm;
    }

    private List<Integer> generatingData(List<Integer> data) {
        for (int i = 0; i < 50000; i++) {
            Random rand = new Random();
            data.add(rand.nextInt(1000));
        }
        return data;
    }

    public void doSort(SortingForm form) {
        qSort(form.getQuickSortingData());
        insertionSort(form.getInsertionSortingData());
    }

    private void qSort(SortingData sortingData) {
        long t1 = System.currentTimeMillis();
        qSort(sortingData.getData());
        sortingData.setDuration(System.currentTimeMillis() - t1);
    }

    private void qSort(List<Integer> data) {
        int n = data.size();
        int i = 0;
        int j = n - 1;
        Random rand = new Random();
        int x = data.get(rand.nextInt(n));
        while (i <= j) {
            while (data.get(i) < x) {
                i++;
            }
            while (data.get(j) > x) {
                j--;
            }
            if (i <= j) {
                Collections.swap(data, i, j);
                i++;
                j--;
            }
        }
        if (j > 0) {
            qSort(data.subList(0, j + 1));
        }
        if (i < n) {
            qSort(data.subList(i, n));
        }
    }

    private void insertionSort(SortingData sortingData) {
        long t1 = System.currentTimeMillis();
        insertionSort(sortingData.getData());
        sortingData.setDuration(System.currentTimeMillis() - t1);
    }

    private void insertionSort(List<Integer> array) {
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

    private void clearData(SortingForm form) {
        form.getInsertionSortingData().getData().clear();
        form.getQuickSortingData().getData().clear();
    }

}
