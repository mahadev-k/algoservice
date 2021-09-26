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

    @GetMapping("/{sortId}")
    ResponseEntity<List<Node>> getCurrentSortedList(@PathVariable String sortId);

    @PostMapping
    ResponseEntity<List<Node>> sort(@RequestBody List<Node> nodes) throws InterruptedException;

    @PostMapping("mergeSort/{timeInMills}/{sortId}")
    ResponseEntity<List<Node>> mergeSort(@RequestBody List<Node> nodes,
                                         @PathVariable
                                         @Min(value = 30, message = "{timeInMills.tooLess}")
                                         @Max(value = 1000, message ="{timeInMills.exceeded}")
                                                 long timeInMills,
                                         @PathVariable String sortId) throws InterruptedException;

    @PostMapping("heapSort/{timeInMills}/{sortId}")
    ResponseEntity<List<Node>> heapSort(@RequestBody List<Node> nodes,
                                        @PathVariable
                                        @Min(value = 30, message = "{timeInMills.tooLess}")
                                        @Max(value = 1000, message ="{timeInMills.exceeded}")
                                                long timeInMills,
                                        @PathVariable String sortId) throws InterruptedException;

    @PostMapping("bubbleSort/{timeInMills}/{sortId}")
    ResponseEntity<List<Node>> bubbleSort(@RequestBody List<Node> nodes,
                                          @PathVariable
                                          @Min(value = 30, message = "{timeInMills.tooLess}")
                                          @Max(value = 1000, message ="{timeInMills.exceeded}")
                                                  long timeInMills,
                                          @PathVariable String sortId) throws InterruptedException;

}
