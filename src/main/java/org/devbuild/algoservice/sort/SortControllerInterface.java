package org.devbuild.algoservice.sort;

import org.devbuild.algoservice.dto.Node;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("default/sort")
public interface SortControllerInterface {

    @GetMapping
    ResponseEntity<List<Node>> getCurrentSortedList();

    @PostMapping
    ResponseEntity<List<Node>> sort(@RequestBody List<Node> nodes) throws InterruptedException;

    @PostMapping("mergeSort/{timeInMills}")
    ResponseEntity<List<Node>> mergeSort(@RequestBody List<Node> nodes,
                                         @PathVariable
                                         @Min(value = 0, message = "{timeInMills.lessThanZero}")
                                         @Max(value = 1000, message ="{timeInMills.exceeded}")
                                                 long timeInMills) throws InterruptedException;

    @PostMapping("heapSort/{timeInMills}")
    ResponseEntity<List<Node>> heapSort(@RequestBody List<Node> nodes,
                                        @PathVariable
                                        @Min(value = 0, message = "{timeInMills.lessThanZero}")
                                        @Max(value = 1000, message ="{timeInMills.exceeded}")
                                                long timeInMills) throws InterruptedException;

    @PostMapping("bubbleSort/{timeInMills}")
    ResponseEntity<List<Node>> bubbleSort(@RequestBody List<Node> nodes,
                                          @PathVariable
                                          @Min(value = 0, message = "{timeInMills.lessThanZero}")
                                          @Max(value = 1000, message ="{timeInMills.exceeded}")
                                                  long timeInMills) throws InterruptedException;

}
