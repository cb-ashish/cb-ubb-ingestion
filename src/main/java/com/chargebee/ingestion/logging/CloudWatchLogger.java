package com.chargebee.ingestion.logging;

import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.cloudwatchlogs.model.*;

import java.util.Collections;

public class CloudWatchLogger {
    private final CloudWatchLogsClient client;
    private final String logGroupName;
    private final String logStreamName;

    public CloudWatchLogger(String logGroupName, String logStreamName) {
        this.client = CloudWatchLogsClient.create();
        this.logGroupName = logGroupName;
        this.logStreamName = logStreamName;
        createLogGroupAndStream();
    }

    private void createLogGroupAndStream() {
        try {
            client.createLogGroup(CreateLogGroupRequest.builder().logGroupName(logGroupName).build());
        } catch (Exception e) {
            // Log group already exists
        }

        try {
            client.createLogStream(CreateLogStreamRequest.builder().logGroupName(logGroupName).logStreamName(logStreamName).build());
        } catch (ResourceAlreadyExistsException e) {
            // Log stream already exists
        }
    }

    public void log(String message) {
        InputLogEvent logEvent = InputLogEvent.builder()
                .message(message)
                .timestamp(System.currentTimeMillis())
                .build();

        PutLogEventsRequest request = PutLogEventsRequest.builder()
                .logGroupName(logGroupName)
                .logStreamName(logStreamName)
                .logEvents(Collections.singletonList(logEvent))
                .build();

        client.putLogEvents(request);
    }
}
