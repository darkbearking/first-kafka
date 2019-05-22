package org.dark;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * 消費者組的概念
 * 對於kafka而言，每條消息topic都會發送到所有的消費者組中去。
 * 若每個消費者組中有多個消費者，那麼每條消息只會被組中的某一個消費者消費。而非被組中所有消費者消費。
 * @author liwei
 *
 */
public class ConsumerMainA {

	public static void main(String[] args) {
		//配置信息
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		//指定不同的消費者組，消息會被廣播到所有的消費者實例
		props.put("group.id", "test");
		//設置數據key的序列化處理類
		props.put("key.deserializer", 
				"org.apache.kafka.common.serialization.StringDeserializer");
		//設置數據value的序列化處理類
		props.put("value.deserializer", 
				"org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		//訂閱 my-topic 的消息
		consumer.subscribe(Arrays.asList("my-topic"));
		
		//到服務器中讀取記錄
		while(true) {
			//從kafka服務器中拉去消息記錄，一次100條
			ConsumerRecords<String, String> records = consumer.poll(100);
			for(ConsumerRecord<String, String> record : records) {
				System.out.println("這是消費者A，key： " + record.key() + "value: " + record.value());
			}
		}
	}

}
