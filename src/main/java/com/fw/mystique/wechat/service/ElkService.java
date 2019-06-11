package com.fw.mystique.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ElkService {
	
	public static final String TOPIC = "wechat-server";
	
	@Autowired
	private KafkaTemplate kafkaTemplate;
	
	@Value("${spring.kafka.enable}")
	private boolean kafkaEnable;

	public void sendMessage(String method, String message) {
		
		if(kafkaEnable&&message!=null) {
        	try {
        		ELKRequest request = new ELKRequest(method,message);
            	kafkaTemplate.send(TOPIC,  JSON.toJSONString(request));
            } catch (Exception e) {
            	e.printStackTrace();
            	log.error("Send message to kafka unsuccessfully", e);
            }
        }
	}
	
	@Data
    @AllArgsConstructor
    class ELKRequest {
    	private String method;
    	private String message;
    }
}
