package org.devbuild.algoservice.sort;

import org.devbuild.algoservice.dto.Node;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SortServiceImpl implements SortService {

    long timeInMills = 0;


    @Override
    public List<Node> sort(List<Node> nodes) throws InterruptedException {
        int start = 0;
        int end = nodes.size() - 1;

        mergeSort(nodes, start, end, 0);

        return nodes;
    }

    @Override
    public void mergeSort(List<Node> nodes, int start, int end, long timeInMills) throws InterruptedException {

        if (start != end) {
            int mid = (start + end) / 2;
            mergeSort(nodes, start, mid, timeInMills);
            mergeSort(nodes, mid + 1, end, timeInMills);
            int i = start;
            int j = mid + 1;
            List<Node> sortedNodes = new ArrayList<>();
            while (i <= mid && j <= end) {
                Node nodeI = nodes.get(i);
                Node nodeJ = nodes.get(j);

                if (nodeI.lessThan(nodeJ)) {
                    sortedNodes.add(nodeI);
                    i++;
                } else {
                    sortedNodes.add(nodeJ);
                    j++;
                }
            }
            while (i <= mid) {
                sortedNodes.add(nodes.get(i));
                i++;
            }
            while (j <= end) {
                sortedNodes.add(nodes.get(j));
                j++;
            }
            for (int k = start; k <= end; k++) {
                nodes.set(k, sortedNodes.get(k - start));
            }
        }

        Thread.sleep(timeInMills);

    }

    @Override
    public void populateNodeData(List<Node> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).setIndex(i);
        }
    }

    @Override
    public Node createTreeFromArray(List<Node> nodes, int i) {
        Node result = null;

        if (i < nodes.size()) {
            nodes.get(i).setLeft(createTreeFromArray(nodes, 2 * i + 1));
            nodes.get(i).setRight(createTreeFromArray(nodes, 2 * i + 2));

            if (i % 2 == 0) {
                nodes.get(i).setColor("Blue");
            } else {
                nodes.get(i).setColor("Green");
            }

            result = nodes.get(i);
        }

        return result;
    }

    @Override
    public void swap(Node node1, Node node2, boolean leftRoot) {

        Node leftChild = node1.getLeft();
        Node rightChild = node1.getRight();

        //root
        node1.setLeft(node2.getLeft());
        node1.setRight(node2.getRight());

        if (leftRoot) {
            //left child
            node2.setLeft(node1);
            node2.setRight(rightChild);
        } else {
            //right
            node2.setRight(node1);
            node2.setLeft(leftChild);
        }
    }

    public void swap(List<Node> nodes, int i, int j) throws InterruptedException {
        Node temp = nodes.get(i);
        nodes.set(i, nodes.get(j));
        nodes.set(j, temp);

        nodes.get(i).setIndex(i);
        nodes.get(j).setIndex(j);

        Thread.sleep(timeInMills);
    }

    @Override
    public void maxHeap(List<Node> nodes, int i, int n) throws InterruptedException {

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        int greaterNode = i;

        if (left < n && nodes.get(greaterNode).lessThan(nodes.get(left))) {
            greaterNode = left;
        }

        if (right < n && nodes.get(greaterNode).lessThan(nodes.get(right))) {
            greaterNode = right;
        }

        if(greaterNode != i){
            swap(nodes, i, greaterNode);
            maxHeap(nodes, greaterNode, n);
        }

    }

    @Override
    public List<Node> heapSort(List<Node> nodes, long timeInMills) throws InterruptedException {

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
    public void bubbleSort(List<Node> nodes, long timeInMills) throws InterruptedException {

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
