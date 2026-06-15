package org.example.springbatch.partition;

import org.springframework.batch.core.partition.Partitioner;
import org.springframework.batch.infrastructure.item.ExecutionContext;

import java.util.Map;

public class CustomPartitioner implements Partitioner {

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        return Map.of();
    }
}
