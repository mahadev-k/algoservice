package org.devbuild.algoservice.common;

import org.devbuild.algoservice.exception.AlgoServiceException;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonAlgoServiceImpl implements CommonAlgoService{

    static final String ELEMENT_NOT_FOUND = "element.not.found";



    @Override
    public int binarySearchToLeft(List<CommonEl> commonEls,
                                  Integer maxN,
                                  Map<String,CommonEl> binarySearchIndex,
                                  long timeInMills,
                                  String id) throws InterruptedException, AlgoServiceException {

        int start = 0;
        int end = commonEls.size()-1;

        while(start<end-1){

            int mid = (start+end)/2;

            if(commonEls.get(mid).getWeight() >= maxN){
                end = mid;
            }else{
                start = mid;
            }

            binarySearchIndex.put(id, commonEls.get(mid));

            Thread.sleep(timeInMills);
        }

        if(commonEls.get(start).getWeight() < maxN){
            if(commonEls.get(end).getWeight() < maxN){
                throw new AlgoServiceException(ELEMENT_NOT_FOUND);
            }
            return end;
        }else{
            return start;
        }

    }
}
