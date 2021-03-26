package com.kafkapri.demo1;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Date Nov 3, 2016
 * @Author dengjie
 * @Note 按照先 Hash 再取模的规则，进行分区入库
 */
public class PartitionerProducer {
    public static void main(String[] args) {
        producerData();
    }

    private static void producerData() {
        Properties props = new Properties();
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "192.168.240.106:9092");
        props.put("partitioner.class", "com.kafkapri.CustomerPartitioner");
        Producer<String, String> producer = new Producer<String, String>(new ProducerConfig(props));
        String topic = "ke_test";
        List<String> list = new ArrayList();
        for (int i = 0; i < 200; i++) {
            list.add(i + "");
        }
        for (int i = 0; i < list.size(); i++) {
            String k = "key" + i;
            String v = new String(list.get(i));
            producer.send(new KeyedMessage<String, String>(topic, k, v));
            if (i == (list.size() - 1)) {
                return;
            }
        }
        producer.close();
    }
}