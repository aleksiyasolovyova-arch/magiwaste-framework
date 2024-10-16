package be.kdg.integration3.magiwaste.socketConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.config.annotation.*;

@Configuration
public class ESPSocketClientConfig {

    @Bean
    public WebSocketConnectionManager connectionManager() {
        StandardWebSocketClient client = new StandardWebSocketClient();
        WebSocketConnectionManager manager = new WebSocketConnectionManager(
                client,
                new ESPWebsocketHandler(),
                "ws://192.168.1.31"        // my own localserver, we gonna replace it with the kdg server
        );
        manager.setAutoStartup(true);
        return manager;
    }
}
