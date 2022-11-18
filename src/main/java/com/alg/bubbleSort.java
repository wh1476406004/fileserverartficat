package com.alg;

import java.util.Arrays;

/**
 * @description:
 * @author: GooGoo
 * @time: 2021/11/7 13:29
 */
public class bubbleSort {
    public static void main(String[] args) {
        int[] arr = {8,1,5,12,11,32};
        apply(arr);
    }
    public static void apply(int[] arr){
        int temp = 0;
        for (int i = arr.length-1; i >0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
                
            }
            
        }
        System.out.println(Arrays.toString(arr));
    }
}
