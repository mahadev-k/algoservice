package org.devbuild.algoservice.sort;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("sort")
@Slf4j
public class SortController implements SortControllerInterface{

    @Autowired
    SortService sortService;

    List<SortEl> currentSortEls = new ArrayList<>();

    Map<String,List<SortEl>> currentSortElsMap = new HashMap<>();

    @Override
    public ResponseEntity<List<SortEl>> getCurrentSortedList(String sortId) {
        return ResponseEntity.ok(currentSortElsMap.get(sortId));
    }

    @Override
    public ResponseEntity<List<SortEl>> sort(List<SortEl> nodes) throws InterruptedException{

        currentSortEls = nodes;

        sortService.sort(nodes);

        return ResponseEntity.ok(nodes);
    }

    @Override
    public ResponseEntity<List<SortEl>> mergeSort(List<SortEl> nodes, long timeInMills, String sortId) throws InterruptedException{

        currentSortElsMap.put(sortId, nodes);

        Runnable mergeSort = () -> {
            try {
                sortService.mergeSort(nodes, 0, nodes.size() - 1, timeInMills);
            } catch (InterruptedException e) {
                log.error("An Exception occurred while sorting -> MergeSort ",e);
            }
        };

        Thread mergeSortThread = new Thread(mergeSort);

        mergeSortThread.start();
        mergeSortThread.join();

        currentSortElsMap.remove(sortId);
        return ResponseEntity.ok(nodes);
    }

    @Override
    public ResponseEntity<List<SortEl>> heapSort(List<SortEl> nodes, long timeInMills, String sortId) throws InterruptedException {

        currentSortElsMap.put(sortId, nodes);

        sortService.heapSort(nodes, timeInMills);

        currentSortElsMap.remove(sortId);
        return ResponseEntity.ok(nodes);
    }

    @Override
    public ResponseEntity<List<SortEl>> bubbleSort(List<SortEl> nodes, long timeInMills, String sortId) throws InterruptedException {

        currentSortElsMap.put(sortId, nodes);

        sortService.bubbleSort(nodes, timeInMills);

        currentSortElsMap.remove(sortId);
        return ResponseEntity.ok(nodes);
    }


}
