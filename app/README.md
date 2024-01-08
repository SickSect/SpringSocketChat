## MAIN INFO

WebSockets — это двунаправленное полнодуплексное постоянное соединение между веб-браузером и сервером. После установки соединения WebSocket оно остается открытым до тех пор, пока клиент или сервер не решит закрыть это соединение.

WebSocket — протокол низкого уровня, который не определяет форматы сообщений. Поэтому WebSocket RFC определяет подпротоколы, описывающие структуру и стандарты сообщений

STOMP похож на HTTP и работает поверх TCP, используя следующие команды:
```shell
CONNECT
SUBSCRIBE
UNSUBSCRIBE
SEND
BEGIN
COMMIT
ACK
```


Cоздаем класс WebSocketConfig с аннотациями @Configuration и @EnableWebSocketMessageBroker.

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker( "/user");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);
        messageConverters.add(converter);
        return false;
    }
}

Первый метод конфигурирует простой брокер сообщений в памяти с одним адресом с префиксом /user для отправки и получения сообщений. Адреса с префиксом /app предназначены для сообщений, обрабатываемых методами с аннотацией @MessageMapping, которые мы обсудим в следующем разделе.

Второй метод регистрирует конечную точку STOMP /ws. Эта конечная точка будет использоваться клиентами для подключения к STOMP-серверу. Здесь также включается резервный SockJS, который будет использоваться, если WebSocket будет недоступен.

Последний метод настраивает конвертер JSON, который используется Spring'ом для преобразования сообщений из/в JSON.

## SOURCES

https://www.baeldung.com/websockets-spring

https://www.youtube.com/watch?v=7T-HnTE6v64&t=1282s

https://www.baeldung.com/spring-security-websockets

https://habr.com/ru/companies/otus/articles/516702/