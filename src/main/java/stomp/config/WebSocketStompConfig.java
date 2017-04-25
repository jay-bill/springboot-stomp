package stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 配置stomp协议支持
 * @author jaybill
 */
@Configuration
@EnableWebSocketMessageBroker//配置WebSocket和Stomp
public class WebSocketStompConfig extends AbstractWebSocketMessageBrokerConfigurer{

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//为"/myStomp"路径开启SockJS功能
		registry.addEndpoint("/myStomp").withSockJS();
	}
	
	//配置简单消息代理
	public void configureMessageBroker(MessageBrokerRegistry registry){
		//消息代理会处理前缀为"/queue"、"/topic"的消息
		registry.enableSimpleBroker("/queue","/topic");
		//应用程序的消息会带有"/appli"前缀
		registry.setApplicationDestinationPrefixes("/appli");
	}
}
