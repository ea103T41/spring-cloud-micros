package com.euphy.learn.line;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceRegisteredEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class LineNotifier extends AbstractEventNotifier {

    private static final String DEFAULT_MESSAGE = "#{registration.name} (#{id}) is #{statusInfo.status}";

    private final LineProperties lineProperties;
    private final Expression message;
    private final RestTemplate restTemplate;

    @Autowired
    public LineNotifier(InstanceRepository repository, LineProperties lineProperties, RestTemplate restTemplate) {
        super(repository);
        this.lineProperties = lineProperties;
        this.restTemplate = restTemplate;
        this.message = new SpelExpressionParser().parseExpression(DEFAULT_MESSAGE, ParserContext.TEMPLATE_EXPRESSION);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        if (!lineProperties.isEnabled()) {
            log.info("LINE notifier is disabled.");
            return Mono.empty();
        }

        return Mono.fromRunnable(() -> {
            log.info("LINE notifier is running...");

            if (event instanceof InstanceRegisteredEvent) {
                log.info("Instance registered: {}", instance.getRegistration().getName());

                String msg = String.format(
                        "Service '%s' with ID '%s' has been registered.",
                        instance.getRegistration().getName(),
                        instance.getId()
                );

                sendLineNotification(msg);
            }
            if (event instanceof InstanceStatusChangedEvent) {
                String msg = message.getValue(instance, String.class);
                log.info("Sending LINE notification: {}", msg);

                sendLineNotification(msg);
            }
        });
    }

    private void sendLineNotification(String msg) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", String.format("Bearer %s", lineProperties.getChannelToken()));

        HttpEntity<Map<String, Object>> entity = createHttpEntity(msg, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "https://api.line.me/v2/bot/message/push",
                    HttpMethod.POST, entity, String.class
            );
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("LINE notification sent successfully: {}", response.getBody());
            } else {
                log.warn("LINE notification failed: {}", response.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Failed to send LINE notification", e);
        }
    }

    private HttpEntity<Map<String, Object>> createHttpEntity(String msg, HttpHeaders headers) {
        Map<String, Object> body = new HashMap<>();
        Map<String, String> message = new HashMap<>();
        message.put("type", "text");
        message.put("text", msg);

        body.put("to", lineProperties.getTo());
        body.put("messages", List.of(message));

        return new HttpEntity<>(body, headers);
    }
}
