package com.micro_service.regression.toolKit;

import base.SuperClass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class SequentialAndParallelStreams extends SuperClass {
    
    private static final int MAX_NUMBER = 100000;
    
    public static void main(String[] args) {
        sortSequential();
        sortParallel();
    }
    
    public static void sortSequential() {
    
    }
    
    public static void sortParallel() {
    
    }
}
