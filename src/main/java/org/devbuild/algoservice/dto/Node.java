package org.devbuild.algoservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node implements Comparable<Node> {

    int data;
    String color;
    int index;
    String borderColor;

    @Override
    public int compareTo(Node o) {
        return this.data - o.data;
    }

    public boolean lessThan(Node node){
        return this.data<node.data;
    }
}
