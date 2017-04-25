package stomp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import stomp.entity.Shout;

/**
 * 入门第一个Controller：只能为目标客户端发送数据，能不能为指定用户发送数据
 * @author jaybill
 *
 */
@Controller
@MessageMapping("/first")
public class StompController {
	
	private static final Logger logger = LoggerFactory.getLogger(StompController.class);
	
	@Autowired
	private StompService service;
	
	/**
	 * 处理发往"/app/stompHS"目的地的消息，但不回传消息
	 * @param incoming
	 * @return
	 */
	@MessageMapping("/stompHS")
	public void handleShout(Shout incoming){
		logger.info("收到的消息："+incoming.getMessage());
		System.out.println("收到的消息："+incoming.getMessage());
		
	}
	
	/**
	 * 接收并回应消息
	 * @param incoming
	 * @return
	 */
	@MessageMapping("/responseSTMP")
	//如果没有下面这个注解，回应给客户端的消息存放到/topic/responseSTMP中
	//否则，消息存放到/topic/shout中
	//路径前缀必须和配置的一致，否则无法访问
	@SendTo("/topic/shout")
	public Shout responseShout(Shout incoming){
		logger.info("收到了消息："+incoming.getMessage());
		Shout outgoing = new Shout();
		outgoing.setMessage("你好，我是服务端！");
		return outgoing;
	}
	
	/**
	 * 处理订阅：浏览器请求为/appli/handleSubscribe
	 * 这个注解无法接收任何参数，否则抛出异常；
	 * 这也和他的名字含义对应，订阅，所谓的订阅，就是浏览器前来获取。
	 * @return
	 */
	@SubscribeMapping("/handleSubscribe")
	public Shout handleSubscribe(){
		Shout outgoing = new Shout();
		outgoing.setMessage("你好，我是服务端！");
		return outgoing;
	}
	
	/**
	 * 任意地方都可以发送消息，回复给客户端
	 */
	@RequestMapping("/sendMsgAnyWhere")
	@ResponseBody
	public void sendMsgAnyWhere(){
		service.sendToTopic();
	}
}
