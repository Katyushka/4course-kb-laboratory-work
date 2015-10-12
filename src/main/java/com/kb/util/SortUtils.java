package com.kb.util;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SortUtils {
    public static void qSort(List<Integer> data) {
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

    public static void insertionSort(List<Integer> array) {
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
