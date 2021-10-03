package org.devbuild.algoservice.common;

import org.devbuild.algoservice.exception.AlgoServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RequestMapping("default/common")
@RestController
@CrossOrigin
public interface CommonAlgoControllerInterface {

    @GetMapping
    ResponseEntity<String> getDefaultResponse();

    @PostMapping("binarySearchToLeft/{id}/{maxN}/{timeInMills}")
    ResponseEntity<CommonEl> binarySearchToLeft(@RequestBody List<CommonEl> commonEl,
                                                @PathVariable String id,
                                                @PathVariable int maxN,
                                                @Min(value = 10, message = "{timeInMills.tooLess}")
                                                @Max(value = 50, message = "{timeInMills.exceeded}")
                                                @PathVariable long timeInMills) throws InterruptedException, AlgoServiceException;

    @GetMapping("/binarySearch/{id}")
    ResponseEntity<CommonEl> getCurrentBinarySearchIndex(@PathVariable  String id);
}
