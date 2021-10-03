package org.devbuild.algoservice.sort;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SortServiceImpl implements SortService {

    long timeInMills = 0;


    @Override
    public List<SortEl> sort(List<SortEl> nodes) throws InterruptedException {
        int start = 0;
        int end = nodes.size() - 1;

        mergeSort(nodes, start, end, 0);

        return nodes;
    }

    @Override
    public void mergeSort(List<SortEl> nodes, int start, int end, long timeInMills) throws InterruptedException {

        if (start != end) {
            int mid = (start + end) / 2;
            mergeSort(nodes, start, mid, timeInMills);
            mergeSort(nodes, mid + 1, end, timeInMills);
            int i = start;
            int j = mid + 1;
            List<SortEl> sortedSortEls = new ArrayList<>();
            while (i <= mid && j <= end) {
                SortEl nodeI = nodes.get(i);
                SortEl nodeJ = nodes.get(j);

                if (nodeI.lessThan(nodeJ)) {
                    sortedSortEls.add(nodeI);
                    i++;
                } else {
                    sortedSortEls.add(nodeJ);
                    j++;
                }
            }
            while (i <= mid) {
                sortedSortEls.add(nodes.get(i));
                i++;
            }
            while (j <= end) {
                sortedSortEls.add(nodes.get(j));
                j++;
            }
            for (int k = start; k <= end; k++) {
                nodes.set(k, sortedSortEls.get(k - start));
            }
        }

        Thread.sleep(timeInMills);

    }

    @Override
    public void populateSortElData(List<SortEl> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).setIndex(i);
        }
    }

    public void swap(List<SortEl> nodes, int i, int j) throws InterruptedException {
        SortEl temp = nodes.get(i);
        nodes.set(i, nodes.get(j));
        nodes.set(j, temp);

        nodes.get(i).setIndex(i);
        nodes.get(j).setIndex(j);

        Thread.sleep(timeInMills);
    }

    @Override
    public void maxHeap(List<SortEl> nodes, int i, int n) throws InterruptedException {

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        int greaterSortEl = i;

        if (left < n && nodes.get(greaterSortEl).lessThan(nodes.get(left))) {
            greaterSortEl = left;
        }

        if (right < n && nodes.get(greaterSortEl).lessThan(nodes.get(right))) {
            greaterSortEl = right;
        }

        if(greaterSortEl != i){
            swap(nodes, i, greaterSortEl);
            maxHeap(nodes, greaterSortEl, n);
        }

    }

    @Override
    public List<SortEl> heapSort(List<SortEl> nodes, long timeInMills) throws InterruptedException {

        this.timeInMills = timeInMills;

        for(int i = (nodes.size()-1)/2; i>=0;  i--){
            maxHeap(nodes, i, nodes.size());
        }

        int i = nodes.size() - 1;
        while (i>0) {

            maxHeap(nodes, 0, i + 1);
            swap(nodes, 0, i);

            i--;
        }

        return nodes;
    }

    @Override
    public void bubbleSort(List<SortEl> nodes, long timeInMills) throws InterruptedException {

        this.timeInMills = timeInMills;

        boolean swapped;

        for (int i = 0; i < nodes.size() - 1; i++) {
            swapped = false;
            for (int j = 0; j < nodes.size() - i - 1; j++) {
                if (!nodes.get(j).lessThan(nodes.get(j + 1))) {
                    swap(nodes, j + 1, j);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}
