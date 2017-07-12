package com.chattio.config.websocket;

import com.chattio.constants.ConfigConstants;
import com.chattio.util.WebSocketUserRetriever;
import com.chattio.util.helper.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.socket.config.annotation.*;

import java.security.Principal;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages = {"com.chattio.service", "com.chattio.util", "com.chattio.config"})
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/chat")
                .setAllowedOrigins(ConfigConstants.CORS_ORIGIN)
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(new ChannelInterceptorAdapter() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String userEmail = WebSocketUserRetriever.parseUserEmailFromHeaders(message.getHeaders());
                    Principal principal = authenticationHelper.setApplicationAuthentication(userEmail);
                    accessor.setUser(principal);
                }
                return message;
            }
        });
    }
}
