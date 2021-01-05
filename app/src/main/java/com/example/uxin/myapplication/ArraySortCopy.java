package com.example.uxin.myapplication;

/**
 * Created by shuipingyue@uxin.com on 2020/12/18.
 */
class ArraySortCopy {
    public static void main(String[] args) {
        int [] a = new int[20];
        a[0] = 1;
        a[1] = 2;
        a[2] = 5;
        a[3] = 8;
        a[4] = 20;
        int [] b = new int[]{5,6,7,9};
        int m = 5;
        int n = 4;
        int [] c = mergeSortedArray(a,b,m,n);
        for (int i = 0 ; i < c.length;i++){
            System.out.println(c[i]);
            a[i] = c[i];
         }

    }

    private static int [] mergeSortedArray(int [] a,int [] b,int m,int n){
        int [] c = new int[m+n];
        int i = 0,j = 0,k = 0;
        for (;i < m && j < n;k++) {
            if (a[i] < b[j]) {
                c[k] = a[i];
                i++;
            } else if (a[i] == b[j]) {
                c[k] = a[i];
                k++;
                i++;
                c[k] = b[j];
                j++;
            } else {
                c[k] = b[j];
                j++;
            }

        }
        if (i < m) {
            for (; i < m; i++,k++) {
                c[k] = a[i];
            }
        } else if (j < n) {
            for (;j < n;j++,k++) {
                c[k] = b[j];
            }
        }
        return c;
    }


}
