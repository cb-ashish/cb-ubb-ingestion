package com.chargebee.ingestion.controllers;


import com.chargebee.ingestion.models.UsageRecord;
import com.chargebee.ingestion.service.UsageRecordProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.io.IOException;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-20T10:37:27.792356+05:30[Asia/Kolkata]")
@Controller
@RequestMapping("${openapi.usageIngestion.base-path:/v1}")
public class UsageApiController implements UsageApi {

    private static final Logger logger = LoggerFactory.getLogger(UsageApiController.class);

    private final NativeWebRequest request;

    @Autowired
    private UsageRecordProducer usageRecordProducer;


    @Autowired
    public UsageApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<UsageRecord> ingestUsage(UsageRecord usageRecord) throws IOException {
        logger.info("Ingesting usage record with {} attributes", usageRecord.getAttributes().size());
        logger.debug("Attributes: {}", usageRecord.getAttributes());
        usageRecordProducer.sendUsageRecord(usageRecord);
        return new ResponseEntity<>(usageRecord, HttpStatus.CREATED);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
