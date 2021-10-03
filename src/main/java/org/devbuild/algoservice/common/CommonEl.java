package org.devbuild.algoservice.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.devbuild.algoservice.dto.Node;

@Getter
@Setter
@Builder
public class CommonEl extends Node {

    private long x = 0;
    private long y = 0;
    private long cartesianDistance = 0;

    private long weight;

}
