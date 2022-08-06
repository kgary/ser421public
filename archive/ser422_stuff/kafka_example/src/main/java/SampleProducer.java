package main.java;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SampleProducer {
    private static String __id    = "test3";
    
    public SampleProducer(ProducerRecord producerRec){
        Properties properties = new Properties();

        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer kafkaProducer = new KafkaProducer(properties);

        kafkaProducer.send(producerRec);
        kafkaProducer.close();
    }

    public static void main(String[] args) {
	if (args.length != 3) {
	    System.out.println("USAGE: java main.java.SampleProducer name age location");
	    System.exit(-1);
	}
	ProducerRecord producerRecord =
	    new ProducerRecord(__id, "name", "{name: \"" + args[0] + "\", age:\"" + args[1] + ",\" city: \"" + args[2] + "\"};");
        SampleProducer sampleProducer = new SampleProducer(producerRecord);
    }    
}
