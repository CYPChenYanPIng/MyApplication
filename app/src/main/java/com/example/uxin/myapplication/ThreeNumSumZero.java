package com.example.uxin.myapplication;

import android.provider.ContactsContract;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by shuipingyue@uxin.com on 2020/12/28.
 */
class ThreeNumSumZero {
    public static void main(String[] args) {
        int [] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> lists = threeSum(nums);
        for (List<Integer>list :lists) {
            for (Integer num:list) {
                System.out.print(num);
            }
            System.out.println();
        }
    }

    private static List<List<Integer>> threeSum(int [] nums){
        if (nums.length < 3) {
            return Collections.emptyList();
        }

        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);
        int zeroCount = 0;
        for (int num: nums) {
            if (num == 0) {
                zeroCount ++;
            }
        }

        for (int i = 0;i<nums.length - 1 && nums[i] < 0 ; i++) {
            int first = nums[i];
            int j = i + 1;
            int k = nums.length -1;
            while (j < k) {
                int t = nums[k] + nums[j] + first;
                if (t == 0) {
                    List<Integer>  list = new ArrayList<>();
                    list.add(first);
                    list.add(nums[k]);
                    list.add(nums[j]);
                    res.add(list);
                    j++;
                    k--;
                } else if (t > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }

        if (zeroCount >= 3){
            List<Integer>  list = new ArrayList<>();
            list.add(0);
            list.add(0);
            list.add(0);
            res.add(list);
        }

        return new ArrayList<>(res);
    }
}
