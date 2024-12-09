package org.hishab.phone.cmd.api.interceptor;

import org.axonframework.messaging.Message;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

public class TraceIdInterceptor implements MessageDispatchInterceptor<Message<?>>  {
    @NotNull
    @Override
    public BiFunction<Integer, Message<?>, Message<?>> handle(@NotNull List<? extends Message<?>> messages) {
        return (index, message) -> {
            // Add traceId if not already present
            if (!message.getMetaData().containsKey("traceId")) {
                String traceId = UUID.randomUUID().toString();
                return message.andMetaData(Collections.singletonMap("traceId", traceId));
            }
            return message;
        };
    }
}
