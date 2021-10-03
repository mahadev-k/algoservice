package org.devbuild.algoservice.common;

import org.devbuild.algoservice.exception.AlgoServiceException;

import java.util.List;
import java.util.Map;

public interface CommonAlgoService {

    int binarySearchToLeft(List<CommonEl> commonEl,
                           Integer maxN,
                           Map<String,CommonEl> binarySearchIndex,
                           long timeInMills,
                           String id) throws InterruptedException, AlgoServiceException;

}
