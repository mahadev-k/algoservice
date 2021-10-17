package org.devbuild.algoservice.sort;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SortServiceImpl implements SortService {

    long timeInMills = 0;


    @Override
    public List<SortEl> sort(List<SortEl> sortEls) throws InterruptedException {
        int start = 0;
        int end = sortEls.size() - 1;

        mergeSort(sortEls, start, end, 0);

        return sortEls;
    }

    @Override
    public void mergeSort(List<SortEl> sortEls, int start, int end, long timeInMills) throws InterruptedException {

        if (start != end) {
            int mid = (start + end) / 2;
            mergeSort(sortEls, start, mid, timeInMills);
            mergeSort(sortEls, mid + 1, end, timeInMills);
            int i = start;
            int j = mid + 1;
            List<SortEl> sortedSortEls = new ArrayList<>();
            while (i <= mid && j <= end) {
                SortEl nodeI = sortEls.get(i);
                SortEl nodeJ = sortEls.get(j);

                if (nodeI.lessThan(nodeJ)) {
                    sortedSortEls.add(nodeI);
                    i++;
                } else {
                    sortedSortEls.add(nodeJ);
                    j++;
                }
            }
            while (i <= mid) {
                sortedSortEls.add(sortEls.get(i));
                i++;
            }
            while (j <= end) {
                sortedSortEls.add(sortEls.get(j));
                j++;
            }
            for (int k = start; k <= end; k++) {
                sortEls.set(k, sortedSortEls.get(k - start));
            }
        }

        Thread.sleep(timeInMills);

    }

    @Override
    public void populateSortElData(List<SortEl> sortEls) {
        for (int i = 0; i < sortEls.size(); i++) {
            sortEls.get(i).setIndex(i);
        }
    }

    public void swap(List<SortEl> sortEls, int i, int j) throws InterruptedException {
        SortEl temp = sortEls.get(i);
        sortEls.set(i, sortEls.get(j));
        sortEls.set(j, temp);

        sortEls.get(i).setIndex(i);
        sortEls.get(j).setIndex(j);

        Thread.sleep(timeInMills);
    }

    @Override
    public void maxHeap(List<SortEl> sortEls, int i, int n) throws InterruptedException {

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        int greaterSortEl = i;

        if (left < n && sortEls.get(greaterSortEl).lessThan(sortEls.get(left))) {
            greaterSortEl = left;
        }

        if (right < n && sortEls.get(greaterSortEl).lessThan(sortEls.get(right))) {
            greaterSortEl = right;
        }

        if(greaterSortEl != i){
            swap(sortEls, i, greaterSortEl);
            maxHeap(sortEls, greaterSortEl, n);
        }

    }

    @Override
    public List<SortEl> heapSort(List<SortEl> sortEls, long timeInMills) throws InterruptedException {

        this.timeInMills = timeInMills;

        for(int i = (sortEls.size()-1)/2; i>=0;  i--){
            maxHeap(sortEls, i, sortEls.size());
        }

        int i = sortEls.size() - 1;
        while (i>0) {

            maxHeap(sortEls, 0, i + 1);
            swap(sortEls, 0, i);

            i--;
        }

        return sortEls;
    }

    @Override
    public void bubbleSort(List<SortEl> sortEls, long timeInMills) throws InterruptedException {

        this.timeInMills = timeInMills;

        boolean swapped;

        for (int i = 0; i < sortEls.size() - 1; i++) {
            swapped = false;
            for (int j = 0; j < sortEls.size() - i - 1; j++) {
                if (!sortEls.get(j).lessThan(sortEls.get(j + 1))) {
                    swap(sortEls, j + 1, j);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}
