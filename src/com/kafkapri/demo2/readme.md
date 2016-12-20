【原创】探讨kafka的分区数与多线程消费
http://raising.iteye.com/blog/2252456


一个分区只能被一个线程消费，但一个消费线程可以消费多个分区的数据！虽然我指定了线程池的线程数为6，但并不是所有的线程都去消费了，这当然跟线程池的调度有关系了。并不是一个消费线程对应地去消费一个分区的数据

如果你topicCountMap的值改成1，而 List<KafkaStream<byte[], byte[]>>的size由Integer值决定，此时为1，可以看出，线程池中只能使用一个线程来发送，还是单线程的效果。若要用多线程消费，Integer的值必须大于1.


状况一：往大于实际分区数的分区发数据，比如发送端的第一层循环设为11：
可看到消费端此时虽能正常的完全消费这10个分区的数据，但生产端会报异常：
No partition metadata for topic blog4 due to kafka.common.LeaderNotAvailableException}] for topic [blog4]: class kafka.common.LeaderNotAvailableException 
这说明，你往partition11发送失败，因为卡夫卡已经设置了10个分区，你再往不存在的分区数发当然会报错了。


状况二：发送端用传统的发送方法，即KeyedMessage的构造函数只有topic和Message
你会发现：虽然我生产发送端往9个分区发送了数据，但实际上只消费掉了7个分区的数据。（如果你再跑一边，可能又是6个分区的数据）——这说明，有的分区的数据没有被消费，原因只可能是线程不够。so，当线程池中的大小小于分区数时，会出现有的分区没有被采集的情况。建议设置：实际发送分区数（一般就等于设置的分区数）= topicCountMap的value = 线程池大小  否则极易出现reblance的异常！！！
