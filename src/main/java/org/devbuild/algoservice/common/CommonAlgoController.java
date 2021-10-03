package org.devbuild.algoservice.common;

import lombok.extern.slf4j.Slf4j;
import org.devbuild.algoservice.exception.AlgoServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonAlgoController implements CommonAlgoControllerInterface {


    @Autowired
    CommonAlgoService commonAlgoService;

    Map<String, CommonEl> binarySearchIndex = new HashMap<>();

    @Override
    public ResponseEntity<String> getDefaultResponse() {
        return ResponseEntity.ok("Hey guys welcome to the common algorithms API, Have Fun!!!");
    }

    @Override
    public ResponseEntity<CommonEl> binarySearchToLeft(List<CommonEl> commonEls,
                                                       String id,
                                                       int maxN,
                                                       long timeInMills) throws InterruptedException, AlgoServiceException {

        Integer mid = 0;

        binarySearchIndex.put(id, commonEls.get(mid));

        int index = commonAlgoService.binarySearchToLeft(commonEls, maxN, binarySearchIndex, timeInMills, id);

        return ResponseEntity.ok(commonEls.get(index));
    }

    @Override
    public ResponseEntity<CommonEl> getCurrentBinarySearchIndex(String id){
        return ResponseEntity.ok(binarySearchIndex.get(id));
    }
}
