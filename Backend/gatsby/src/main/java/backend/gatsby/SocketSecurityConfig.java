package backend.gatsby;

import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

public class SocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer  {
	@Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
          .simpDestMatchers("/websocket/**").authenticated()
          .anyMessage().authenticated();
    }   
}
