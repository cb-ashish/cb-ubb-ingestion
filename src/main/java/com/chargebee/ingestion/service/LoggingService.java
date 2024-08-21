package com.chargebee.ingestion.service;

import com.chargebee.ingestion.logging.CloudWatchLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {

    private final boolean isCloudWatchEnabled;
    private final String logGroupName;
    private final String logStreamName;
    private final CloudWatchLogger cloudWatchLogger;

    @Autowired
    public LoggingService(
            @Value("${logging.cloudwatch.enabled}") boolean isCloudWatchEnabled,
            @Value("${logging.cloudwatch.log-group}") String logGroupName,
            @Value("${logging.cloudwatch.log-stream}") String logStreamName) {
        this.isCloudWatchEnabled = isCloudWatchEnabled;
        this.logGroupName = logGroupName;
        this.logStreamName = logStreamName;
        this.cloudWatchLogger = new CloudWatchLogger(logGroupName, logStreamName);
    }

    public void log(String message) {
        if (isCloudWatchEnabled) {
            this.cloudWatchLogger.log(message);
        } else {
            System.out.println(message);
        }
    }
}
