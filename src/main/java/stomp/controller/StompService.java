package stomp.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

/**
 * 任意地方都可以回复消息到指定主题
 * @author jaybill
 *
 */
@Service
public class StompService {

	//简单的文本回应类，使用该对象可以在任意地方回复消息到某个主题
	@Autowired
	private SimpMessageSendingOperations messaging;
	
	public void sendToTopic(){
		messaging.convertAndSend("/topic/feedback","你好，我是服务端，可以在任意地方发送消息。");
	}
}
