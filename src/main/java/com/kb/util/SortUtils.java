package com.kb.util;

import com.kb.model.parallelprogramming.dto.SortingData;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SortUtils {

    public static void qSort(SortingData sortingData) throws InterruptedException {
        qSort(sortingData, sortingData.getData());
    }

    private static void qSort(SortingData sortingData, List<Integer> data) throws InterruptedException {
        while (sortingData.getThread().isPause()) {
            synchronized (sortingData.getThread()) {
                sortingData.getThread().wait();
            }
        }
        sortingData.setCurrentCount(sortingData.getCurrentCount() + 1);
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
            qSort(sortingData, data.subList(0, j + 1));
        }
        if (i < n) {
            qSort(sortingData, data.subList(i, n));
        }
    }

    public static void insertionSort(SortingData sortingData) throws InterruptedException {
        insertionSort(sortingData, sortingData.getData());
    }

    private static void insertionSort(SortingData sortingData, List<Integer> data) throws InterruptedException {
        for (int i = 0; i < data.size(); i++) {
            while (sortingData.getThread().isPause()) {
                synchronized (sortingData.getThread()) {
                    sortingData.getThread().wait();
                }
            }
            int temp = data.get(i);// запомним i-ый элемент
            int j = i - 1;//будем идти начиная с i-1 элемента
            while (j >= 0 && data.get(j) > temp)
            // пока не достигли начала массива
            // или не нашли элемент больше i-1-го
            // который храниться в переменной temp
            {
                data.set(j + 1, data.get(j));
                //проталкиваем элемент вверх
                j--;
            }
            data.set(j + 1, temp);
            // возвращаем i-1 элемент
            sortingData.setStatus(Math.round(i * 100 / data.size()));
        }
    }
}
