package org.devbuild.algoservice.sort;

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
    ResponseEntity<List<SortEl>> getCurrentSortedList(@PathVariable String sortId);

    @PostMapping
    ResponseEntity<List<SortEl>> sort(@RequestBody List<SortEl> nodes) throws InterruptedException;

    @PostMapping("mergeSort/{timeInMills}/{sortId}")
    ResponseEntity<List<SortEl>> mergeSort(@RequestBody List<SortEl> nodes,
                                         @PathVariable
                                         @Min(value = 30, message = "{timeInMills.tooLess}")
                                         @Max(value = 1000, message ="{timeInMills.exceeded}")
                                                 long timeInMills,
                                         @PathVariable String sortId) throws InterruptedException;

    @PostMapping("heapSort/{timeInMills}/{sortId}")
    ResponseEntity<List<SortEl>> heapSort(@RequestBody List<SortEl> nodes,
                                        @PathVariable
                                        @Min(value = 30, message = "{timeInMills.tooLess}")
                                        @Max(value = 1000, message ="{timeInMills.exceeded}")
                                                long timeInMills,
                                        @PathVariable String sortId) throws InterruptedException;

    @PostMapping("bubbleSort/{timeInMills}/{sortId}")
    ResponseEntity<List<SortEl>> bubbleSort(@RequestBody List<SortEl> nodes,
                                          @PathVariable
                                          @Min(value = 30, message = "{timeInMills.tooLess}")
                                          @Max(value = 1000, message ="{timeInMills.exceeded}")
                                                  long timeInMills,
                                          @PathVariable String sortId) throws InterruptedException;

}
