package com.waffle.interview.algo.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @author: xiaoshuangyi
 * @Date: 2019-03-27 23:25
 * @Description:插入排序
 */
@Slf4j
public class Sorts {

    /**
     *
     * 功能描述: 1.冒泡排序，相邻2个数比较，移动位置
     *          2.多趟遍历，每趟排序
     *
     * @param:
     * @return:
     * @auther: xiaoshuangyi
     * @date: 2019-03-27 23:27
     */
    public static void bubble(int array[]){
        int n = array.length;
        for (int i=0;i<n;i++){
            //跳出循环的标志，避免无用的循环
            boolean flag = false;
            for (int j=0;j<n-i-1;j++){
                if (array[j] > array[j+1]){
                    int tmp = array[j];
                    array[j]= array[j+1];
                    array[j+1] = tmp;
                    flag = true;
                }
            }
            if (!flag){
                break;
            }
        }

    }

    /**
     *@描述：插入排序
     * 从第二个元素开始，拿出来和前面的元素排序。
    **/
    public static void insertSort(int[] arr){
        int len = arr.length;
        for (int i=1;i<len;++i){
            int value = arr[i];
            int j = i-1;
            for (;j>=0;--j){
                if (arr[j] > value){
                    arr[j+1] =arr[j];
                }else{
                    break;
                }
            }
            arr[j+1]=value;
        }

    }

    /**
     *@描述：
    **/
    public static void main(String[] args) {
        int ar[] =new int[]{2,23,1,89,2,1,33,44,9};
        log.info(Arrays.toString(ar));
//        bubble(ar);
        insertSort(ar);
        log.info(Arrays.toString(ar));

    }

}
