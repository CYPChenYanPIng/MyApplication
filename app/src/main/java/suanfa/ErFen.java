package suanfa;

/**
 * Created by shuipingyue@uxin.com on 2020/12/2.
 */
public class ErFen {

    public static void main(String[] args) {

    }

    private void quickSort(int arr[], int begin, int end) {

        if (begin < end) {
            int i = begin;
            int j = end;
            int temp = arr[begin];// 基准数

            while (i < j) {
                while (i < j && arr[j] > temp) {
                    j++;
                }
                arr[i] = arr[j];

                while (i < j && arr[i] < temp) {
                    i++;
                }
                arr[j] = arr[i];

            }
            arr[i] = temp;

        }
        return;
    }

}
