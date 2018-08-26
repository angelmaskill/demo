package com.kafkapri.demo1;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;


public class CustomerPartitioner implements Partitioner {
	public CustomerPartitioner(VerifiableProperties props) {
	}

	public int partition(Object key, int numPartitions) {
		int partition = 0;
		String k = (String) key;
		partition = Math.abs(k.hashCode()) % numPartitions;
		return partition;
	}
}
