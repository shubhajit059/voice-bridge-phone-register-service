package org.hishab.phone.cmd.api.config;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.hishab.phone.cmd.api.interceptor.TraceIdInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Bean
    public SnapshotTriggerDefinition phoneAggregateSnapshotTriggerDefinition(Snapshotter snapshotter, @Value("${axon.aggregate.agentDto.snapshot-threshold:250}") int threshold) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, threshold);
    }

    @Bean
    public CommandGateway commandGateway(CommandBus commandBus) {
        // Register the TraceIdInterceptor
        commandBus.registerDispatchInterceptor(new TraceIdInterceptor());
        return DefaultCommandGateway.builder()
                .commandBus(commandBus)
                .build();
    }

}
