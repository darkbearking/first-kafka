package org.dark;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerMain {

	public static void main(String[] args) {
		//配置信息
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		//設置數據key的序列化處理類
		props.put("key.serializer", 
				"org.apache.kafka.common.serialization.StringSerializer");
		//設置數據value的序列化處理類
		props.put("value.serializer", 
				"org.apache.kafka.common.serialization.StringSerializer");
		//創建生產者實例
		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		//創建一條新的記錄，第一個參數為topic名稱
		//topic有點類似rabbitmq中的隊列的概念。
		ProducerRecord record = new ProducerRecord<String ,String >("my-topic", "userName", "dark");
		//發送記錄
		producer.send(record);
		producer.close();
	}
}
