/**
 * @author RoseMcc04
 * @version April 2024
 */
public class Sorts 
{
    public void merge(int[] arr, int[] left, int[] right) 
    {
        if (arr.length <= 1) 
        {
            return;
        }
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) 
        {
            if (left[i] <= right[j]) 
            {
                arr[k++] = left[i++];
            }
            else 
            {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) 
        {
            arr[k++] = left[i++];
        }
        while (j < right.length) 
        {
            arr[k++] = right[j++];
        }
    }

    public void swap(int[] arr, int n1, int n2) 
    {
        if (arr.length <= 1) 
        {
            return;
        }
        int temp = arr[n1];
        arr[n1] = arr[n2];
        arr[n2] = temp;
    }

    public int binary_search(int[] arr, int low, int high, int target) 
    {
        if (arr.length <= 1) 
        {
            return 0;
        }
        merge_sort(arr);
        int middle = (low + high) / 2;
        if (arr[middle] == target) 
        {
            return middle;
        }
        else if (arr[middle] < target) 
        {
            return binary_search(arr, middle + 1, high, target);
        }
        else 
        {
            return binary_search(arr, low, middle - 1, target);
        }
    }

    public int partition(int[] arr, int low, int high) 
    {
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];
        swap(arr, middle, high);
        int i = low - 1;
        for (int j = low; j < high - 1; j++) 
        {
            if (arr[j] <= pivot) 
            {
                i++; 
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public int[] selection_sort(int[] arr)
    {
        if (arr.length <= 1) 
        {
            return arr;
        }
        for (int i = 0; i < arr.length; i++) 
        {
            int min_index = i;
            for (int j = i + 1; j < arr.length; j++) 
            {
                if (arr[j] < arr[min_index]) 
                {
                    min_index = j;
                }
            }
            swap(arr, min_index, i);
        }
        return arr;
    }

    public int[] insertion_sort(int[] arr) 
    {
        if (arr.length <= 1) 
        {
            return arr;
        }
        for (int i = 1; i < arr.length; i++) 
        {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) 
            {
                swap(arr, j - 1, j);
            }
        }
        return arr;
    }

    public int[] bubble_sort(int[] arr) 
    {
        if (arr.length <= 1) 
        {
            return arr;
        }
        for (int i = 0; i < arr.length - 2; i++) 
        {
            for (int j = 0; j < arr.length - i - 1; j++) 
            {
                if (arr[j] > arr[j + 1]) 
                {
                    swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    public int[] merge_sort(int[] arr)
    {
        if (arr.length <= 1) 
        {
            return arr;
        }
        int middle = arr.length / 2;
        int[] left = new int[middle];
        int[] right = new int[arr.length - middle];
        System.arraycopy(arr, 0, left, 0, middle);
        System.arraycopy(arr, middle, right, 0, arr.length - middle);
        merge_sort(left);
        merge_sort(right);
        merge(arr, left, right);
        return arr;
    }

    public int[] quick_sort(int[] arr, int low, int high) 
    {
        if (arr.length <= 1) 
        {
            return arr;
        }
        if (low < high) 
        {
            int pivotIndex = partition(arr, low, high);
            quick_sort(arr, low, pivotIndex - 1);
            quick_sort(arr, pivotIndex + 1, high);
        }
        return arr;
    }

    public int[] quick_sort(int[] arr) 
    {
        return quick_sort(arr, 0, arr.length - 1);
    }
}