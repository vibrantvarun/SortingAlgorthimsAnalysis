package Demo;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Project : Sorting (Algorithm & DataStructures)
 * @author Varun Jain (Student Id: 801168344)
 * Description:-
 * The below code consists of
 *   1) Insertion Sort
 *   2) Merge Sort
 *   3) Quick Sort (In place)
 *   4) Modified QuickSort (Insertion sort will work to find median and in every 10 elements amd Overall QuickSort will work to sort n/10 groups)
 *   5) Heap Sort (Creating vector based Min heap and also inserting one element at a time then extracting min element which is root and heapifying it)
 *   The order of main method is
 *   //Declaring arrays as inputs for different sorts
 *      a) inputSize(n)    Input for Insertion Sort  n--> 1,2,3,4,.......10
 *      b) inputSize(n)_merge   Input for Merge Sort n--> 1,2,3,4,.......10
 *      c) inputSize(n)_quick   Input for Quick Sort n--> 1,2,3,4,.......10
 *      d) inputSize(n)_mq      Input for Modified Quick Sort n--> 1,2,3,4,.......10
 *      e) inputSize(n)_heap    Input for creating Min heap which is used to store above generated random numbers
 *   So that the same data can be used in all sorts
 *   // Insertion Sort works for all input sizes when inputs are not sorted
 *   // Insertion Sort works for all input sizes when inputs are sorted
 *   // Insertion Sort works for all input sizes when inputs are reversely sorted
 *   // Merge Sort works for all input sizes when inputs are not sorted
 *   // Merge Sort works for all input sizes when inputs are  sorted
 *   // Merge Sort works for all input sizes when inputs are reversely sorted
 *   // Quick Sort works for all input sizes when inputs are not sorted
 *   // Quick Sort works for all input sizes when inputs are  sorted
 *   // Quick Sort works for all input sizes when inputs are reversely sorted
 *   // Modified Quick Sort works for all input sizes when inputs are not sorted
 *   // Modified Quick Sort works for all input sizes when inputs are sorted
 *   // Modified Quick Sort works for all input sizes when inputs are reversely sorted
 *   // Heap Sort works for all input sizes when inputs are not sorted
 *        a) Creating Min heap
 *        b) Extracting Minimum elements
 *   // Heap Sort works for all input sizes when inputs are sorted
 *        a) Creating Min heap
 *        b) Extracting Minimum elements
 *   // Heap Sort works for all input sizes when inputs are reversely sorted
 *        a) Creating Min heap
 *        b) Extracting Minimum elements
 *   // Output format
 *   ______ ->> Insertion, Merge, Quick, Modified Quick, Heap
 *   ______ Sort when arrays are not sorted:
 *   1000, 2000, 3000, 5000,10000,20000,30000,40000,50000,60000
 *   output of all time execution in the above order
 *   For example:
 *   Insertion Sort when arrays are not sorted:
 *   1 2 3 4 5 6 7 8 9 10
 *   1 --> It is the execution time of Insertion sort for 1000 input size
 *   2 --> It is the execution time of Insertion sort for 2000 input size
 *   3 --> It is the execution time of Insertion sort for 3000 input size
 *   4 --> It is the execution time of Insertion sort for 5000 input size
 *   5 --> It is the execution time of Insertion sort for 10000 input size
 *   6 --> It is the execution time of Insertion sort for 20000 input size
 *   7 --> It is the execution time of Insertion sort for 30000 input size
 *   8 --> It is the execution time of Insertion sort for 40000 input size
 *   9 --> It is the execution time of Insertion sort for 50000 input size
 *   10 --> It is the execution time of Insertion sort for 60000 input size
 */
public class sort {
    //Insertion Sort (Worst case complexity O(n^2)
     void insertionSort(Integer arr[]){
        int i=1;
        int n= arr.length;
        while (i<n){
            int element=arr[i];
            int j=i-1;
            boolean largeElementFound=false;
            while (j>=0 && arr[j]>element){
                if (!largeElementFound){
                    largeElementFound=true;
                }
                arr[j+1]=arr[j];
                j--;
            }
            if(largeElementFound){
                arr[j+1]=element;
            }
            i++;
        }
    }
    //Merge Sort (worst case complexity(n(logn))
     void mergeSubArray(Integer arr[],int l, int m, int r){
        int size1= m-l+1;
        int size2=r-m;
        int arr1[]= new int[size1];
        int arr2[]= new int[size2];
        for(int i=0;i<arr1.length;i++){
            arr1[i]=arr[l+i];
        }
        for (int i=0;i<arr2.length;i++){
            arr2[i]=arr[m+1+i];
        }
        int i=0,j=0;
        int k=l;
        while (i<size1 && j<size2){
            if (arr1[i]<=arr2[j]){
                arr[k]=arr1[i];
                i++;
            }else {
                arr[k]=arr2[j];
                j++;
            }
            k++;
        }
        while (i<size1){
            arr[k]=arr1[i];
            i++;
            k++;
        }
        while (j<size2){
            arr[k]=arr2[j];
            j++;
            k++;
        }
    }
     void mergeSort(Integer arr[],int l,int r){
        if (l<r){
            int m= l+((r-l)/2);
            mergeSort(arr,l,m);
            mergeSort(arr,m+1,r);
            mergeSubArray(arr,l,m,r);
        }
    }
    // Quick Sort (worst case complexity(n^2))
    int quickSort(Integer arr[],int low, int high){
        int pivot=arr[high];
        int j=low;
        int i=low-1;
        while (j<=high-1){
            if (arr[j]<pivot) {
                i++;
                swap(arr, i, j);
            }
            j++;
        }
        swap(arr,i+1,high);
        return i+1;
    }
     void quicksort(Integer arr[],int l, int r){
        while (l<r){
            int index= quickSort(arr,l,r);
            if (index-l<r-index){
                quicksort(arr,l,index-1);
                l=index+1;
            }else {
                quicksort(arr,index+1,r);
                r=index-1;
            }
        }
    }
     void swap(Integer arr[],int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
     }
     //Modified quick sort (worst case complexity nlog(n))
     void modifiedQuickSort(Integer arr[], int l, int h){
        if (l<h){
            int n= h-l+1;
            int med= findMedianOfMedian(arr,l,h, n/2);
            int pos= partition(arr,l,h,med);
            modifiedQuickSort(arr,l,pos-1);
            modifiedQuickSort(arr,pos+1,h);
        }
    }
    // Applying insertion sort in modified quick sort for number of elements <= 10
     int median(Integer arr[], int i, int n){
        //Arrays.sort(arr,i,i+n);
         int s=i;
         int e=i+n;
         while (i<e){
             int element=arr[i];
             int j=i-1;
             boolean largeElementFound=false;
             while (j>=s && arr[j]>element){
                 if (!largeElementFound){
                     largeElementFound=true;
                 }
                 arr[j+1]=arr[j];
                 j--;
             }
             if(largeElementFound){
                 arr[j+1]=element;
             }
             i++;
         }
        return arr[s+(n/2)];
    }
    // finding median of median to select that element as pivot
     int findMedianOfMedian(Integer arr[],int l, int r,int k){
        if (k>0 && k<=r-l+1){
            int n= r-l+1;
            int i;
            Integer median[]= new Integer[(n+9)/10];
            for (i=0;i<(n/10);i++){
                median[i]= median(arr,l+i*10,10);
            }
            if (i*10<n){
                median[i]=median(arr,l+i*10,n%10);
                i++;
            }
            int medOfMed= i==1?median[i-1]:findMedianOfMedian(median,0,i-1,i/2);
            int pos= partition(arr,l,r,medOfMed);
            if (pos-l==k-1){
                return arr[pos];
            }else if (pos-l>k-1){
                return findMedianOfMedian(arr,l,pos-1,k);
            }else {
                return findMedianOfMedian(arr,pos+1,r,k-pos+l-1);
            }
        }
        return Integer.MAX_VALUE;
    }
    // Partition for quick sort
     int partition(Integer arr[],int l, int r, int pivot){
        int i;
        for (i=l;i<r;i++){
            if (arr[i]==pivot){
                break;
            }
        }
        swap(arr,i, r);
        i=l;
        for (int j=l;j<r;j++){
            if (arr[j]<=pivot){
                swap(arr,i,j);
                i++;
            }
        }
        swap(arr,i,r);
        return i;
    }
    int parent(int i){
         return (i-1)/2;
    }
    int left(int i){
         return 2*i+1;
    }
    int right(int i){
         return 2*i+2;
    }
    // Heapify method to maintain binary tree as binary heap
    //Heap sort worst case complexity nlogn
    void heapify(MinHeapTree minHeap,int index){
         int left=left(index);
         int right= right(index);
         int min=index;
         if (left<minHeap.size && minHeap.vector.get(min)>minHeap.vector.get(left)){
             min=left;
         }
         if (right<minHeap.size && minHeap.vector.get(min)>minHeap.vector.get(right)){
             min=right;
         }
         if (min!=index){
             int temp= minHeap.vector.get(index);
             minHeap.vector.set(index,minHeap.vector.get(min));
             minHeap.vector.set(min,temp);
            heapify(minHeap,min);
         }
    }
    //Insert Key in Min heap
    void insertKey(MinHeapTree minHeapTree,int key){
         if (minHeapTree.size>=minHeapTree.capacity){
             System.out.println("Heap Size Overflow");
         }
         minHeapTree.size++;
         int i= minHeapTree.size-1;
         minHeapTree.vector.add(i,key);
         while (i!=0 && minHeapTree.vector.get(parent(i))>minHeapTree.vector.get(i)){
             int temp= minHeapTree.vector.get(parent(i));
             minHeapTree.vector.set(parent(i),minHeapTree.vector.get(i));
             minHeapTree.vector.set(i,temp);
             i=parent(i);
         }
    }
    //Extract Min in Min heap
    public int extractMin(MinHeapTree minHeapTree){
        if (minHeapTree.size<=0){
            return Integer.MAX_VALUE;
        }
        if (minHeapTree.size==1){
            minHeapTree.size--;
            return minHeapTree.vector.get(0);
        }
        int root= minHeapTree.vector.get(0);
        minHeapTree.vector.set(0,minHeapTree.vector.get(minHeapTree.size-1));
        minHeapTree.size--;
        heapify(minHeapTree,0);
        return root;
    }
    public static void main(String[] args) {
         sort sort= new sort();
        //Input size arrays for insertion sort
        Integer inputSize1[]= new Integer[1000] ;
        Integer inputSize2[]= new Integer[2000];
        Integer inputSize3[]= new Integer[3000];
        Integer inputSize4[]= new Integer[5000];
        Integer inputSize5[]= new Integer[10000];
        Integer inputSize6[]= new Integer[20000];
        Integer inputSize7[]= new Integer[30000];
        Integer inputSize8[]= new Integer[40000];
        Integer inputSize9[]= new Integer[50000];
        Integer inputSize10[]= new Integer[60000];
        //Input arrays for Merge Sort
        Integer inputSize1_merge[];
        Integer inputSize2_merge[];
        Integer inputSize3_merge[];
        Integer inputSize4_merge[];
        Integer inputSize5_merge[];
        Integer inputSize6_merge[];
        Integer inputSize7_merge[];
        Integer inputSize8_merge[];
        Integer inputSize9_merge[];
        Integer inputSize10_merge[];
        //Input arrays for to create Min heap
        Integer inputSize1_heap[];
        Integer inputSize2_heap[];
        Integer inputSize3_heap[];
        Integer inputSize4_heap[];
        Integer inputSize5_heap[];
        Integer inputSize6_heap[];
        Integer inputSize7_heap[];
        Integer inputSize8_heap[];
        Integer inputSize9_heap[];
        Integer inputSize10_heap[];
        //Input arrays for quick sort
        Integer inputSize1_quick[];
        Integer inputSize2_quick[];
        Integer inputSize3_quick[];
        Integer inputSize4_quick[];
        Integer inputSize5_quick[];
        Integer inputSize6_quick[];
        Integer inputSize7_quick[];
        Integer inputSize8_quick[];
        Integer inputSize9_quick[];
        Integer inputSize10_quick[];
        //Input arrays for modified quicksort
        Integer inputSize1_mq[];
        Integer inputSize2_mq[];
        Integer inputSize3_mq[];
        Integer inputSize4_mq[];
        Integer inputSize5_mq[];
        Integer inputSize6_mq[];
        Integer inputSize7_mq[];
        Integer inputSize8_mq[];
        Integer inputSize9_mq[];
        Integer inputSize10_mq[];
        //Generating random numbers for input sizes
        for (int i=0;i<inputSize1.length;i++){
            inputSize1[i]= ThreadLocalRandom.current().nextInt(1,inputSize1.length+1);
        }
        //Copying above generated data in other input arrays for other sorts to perform all sorts on one data
        inputSize1_merge=inputSize1.clone();
        inputSize1_heap=inputSize1.clone();
        inputSize1_quick=inputSize1.clone();
        inputSize1_mq=inputSize1.clone();
        for (int i=0;i<inputSize2.length;i++){
            inputSize2[i]= ThreadLocalRandom.current().nextInt(1,inputSize2.length+1);
        }
        inputSize2_merge=inputSize2.clone();
        inputSize2_heap=inputSize2.clone();
        inputSize2_quick=inputSize2.clone();
        inputSize2_mq=inputSize2.clone();
        for (int i=0;i<inputSize3.length;i++){
            inputSize3[i]= ThreadLocalRandom.current().nextInt(1,inputSize3.length+1);
        }
        inputSize3_merge=inputSize3.clone();
        inputSize3_heap=inputSize3.clone();
        inputSize3_quick=inputSize3.clone();
        inputSize3_mq=inputSize3.clone();
        for (int i=0;i<inputSize4.length;i++){
            inputSize4[i]= ThreadLocalRandom.current().nextInt(1,inputSize4.length+1);
        }
        inputSize4_merge=inputSize4.clone();
        inputSize4_heap=inputSize4.clone();
        inputSize4_quick=inputSize4.clone();
        inputSize4_mq=inputSize4.clone();
        for (int i=0;i<inputSize5.length;i++){
            inputSize5[i]= ThreadLocalRandom.current().nextInt(1,inputSize5.length+1);
        }
        inputSize5_merge=inputSize5.clone();
        inputSize5_heap=inputSize5.clone();
        inputSize5_quick=inputSize5.clone();
        inputSize5_mq=inputSize5.clone();
        for (int i=0;i<inputSize6.length;i++){
            inputSize6[i]= ThreadLocalRandom.current().nextInt(1,inputSize6.length+1);
        }
        inputSize6_merge=inputSize6.clone();
        inputSize6_heap=inputSize6.clone();
        inputSize6_quick=inputSize6.clone();
        inputSize6_mq=inputSize6.clone();
        for (int i=0;i<inputSize7.length;i++){
            inputSize7[i]= ThreadLocalRandom.current().nextInt(1,inputSize7.length+1);
        }
        inputSize7_merge=inputSize7.clone();
        inputSize7_heap=inputSize7.clone();
        inputSize7_quick=inputSize7.clone();
        inputSize7_mq=inputSize7.clone();
        for (int i=0;i<inputSize8.length;i++){
            inputSize8[i]= ThreadLocalRandom.current().nextInt(1,inputSize8.length+1);
        }
        inputSize8_merge=inputSize8.clone();
        inputSize8_heap=inputSize8.clone();
        inputSize8_quick=inputSize8.clone();
        inputSize8_mq=inputSize8.clone();
        for (int i=0;i<inputSize9.length;i++){
            inputSize9[i]= ThreadLocalRandom.current().nextInt(1,inputSize9.length+1);
        }
        inputSize9_merge=inputSize9.clone();
        inputSize9_heap=inputSize9.clone();
        inputSize9_quick=inputSize9.clone();
        inputSize9_mq=inputSize9.clone();
        for (int i=0;i<inputSize10.length;i++){
            inputSize10[i]= ThreadLocalRandom.current().nextInt(1,inputSize10.length+1);
        }
        inputSize10_merge=inputSize10.clone();
        inputSize10_heap=inputSize10.clone();
        inputSize10_quick=inputSize10.clone();
        inputSize10_mq=inputSize10.clone();
        System.out.println("Time Complexity of different Sorting Techniques displayed for following input sizes:");
        System.out.println("1000 2000 3000 5000 10000 20000 30000 40000 50000 60000");
        //Array Not Sorted
        System.out.println("Time Complexity of Insertion Sort if Arrays are not sorted :");
        long start1= System.currentTimeMillis();
        sort.insertionSort(inputSize1);
        System.out.print(System.currentTimeMillis()-start1+" ");
        long start2= System.currentTimeMillis();
        sort.insertionSort(inputSize2);
        System.out.print(System.currentTimeMillis()-start2+" ");
        long start3= System.currentTimeMillis();
        sort.insertionSort(inputSize3);
        System.out.print(System.currentTimeMillis()-start3+" ");
        long start4= System.currentTimeMillis();
        sort.insertionSort(inputSize4);
        System.out.print(System.currentTimeMillis()-start4+" ");
        long start5= System.currentTimeMillis();
        sort.insertionSort(inputSize5);
        System.out.print(System.currentTimeMillis()-start5+" ");
        long start6= System.currentTimeMillis();
        sort.insertionSort(inputSize6);
        System.out.print(System.currentTimeMillis()-start6+" ");
        long start7= System.currentTimeMillis();
        sort.insertionSort(inputSize7);
        System.out.print(System.currentTimeMillis()-start7+" ");
        long start8= System.currentTimeMillis();
        sort.insertionSort(inputSize8);
        System.out.print(System.currentTimeMillis()-start8+" ");
        long start9= System.currentTimeMillis();
        sort.insertionSort(inputSize9);
        System.out.print(System.currentTimeMillis()-start9+" ");
        long start10= System.currentTimeMillis();
        sort.insertionSort(inputSize10);
        System.out.print(System.currentTimeMillis()-start10+" ");
        System.out.println();
        // Array already Sorted
        System.out.println("Time Complexity of Insertion Sort if Arrays sorted :");
        long start11= System.currentTimeMillis();
        sort.insertionSort(inputSize1);
        System.out.print(System.currentTimeMillis()-start11+" ");
        long start12= System.currentTimeMillis();
        sort.insertionSort(inputSize2);
        System.out.print(System.currentTimeMillis()-start12+" ");
        long start13= System.currentTimeMillis();
        sort.insertionSort(inputSize3);
        System.out.print(System.currentTimeMillis()-start13+" ");
        long start14= System.currentTimeMillis();
        sort.insertionSort(inputSize4);
        System.out.print(System.currentTimeMillis()-start14+" ");
        long start15= System.currentTimeMillis();
        sort.insertionSort(inputSize5);
        System.out.print(System.currentTimeMillis()-start15+" ");
        long start16= System.currentTimeMillis();
        sort.insertionSort(inputSize6);
        System.out.print(System.currentTimeMillis()-start16+" ");
        long start17= System.currentTimeMillis();
        sort.insertionSort(inputSize7);
        System.out.print(System.currentTimeMillis()-start17+" ");
        long start18= System.currentTimeMillis();
        sort.insertionSort(inputSize8);
        System.out.print(System.currentTimeMillis()-start18+" ");
        long start19= System.currentTimeMillis();
        sort.insertionSort(inputSize9);
        System.out.print(System.currentTimeMillis()-start19+" ");
        long start20= System.currentTimeMillis();
        sort.insertionSort(inputSize10);
        System.out.print(System.currentTimeMillis()-start20+" ");
        System.out.println();
        //Array revese sorted
        System.out.println("Time Complexity of Insertion Sort if Arrays reverse sorted :");
        Arrays.sort(inputSize1, Collections.reverseOrder());
        long start111= System.currentTimeMillis();
        sort.insertionSort(inputSize1);
        System.out.print(System.currentTimeMillis()-start111+" ");
        Arrays.sort(inputSize2, Collections.reverseOrder());
        long start121= System.currentTimeMillis();
        sort.insertionSort(inputSize2);
        System.out.print(System.currentTimeMillis()-start121+" ");
        Arrays.sort(inputSize3, Collections.reverseOrder());
        long start131= System.currentTimeMillis();
        sort.insertionSort(inputSize3);
        System.out.print(System.currentTimeMillis()-start131+" ");
        Arrays.sort(inputSize4, Collections.reverseOrder());
        long start141= System.currentTimeMillis();
        sort.insertionSort(inputSize4);
        System.out.print(System.currentTimeMillis()-start141+" ");
        Arrays.sort(inputSize5, Collections.reverseOrder());
        long start151= System.currentTimeMillis();
        sort.insertionSort(inputSize5);
        System.out.print(System.currentTimeMillis()-start151+" ");
        Arrays.sort(inputSize6, Collections.reverseOrder());
        long start161= System.currentTimeMillis();
        sort.insertionSort(inputSize6);
        System.out.print(System.currentTimeMillis()-start161+" ");
        Arrays.sort(inputSize7, Collections.reverseOrder());
        long start171= System.currentTimeMillis();
        sort.insertionSort(inputSize7);
        System.out.print(System.currentTimeMillis()-start171+" ");
        Arrays.sort(inputSize8, Collections.reverseOrder());
        long start181= System.currentTimeMillis();
        sort.insertionSort(inputSize8);
        System.out.print(System.currentTimeMillis()-start181+" ");
        Arrays.sort(inputSize9, Collections.reverseOrder());
        long start191= System.currentTimeMillis();
        sort.insertionSort(inputSize9);
        System.out.print(System.currentTimeMillis()-start191+" ");
        Arrays.sort(inputSize10, Collections.reverseOrder());
        long start200= System.currentTimeMillis();
        sort.insertionSort(inputSize10);
        System.out.print(System.currentTimeMillis()-start200+" ");
        System.out.println();
        //Merge Sort
        //Array Not Sorted
        System.out.println("Time Complexity of Merge Sort if Arrays are not sorted :");
        long start21= System.currentTimeMillis();
        sort.mergeSort(inputSize1_merge,0,inputSize1_merge.length-1);
        System.out.print(System.currentTimeMillis()-start21+" ");
        long start22= System.currentTimeMillis();
        sort.mergeSort(inputSize2_merge,0,inputSize2_merge.length-1);
        System.out.print(System.currentTimeMillis()-start22+" ");
        long start23= System.currentTimeMillis();
        sort.mergeSort(inputSize3_merge,0,inputSize3_merge.length-1);
        System.out.print(System.currentTimeMillis()-start23+" ");
        long start24= System.currentTimeMillis();
        sort.mergeSort(inputSize4_merge,0,inputSize4_merge.length-1);
        System.out.print(System.currentTimeMillis()-start24+" ");
        long start25= System.currentTimeMillis();
        sort.mergeSort(inputSize5_merge,0,inputSize5_merge.length-1);
        System.out.print(System.currentTimeMillis()-start25+" ");
        long start26= System.currentTimeMillis();
        sort.mergeSort(inputSize6_merge,0,inputSize6_merge.length-1);
        System.out.print(System.currentTimeMillis()-start26+" ");
        long start27= System.currentTimeMillis();
        sort.mergeSort(inputSize7_merge,0,inputSize7_merge.length-1);
        System.out.print(System.currentTimeMillis()-start27+" ");
        long start28= System.currentTimeMillis();
        sort.mergeSort(inputSize8_merge,0,inputSize8_merge.length-1);
        System.out.print(System.currentTimeMillis()-start28+" ");
        long start29= System.currentTimeMillis();
        sort.mergeSort(inputSize9_merge,0,inputSize9_merge.length-1);
        System.out.print(System.currentTimeMillis()-start29+" ");
        long start30= System.currentTimeMillis();
        sort.mergeSort(inputSize10_merge,0,inputSize10_merge.length-1);
        System.out.print(System.currentTimeMillis()-start30+" ");
        System.out.println();
        // Array already Sorted
        System.out.println("Time Complexity of Merge Sort if Arrays sorted :");
        long start31= System.currentTimeMillis();
        sort.mergeSort(inputSize1_merge,0,inputSize1_merge.length-1);
        System.out.print(System.currentTimeMillis()-start31+" ");
        long start32= System.currentTimeMillis();
        sort.mergeSort(inputSize2_merge,0,inputSize2_merge.length-1);
        System.out.print(System.currentTimeMillis()-start32+" ");
        long start33= System.currentTimeMillis();
        sort.mergeSort(inputSize3_merge,0,inputSize3_merge.length-1);
        System.out.print(System.currentTimeMillis()-start33+" ");
        long start34= System.currentTimeMillis();
        sort.mergeSort(inputSize4_merge,0,inputSize4_merge.length-1);
        System.out.print(System.currentTimeMillis()-start34+" ");
        long start35= System.currentTimeMillis();
        sort.mergeSort(inputSize5_merge,0,inputSize5_merge.length-1);
        System.out.print(System.currentTimeMillis()-start35+" ");
        long start36= System.currentTimeMillis();
        sort.mergeSort(inputSize6_merge,0,inputSize6_merge.length-1);
        System.out.print(System.currentTimeMillis()-start36+" ");
        long start37= System.currentTimeMillis();
        sort.mergeSort(inputSize7_merge,0,inputSize7_merge.length-1);
        System.out.print(System.currentTimeMillis()-start37+" ");
        long start38= System.currentTimeMillis();
        sort.mergeSort(inputSize8_merge,0,inputSize8_merge.length-1);
        System.out.print(System.currentTimeMillis()-start38+" ");
        long start39= System.currentTimeMillis();
        sort.mergeSort(inputSize9_merge,0,inputSize9_merge.length-1);
        System.out.print(System.currentTimeMillis()-start39+" ");
        long start40= System.currentTimeMillis();
        sort.mergeSort(inputSize10_merge,0,inputSize10_merge.length-1);
        System.out.print(System.currentTimeMillis()-start40+" ");
        System.out.println();
        //Array reversely sorted
        System.out.println("Time Complexity of Merge Sort if Arrays reverse sorted :");
        Arrays.sort(inputSize1_merge, Collections.reverseOrder());
        long start311= System.currentTimeMillis();
        sort.mergeSort(inputSize1_merge,0,inputSize1_merge.length-1);
        System.out.print(System.currentTimeMillis()-start311+" ");
        Arrays.sort(inputSize2_merge, Collections.reverseOrder());
        long start322= System.currentTimeMillis();
        sort.mergeSort(inputSize2_merge,0,inputSize2_merge.length-1);
        System.out.print(System.currentTimeMillis()-start322+" ");
        Arrays.sort(inputSize3_merge, Collections.reverseOrder());
        long start333= System.currentTimeMillis();
        sort.mergeSort(inputSize3_merge,0,inputSize3_merge.length-1);
        System.out.print(System.currentTimeMillis()-start333+" ");
        Arrays.sort(inputSize4_merge, Collections.reverseOrder());
        long start344= System.currentTimeMillis();
        sort.mergeSort(inputSize4_merge,0,inputSize4_merge.length-1);
        System.out.print(System.currentTimeMillis()-start344+" ");
        Arrays.sort(inputSize5_merge, Collections.reverseOrder());
        long start355= System.currentTimeMillis();
        sort.mergeSort(inputSize5_merge,0,inputSize5_merge.length-1);
        System.out.print(System.currentTimeMillis()-start355+" ");
        Arrays.sort(inputSize6_merge, Collections.reverseOrder());
        long start366= System.currentTimeMillis();
        sort.mergeSort(inputSize6_merge,0,inputSize6_merge.length-1);
        System.out.print(System.currentTimeMillis()-start366+" ");
        Arrays.sort(inputSize7_merge, Collections.reverseOrder());
        long start377= System.currentTimeMillis();
        sort.mergeSort(inputSize7_merge,0,inputSize7_merge.length-1);
        System.out.print(System.currentTimeMillis()-start377+" ");
        Arrays.sort(inputSize8_merge, Collections.reverseOrder());
        long start388= System.currentTimeMillis();
        sort.mergeSort(inputSize8_merge,0,inputSize8_merge.length-1);
        System.out.print(System.currentTimeMillis()-start388+" ");
        Arrays.sort(inputSize9_merge, Collections.reverseOrder());
        long start399= System.currentTimeMillis();
        sort.mergeSort(inputSize9_merge,0,inputSize9_merge.length-1);
        System.out.print(System.currentTimeMillis()-start399+" ");
        Arrays.sort(inputSize10_merge, Collections.reverseOrder());
        long start400= System.currentTimeMillis();
        sort.mergeSort(inputSize10_merge,0,inputSize10_merge.length-1);
        System.out.print(System.currentTimeMillis()-start400+" ");
        System.out.println();
        //Quick Sort(In_place)
        //Array Not Sorted
        System.out.println("Time Complexity of Quick Sort if Arrays are not sorted :");
        long start41= System.currentTimeMillis();
        sort.quicksort(inputSize1_quick,0,inputSize1_quick.length-1);
        System.out.print(System.currentTimeMillis()-start41+" ");
        long start42= System.currentTimeMillis();
        sort.quicksort(inputSize2_quick,0,inputSize2_quick.length-1);
        System.out.print(System.currentTimeMillis()-start42+" ");
        long start43= System.currentTimeMillis();
        sort.quicksort(inputSize3_quick,0,inputSize3_quick.length-1);
        System.out.print(System.currentTimeMillis()-start43+" ");
        long start44= System.currentTimeMillis();
        sort.quicksort(inputSize4_quick,0,inputSize4_quick.length-1);
        System.out.print(System.currentTimeMillis()-start44+" ");
        long start45= System.currentTimeMillis();
        sort.quicksort(inputSize5_quick,0,inputSize5_quick.length-1);
        System.out.print(System.currentTimeMillis()-start45+" ");
        long start46= System.currentTimeMillis();
        sort.quicksort(inputSize6_quick,0,inputSize6_quick.length-1);
        System.out.print(System.currentTimeMillis()-start46+" ");
        long start47= System.currentTimeMillis();
        sort.quicksort(inputSize7_quick,0,inputSize7_quick.length-1);
        System.out.print(System.currentTimeMillis()-start47+" ");
        long start48= System.currentTimeMillis();
        sort.quicksort(inputSize8_quick,0,inputSize8_quick.length-1);
        System.out.print(System.currentTimeMillis()-start48+" ");
        long start49= System.currentTimeMillis();
        sort.quicksort(inputSize9_quick,0,inputSize9_quick.length-1);
        System.out.print(System.currentTimeMillis()-start49+" ");
        long start50= System.currentTimeMillis();
        sort.quicksort(inputSize10_quick,0,inputSize10_quick.length-1);
        System.out.print(System.currentTimeMillis()-start50+" ");
        System.out.println();
        // Array already Sorted
        System.out.println("Time Complexity of Quick Sort if Arrays sorted :");
        long start51= System.currentTimeMillis();
        sort.quicksort(inputSize1_quick,0,inputSize1_quick.length-1);
        System.out.print(System.currentTimeMillis()-start51+" ");
        long start52= System.currentTimeMillis();
        sort.quicksort(inputSize2_quick,0,inputSize2_quick.length-1);
        System.out.print(System.currentTimeMillis()-start52+" ");
        long start53= System.currentTimeMillis();
        sort.quicksort(inputSize3_quick,0,inputSize3_quick.length-1);
        System.out.print(System.currentTimeMillis()-start53+" ");
        long start54= System.currentTimeMillis();
        sort.quicksort(inputSize4_quick,0,inputSize4_quick.length-1);
        System.out.print(System.currentTimeMillis()-start54+" ");
        long start55= System.currentTimeMillis();
        sort.quicksort(inputSize5_quick,0,inputSize5_quick.length-1);
        System.out.print(System.currentTimeMillis()-start55+" ");
        long start56= System.currentTimeMillis();
        sort.quicksort(inputSize6_quick,0,inputSize6_quick.length-1);
        System.out.print(System.currentTimeMillis()-start56+" ");
        long start57= System.currentTimeMillis();
        sort.quicksort(inputSize7_quick,0,inputSize7_quick.length-1);
        System.out.print(System.currentTimeMillis()-start57+" ");
        long start58= System.currentTimeMillis();
        sort.quicksort(inputSize8_quick,0,inputSize8_quick.length-1);
        System.out.print(System.currentTimeMillis()-start58+" ");
        long start59= System.currentTimeMillis();
        sort.quicksort(inputSize9_quick,0,inputSize9_quick.length-1);
        System.out.print(System.currentTimeMillis()-start59+" ");
        long start60= System.currentTimeMillis();
        sort.quicksort(inputSize10_quick,0,inputSize10_quick.length-1);
        System.out.print(System.currentTimeMillis()-start60+" ");
        System.out.println();
        // Array reverse Sorted
        System.out.println("Time Complexity of Quick Sort if Arrays reverse sorted :");
        Arrays.sort(inputSize1_quick,Collections.reverseOrder());
        long start511= System.currentTimeMillis();
        sort.quicksort(inputSize1_quick,0,inputSize1_quick.length-1);
        System.out.print(System.currentTimeMillis()-start511+" ");
        Arrays.sort(inputSize2_quick,Collections.reverseOrder());
        long start522= System.currentTimeMillis();
        sort.quicksort(inputSize2_quick,0,inputSize2_quick.length-1);
        System.out.print(System.currentTimeMillis()-start522+" ");
        Arrays.sort(inputSize3_quick,Collections.reverseOrder());
        long start533= System.currentTimeMillis();
        sort.quicksort(inputSize3_quick,0,inputSize3_quick.length-1);
        System.out.print(System.currentTimeMillis()-start533+" ");
        Arrays.sort(inputSize4_quick,Collections.reverseOrder());
        long start544= System.currentTimeMillis();
        sort.quicksort(inputSize4_quick,0,inputSize4_quick.length-1);
        System.out.print(System.currentTimeMillis()-start544+" ");
        Arrays.sort(inputSize5_quick,Collections.reverseOrder());
        long start555= System.currentTimeMillis();
        sort.quicksort(inputSize5_quick,0,inputSize5_quick.length-1);
        System.out.print(System.currentTimeMillis()-start555+" ");
        Arrays.sort(inputSize6_quick,Collections.reverseOrder());
        long start566= System.currentTimeMillis();
        sort.quicksort(inputSize6_quick,0,inputSize6_quick.length-1);
        System.out.print(System.currentTimeMillis()-start566+" ");
        Arrays.sort(inputSize7_quick,Collections.reverseOrder());
        long start577= System.currentTimeMillis();
        sort.quicksort(inputSize7_quick,0,inputSize7_quick.length-1);
        System.out.print(System.currentTimeMillis()-start577+" ");
        Arrays.sort(inputSize8_quick,Collections.reverseOrder());
        long start588= System.currentTimeMillis();
        sort.quicksort(inputSize8_quick,0,inputSize8_quick.length-1);
        System.out.print(System.currentTimeMillis()-start588+" ");
        Arrays.sort(inputSize9_quick,Collections.reverseOrder());
        long start599= System.currentTimeMillis();
        sort.quicksort(inputSize9_quick,0,inputSize9_quick.length-1);
        System.out.print(System.currentTimeMillis()-start599+" ");
        Arrays.sort(inputSize10_quick,Collections.reverseOrder());
        long start600= System.currentTimeMillis();
        sort.quicksort(inputSize10_quick,0,inputSize10_quick.length-1);
        System.out.print(System.currentTimeMillis()-start600+" ");
        System.out.println();
        //Modified Quick Sort(In_place)
        //Array Not Sorted
        System.out.println("Time Complexity of Modified Quick Sort if Arrays are not sorted :");
        long start61= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize1_mq,0,inputSize1_mq.length-1);
        System.out.print(System.currentTimeMillis()-start61+" ");
        long start62= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize2_mq,0,inputSize2_mq.length-1);
        System.out.print(System.currentTimeMillis()-start62+" ");
        long start63= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize3_mq,0,inputSize3_mq.length-1);
        System.out.print(System.currentTimeMillis()-start63+" ");
        long start64= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize4_mq,0,inputSize4_mq.length-1);
        System.out.print(System.currentTimeMillis()-start64+" ");
        long start65= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize5_mq,0,inputSize5_mq.length-1);
        System.out.print(System.currentTimeMillis()-start65+" ");
        long start66= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize6_mq,0,inputSize6_mq.length-1);
        System.out.print(System.currentTimeMillis()-start66+" ");
        long start67= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize7_mq,0,inputSize7_mq.length-1);
        System.out.print(System.currentTimeMillis()-start67+" ");
        long start68= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize8_mq,0,inputSize8_mq.length-1);
        System.out.print(System.currentTimeMillis()-start68+" ");
        long start69= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize9_mq,0,inputSize9_mq.length-1);
        System.out.print(System.currentTimeMillis()-start69+" ");
        long start70= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize10_mq,0,inputSize10_mq.length-1);
        System.out.print(System.currentTimeMillis()-start70+" ");
        System.out.println();
        // Array already Sorted
        System.out.println("Time Complexity of Modified Quick Sort if Arrays sorted :");
        long start71= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize1_mq,0,inputSize1_mq.length-1);
        System.out.print(System.currentTimeMillis()-start71+" ");
        long start72= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize2_mq,0,inputSize2_mq.length-1);
        System.out.print(System.currentTimeMillis()-start72+" ");
        long start73= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize3_mq,0,inputSize3_mq.length-1);
        System.out.print(System.currentTimeMillis()-start73+" ");
        long start74= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize4_mq,0,inputSize4_mq.length-1);
        System.out.print(System.currentTimeMillis()-start74+" ");
        long start75= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize5_mq,0,inputSize5_mq.length-1);
        System.out.print(System.currentTimeMillis()-start75+" ");
        long start76= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize6_mq,0,inputSize6_mq.length-1);
        System.out.print(System.currentTimeMillis()-start76+" ");
        long start77= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize7_mq,0,inputSize7_quick.length-1);
        System.out.print(System.currentTimeMillis()-start77+" ");
        long start78= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize8_mq,0,inputSize8_quick.length-1);
        System.out.print(System.currentTimeMillis()-start78+" ");
        long start79= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize9_mq,0,inputSize9_quick.length-1);
        System.out.print(System.currentTimeMillis()-start79+" ");
        long start80= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize10_mq,0,inputSize10_mq.length-1);
        System.out.print(System.currentTimeMillis()-start80+" ");
        System.out.println();
        // Array reverse Sorted
        System.out.println("Time Complexity of Modified Quick Sort if Arrays reverse sorted :");
        Arrays.sort(inputSize1_mq,Collections.reverseOrder());
        long start711= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize1_mq,0,inputSize1_mq.length-1);
        System.out.print(System.currentTimeMillis()-start711+" ");
        Arrays.sort(inputSize2_mq,Collections.reverseOrder());
        long start722= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize2_mq,0,inputSize2_mq.length-1);
        System.out.print(System.currentTimeMillis()-start722+" ");
        Arrays.sort(inputSize3_mq,Collections.reverseOrder());
        long start733= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize3_mq,0,inputSize3_mq.length-1);
        System.out.print(System.currentTimeMillis()-start733+" ");
        Arrays.sort(inputSize4_mq,Collections.reverseOrder());
        long start744= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize4_mq,0,inputSize4_mq.length-1);
        System.out.print(System.currentTimeMillis()-start744+" ");
        Arrays.sort(inputSize5_mq,Collections.reverseOrder());
        long start755= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize5_mq,0,inputSize5_mq.length-1);
        System.out.print(System.currentTimeMillis()-start755+" ");
        Arrays.sort(inputSize6_mq,Collections.reverseOrder());
        long start766= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize6_mq,0,inputSize6_mq.length-1);
        System.out.print(System.currentTimeMillis()-start766+" ");
        Arrays.sort(inputSize7_mq,Collections.reverseOrder());
        long start777= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize7_mq,0,inputSize7_quick.length-1);
        System.out.print(System.currentTimeMillis()-start777+" ");
        Arrays.sort(inputSize8_mq,Collections.reverseOrder());
        long start788= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize8_mq,0,inputSize8_quick.length-1);
        System.out.print(System.currentTimeMillis()-start788+" ");
        Arrays.sort(inputSize9_mq,Collections.reverseOrder());
        long start799= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize9_mq,0,inputSize9_quick.length-1);
        System.out.print(System.currentTimeMillis()-start799+" ");
        Arrays.sort(inputSize10_mq,Collections.reverseOrder());
        long start800= System.currentTimeMillis();
        sort.modifiedQuickSort(inputSize10_mq,0,inputSize10_mq.length-1);
        System.out.print(System.currentTimeMillis()-start800+" ");
        System.out.println();
        //Array Not Sort
        System.out.println("Time Complexity of heap Sort if Arrays are not sorted :");
        long start81= System.currentTimeMillis();
        MinHeapTree minHeapTree1= new MinHeapTree(inputSize1_heap.length);
        for (int i=0;i<inputSize1_heap.length;i++){
            sort.insertKey(minHeapTree1,inputSize1_heap[i]);
        }
        for (int i=0;i<inputSize1_heap.length;i++){
            inputSize1_heap[i]=sort.extractMin(minHeapTree1);
        }
        System.out.print(System.currentTimeMillis()-start81+" ");
        long start82= System.currentTimeMillis();
        MinHeapTree minHeapTree2= new MinHeapTree(inputSize2_heap.length);
        for (int i=0;i<inputSize2_heap.length;i++){
            sort.insertKey(minHeapTree2,inputSize2_heap[i]);
        }
        for (int i=0;i<inputSize2_heap.length;i++){
            inputSize2_heap[i]=sort.extractMin(minHeapTree2);
        }
        System.out.print(System.currentTimeMillis()-start82+" ");
        long start83= System.currentTimeMillis();
        MinHeapTree minHeapTree3= new MinHeapTree(inputSize3_heap.length);
        for (int i=0;i<inputSize3_heap.length;i++){
            sort.insertKey(minHeapTree3,inputSize3_heap[i]);
        }
        for (int i=0;i<inputSize3_heap.length;i++){
            inputSize3_heap[i]=sort.extractMin(minHeapTree3);
        }
        System.out.print(System.currentTimeMillis()-start83+" ");
        long start84= System.currentTimeMillis();
        MinHeapTree minHeapTree4= new MinHeapTree(inputSize4_heap.length);
        for (int i=0;i<inputSize4_heap.length;i++){
            sort.insertKey(minHeapTree4,inputSize4_heap[i]);
        }
        for (int i=0;i<inputSize4_heap.length;i++){
            inputSize4_heap[i]=sort.extractMin(minHeapTree4);
        }
        System.out.print(System.currentTimeMillis()-start84+" ");
        long start85= System.currentTimeMillis();
        MinHeapTree minHeapTree5= new MinHeapTree(inputSize5_heap.length);
        for (int i=0;i<inputSize5_heap.length;i++){
            sort.insertKey(minHeapTree5,inputSize5_heap[i]);
        }
        for (int i=0;i<inputSize5_heap.length;i++){
            inputSize5_heap[i]=sort.extractMin(minHeapTree5);
        }
        System.out.print(System.currentTimeMillis()-start85+" ");
        long start86= System.currentTimeMillis();
        MinHeapTree minHeapTree6= new MinHeapTree(inputSize6_heap.length);
        for (int i=0;i<inputSize6_heap.length;i++){
            sort.insertKey(minHeapTree6,inputSize6_heap[i]);
        }
        for (int i=0;i<inputSize6_heap.length;i++){
            inputSize6_heap[i]=sort.extractMin(minHeapTree6);
        }
        System.out.print(System.currentTimeMillis()-start86+" ");
        long start87= System.currentTimeMillis();
        MinHeapTree minHeapTree7= new MinHeapTree(inputSize7_heap.length);
        for (int i=0;i<inputSize7_heap.length;i++){
            sort.insertKey(minHeapTree7,inputSize7_heap[i]);
        }
        for (int i=0;i<inputSize7_heap.length;i++){
            inputSize7_heap[i]=sort.extractMin(minHeapTree7);
        }
        System.out.print(System.currentTimeMillis()-start87+" ");
        long start88= System.currentTimeMillis();
        MinHeapTree minHeapTree8= new MinHeapTree(inputSize8_heap.length);
        for (int i=0;i<inputSize8_heap.length;i++){
            sort.insertKey(minHeapTree8,inputSize8_heap[i]);
        }
        for (int i=0;i<inputSize8_heap.length;i++){
            inputSize8_heap[i]=sort.extractMin(minHeapTree8);
        }
        System.out.print(System.currentTimeMillis()-start88+" ");
        long start89= System.currentTimeMillis();
        MinHeapTree minHeapTree9= new MinHeapTree(inputSize9_heap.length);
        for (int i=0;i<inputSize9_heap.length;i++){
            sort.insertKey(minHeapTree9,inputSize9_heap[i]);
        }
        for (int i=0;i<inputSize9_heap.length;i++){
            inputSize9_heap[i]=sort.extractMin(minHeapTree9);
        }
        System.out.print(System.currentTimeMillis()-start89+" ");
        long start90= System.currentTimeMillis();
        MinHeapTree minHeapTree10= new MinHeapTree(inputSize10_heap.length);
        for (int i=0;i<inputSize10_heap.length;i++){
            sort.insertKey(minHeapTree10,inputSize10_heap[i]);
        }
        for (int i=0;i<inputSize10_heap.length;i++){
            inputSize10_heap[i]=sort.extractMin(minHeapTree10);
        }
        System.out.print(System.currentTimeMillis()-start90+" ");
        System.out.println();
        //Array already Sort
        System.out.println("Time Complexity of heap Sort if Arrays is already sorted :");
        long start91= System.currentTimeMillis();
        MinHeapTree minHeapTree11= new MinHeapTree(inputSize1_heap.length);
        for (int i=0;i<inputSize1_heap.length;i++){
            sort.insertKey(minHeapTree11,inputSize1_heap[i]);
        }
        for (int i=0;i<inputSize1_heap.length;i++){
            inputSize1_heap[i]=sort.extractMin(minHeapTree11);
        }
        System.out.print(System.currentTimeMillis()-start91+" ");
        long start92= System.currentTimeMillis();
        MinHeapTree minHeapTree12= new MinHeapTree(inputSize2_heap.length);
        for (int i=0;i<inputSize2_heap.length;i++){
            sort.insertKey(minHeapTree12,inputSize2_heap[i]);
        }
        for (int i=0;i<inputSize2_heap.length;i++){
            inputSize2_heap[i]=sort.extractMin(minHeapTree12);
        }
        System.out.print(System.currentTimeMillis()-start92+" ");
        long start93= System.currentTimeMillis();
        MinHeapTree minHeapTree13= new MinHeapTree(inputSize3_heap.length);
        for (int i=0;i<inputSize3_heap.length;i++){
            sort.insertKey(minHeapTree13,inputSize3_heap[i]);
        }
        for (int i=0;i<inputSize3_heap.length;i++){
            inputSize3_heap[i]=sort.extractMin(minHeapTree13);
        }
        System.out.print(System.currentTimeMillis()-start93+" ");
        long start94= System.currentTimeMillis();
        MinHeapTree minHeapTree14= new MinHeapTree(inputSize4_heap.length);
        for (int i=0;i<inputSize4_heap.length;i++){
            sort.insertKey(minHeapTree14,inputSize4_heap[i]);
        }
        for (int i=0;i<inputSize4_heap.length;i++){
            inputSize4_heap[i]=sort.extractMin(minHeapTree14);
        }
        System.out.print(System.currentTimeMillis()-start94+" ");
        long start95= System.currentTimeMillis();
        MinHeapTree minHeapTree15= new MinHeapTree(inputSize5_heap.length);
        for (int i=0;i<inputSize5_heap.length;i++){
            sort.insertKey(minHeapTree15,inputSize5_heap[i]);
        }
        for (int i=0;i<inputSize5_heap.length;i++){
            inputSize5_heap[i]=sort.extractMin(minHeapTree15);
        }
        System.out.print(System.currentTimeMillis()-start95+" ");
        long start96= System.currentTimeMillis();
        MinHeapTree minHeapTree16= new MinHeapTree(inputSize6_heap.length);
        for (int i=0;i<inputSize6_heap.length;i++){
            sort.insertKey(minHeapTree16,inputSize6_heap[i]);
        }
        for (int i=0;i<inputSize6_heap.length;i++){
            inputSize6_heap[i]=sort.extractMin(minHeapTree16);
        }
        System.out.print(System.currentTimeMillis()-start96+" ");
        long start97= System.currentTimeMillis();
        MinHeapTree minHeapTree17= new MinHeapTree(inputSize7_heap.length);
        for (int i=0;i<inputSize7_heap.length;i++){
            sort.insertKey(minHeapTree17,inputSize7_heap[i]);
        }
        for (int i=0;i<inputSize7_heap.length;i++){
            inputSize7_heap[i]=sort.extractMin(minHeapTree17);
        }
        System.out.print(System.currentTimeMillis()-start97+" ");
        long start98= System.currentTimeMillis();
        MinHeapTree minHeapTree18= new MinHeapTree(inputSize8_heap.length);
        for (int i=0;i<inputSize8_heap.length;i++){
            sort.insertKey(minHeapTree18,inputSize8_heap[i]);
        }
        for (int i=0;i<inputSize8_heap.length;i++){
            inputSize8_heap[i]=sort.extractMin(minHeapTree18);
        }
        System.out.print(System.currentTimeMillis()-start98+" ");
        long start99= System.currentTimeMillis();
        MinHeapTree minHeapTree19= new MinHeapTree(inputSize9_heap.length);
        for (int i=0;i<inputSize9_heap.length;i++){
            sort.insertKey(minHeapTree19,inputSize9_heap[i]);
        }
        for (int i=0;i<inputSize9_heap.length;i++){
            inputSize9_heap[i]=sort.extractMin(minHeapTree19);
        }
        System.out.print(System.currentTimeMillis()-start99+" ");
        long start100= System.currentTimeMillis();
        MinHeapTree minHeapTree20= new MinHeapTree(inputSize10_heap.length);
        for (int i=0;i<inputSize10_heap.length;i++){
            sort.insertKey(minHeapTree20,inputSize10_heap[i]);
        }
        for (int i=0;i<inputSize10_heap.length;i++){
            inputSize10_heap[i]=sort.extractMin(minHeapTree20);
        }
        System.out.print(System.currentTimeMillis()-start100+" ");
        System.out.println();
        //Array reversly Sort
        System.out.println("Time Complexity of heap Sort if Arrays is reversly sorted :");
        Arrays.sort(inputSize1_heap,Collections.reverseOrder());
        long start101= System.currentTimeMillis();
        MinHeapTree minHeapTree21= new MinHeapTree(inputSize1_heap.length);
        for (int i=0;i<inputSize1_heap.length;i++){
            sort.insertKey(minHeapTree21,inputSize1_heap[i]);
        }
        for (int i=0;i<inputSize1_heap.length;i++){
            inputSize1_heap[i]=sort.extractMin(minHeapTree21);
        }
        System.out.print(System.currentTimeMillis()-start101+" ");
        Arrays.sort(inputSize2_heap,Collections.reverseOrder());
        long start102= System.currentTimeMillis();
        MinHeapTree minHeapTree22= new MinHeapTree(inputSize2_heap.length);
        for (int i=0;i<inputSize2_heap.length;i++){
            sort.insertKey(minHeapTree22,inputSize2_heap[i]);
        }
        for (int i=0;i<inputSize2_heap.length;i++){
            inputSize2_heap[i]=sort.extractMin(minHeapTree22);
        }
        System.out.print(System.currentTimeMillis()-start102+" ");
        Arrays.sort(inputSize3_heap,Collections.reverseOrder());
        long start103= System.currentTimeMillis();
        MinHeapTree minHeapTree23= new MinHeapTree(inputSize3_heap.length);
        for (int i=0;i<inputSize3_heap.length;i++){
            sort.insertKey(minHeapTree23,inputSize3_heap[i]);
        }
        for (int i=0;i<inputSize3_heap.length;i++){
            inputSize3_heap[i]=sort.extractMin(minHeapTree23);
        }
        System.out.print(System.currentTimeMillis()-start103+" ");
        Arrays.sort(inputSize4_heap,Collections.reverseOrder());
        long start104= System.currentTimeMillis();
        MinHeapTree minHeapTree24= new MinHeapTree(inputSize4_heap.length);
        for (int i=0;i<inputSize4_heap.length;i++){
            sort.insertKey(minHeapTree24,inputSize4_heap[i]);
        }
        for (int i=0;i<inputSize4_heap.length;i++){
            inputSize4_heap[i]=sort.extractMin(minHeapTree24);
        }
        System.out.print(System.currentTimeMillis()-start104+" ");
        Arrays.sort(inputSize5_heap,Collections.reverseOrder());
        long start105= System.currentTimeMillis();
        MinHeapTree minHeapTree25= new MinHeapTree(inputSize5_heap.length);
        for (int i=0;i<inputSize5_heap.length;i++){
            sort.insertKey(minHeapTree25,inputSize5_heap[i]);
        }
        for (int i=0;i<inputSize5_heap.length;i++){
            inputSize5_heap[i]=sort.extractMin(minHeapTree25);
        }
        System.out.print(System.currentTimeMillis()-start105+" ");
        Arrays.sort(inputSize6_heap,Collections.reverseOrder());
        long start106= System.currentTimeMillis();
        MinHeapTree minHeapTree26= new MinHeapTree(inputSize6_heap.length);
        for (int i=0;i<inputSize6_heap.length;i++){
            sort.insertKey(minHeapTree26,inputSize6_heap[i]);
        }
        for (int i=0;i<inputSize6_heap.length;i++){
            inputSize6_heap[i]=sort.extractMin(minHeapTree26);
        }
        System.out.print(System.currentTimeMillis()-start106+" ");
        Arrays.sort(inputSize7_heap,Collections.reverseOrder());
        long start107= System.currentTimeMillis();
        MinHeapTree minHeapTree27= new MinHeapTree(inputSize7_heap.length);
        for (int i=0;i<inputSize7_heap.length;i++){
            sort.insertKey(minHeapTree27,inputSize7_heap[i]);
        }
        for (int i=0;i<inputSize7_heap.length;i++){
            inputSize7_heap[i]=sort.extractMin(minHeapTree27);
        }
        System.out.print(System.currentTimeMillis()-start107+" ");
        Arrays.sort(inputSize8_heap,Collections.reverseOrder());
        long start108= System.currentTimeMillis();
        MinHeapTree minHeapTree28= new MinHeapTree(inputSize8_heap.length);
        for (int i=0;i<inputSize8_heap.length;i++){
            sort.insertKey(minHeapTree28,inputSize8_heap[i]);
        }
        for (int i=0;i<inputSize8_heap.length;i++){
            inputSize8_heap[i]=sort.extractMin(minHeapTree28);
        }
        System.out.print(System.currentTimeMillis()-start108+" ");
        Arrays.sort(inputSize9_heap,Collections.reverseOrder());
        long start109= System.currentTimeMillis();
        MinHeapTree minHeapTree29= new MinHeapTree(inputSize9_heap.length);
        for (int i=0;i<inputSize9_heap.length;i++){
            sort.insertKey(minHeapTree29,inputSize9_heap[i]);
        }
        for (int i=0;i<inputSize9_heap.length;i++){
            inputSize9_heap[i]=sort.extractMin(minHeapTree29);
        }
        System.out.print(System.currentTimeMillis()-start109+" ");
        Arrays.sort(inputSize10_heap,Collections.reverseOrder());
        long start201= System.currentTimeMillis();
        MinHeapTree minHeapTree30= new MinHeapTree(inputSize10_heap.length);
        for (int i=0;i<inputSize10_heap.length;i++){
            sort.insertKey(minHeapTree30,inputSize10_heap[i]);
        }
        for (int i=0;i<inputSize10_heap.length;i++){
            inputSize10_heap[i]=sort.extractMin(minHeapTree30);
        }
        System.out.print(System.currentTimeMillis()-start201+" ");
    }
}
//MinHeaptree consisting of Vector
class MinHeapTree{
    int size;
    int capacity;
    Vector<Integer> vector;
    MinHeapTree(int capacity){
        this.size=0;
        this.capacity=capacity;
        vector=new Vector<>();
    }
}