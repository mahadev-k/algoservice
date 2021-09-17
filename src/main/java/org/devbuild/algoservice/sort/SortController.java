package org.devbuild.algoservice.sort;

import lombok.extern.slf4j.Slf4j;
import org.devbuild.algoservice.dto.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("sort")
@Slf4j
public class SortController implements SortControllerInterface{

    @Autowired
    SortService sortService;

    List<Node> currentNodes = new ArrayList<>();

    @Override
    public ResponseEntity<List<Node>> getCurrentSortedList() {
        return ResponseEntity.ok(currentNodes);
    }

    @Override
    public ResponseEntity<List<Node>> sort(List<Node> nodes) throws InterruptedException{

        currentNodes = nodes;

        sortService.sort(nodes);

        return ResponseEntity.ok(nodes);
    }

    @Override
    public ResponseEntity<List<Node>> mergeSort(List<Node> nodes, long timeInMills) throws InterruptedException{

        currentNodes = nodes;

        Runnable mergeSort = () -> {
            try {
                sortService.mergeSort(nodes, 0, nodes.size() - 1, timeInMills);
            } catch (InterruptedException e) {
                log.error("An Exception occurred while sorting -> MergeSort ",e);
            }
        };

        Thread mergeSortThread = new Thread(mergeSort);

        mergeSortThread.run();
        mergeSortThread.join();

        return ResponseEntity.ok(nodes);
    }

    @Override
    public ResponseEntity<List<Node>> heapSort(List<Node> nodes, long timeInMills) throws InterruptedException {

        currentNodes = nodes;

        sortService.heapSort(nodes, timeInMills);

        return ResponseEntity.ok(nodes);
    }

    @Override
    public ResponseEntity<List<Node>> bubbleSort(List<Node> nodes, long timeInMills) throws InterruptedException {

        currentNodes = nodes;

        sortService.bubbleSort(nodes, timeInMills);

        return ResponseEntity.ok(nodes);
    }


}
