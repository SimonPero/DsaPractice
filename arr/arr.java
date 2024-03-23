package arr;

import java.util.Arrays;

public class arr {
    int[] numeros = new int[0];

    public void numPrint(int inicio, int fin) {
        if (inicio <= fin) {
            for (int i = inicio; i <= fin; i++) {
                numeros = Arrays.copyOf(numeros, numeros.length + 1);
                numeros[numeros.length - 1] = i;
            }
        } else {
            System.out.println("El inicio del rango debe ser menor o igual al final.");
        }
    }

    public int binarySearch(int data) {
        int low = 0;
        int high = numeros.length - 1; 
        while (low <= high) { 
            int mid = low + (high - low) / 2; 
            int midNum = numeros[mid];
            if (data == midNum) {
                System.out.println( midNum);
                return mid;
            }
            if (data < midNum) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1; 
    }

    public int bSearch(int data){
        int low = 0;
        int high = numeros.length - 1; 
        int x = bSearchRecur(data, low, high);
        return x;
    }
    private int bSearchRecur(int data, int start, int end){
        if(start > end) { 
            return -1;
        }
        int mid = start + (end - start) / 2; 
        int midNum = numeros[mid];
        if(data == midNum){
            System.out.println( midNum);
            return mid;
        }
        if(data < midNum){
            return bSearchRecur(data, start, mid-1);
        } else{
            return bSearchRecur(data, mid+1, end);
        }
   }
    public static void main(String[] args) {
        arr arr = new arr();
        arr.numPrint(1, 40);
        arr.binarySearch(15);
        arr.bSearch(10);
    }
}
