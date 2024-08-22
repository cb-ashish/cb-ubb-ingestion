package com.chargebee.ingestion.controllers;

import com.chargebee.ingestion.models.ApplicationInfo;
import com.chargebee.ingestion.service.ApplicationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-22T23:20:51.709090+05:30[Asia/Kolkata]")
@Controller
@RequestMapping("${openapi.usageIngestion.base-path:/v1}")
public class StatusApiController implements StatusApi {

    private final ApplicationInfoService applicationInfoService;
    private final NativeWebRequest request;

    @Autowired
    public StatusApiController(ApplicationInfoService applicationInfoService, NativeWebRequest request) {
        this.applicationInfoService = applicationInfoService;
        this.request = request;
    }

    @Override
    public ResponseEntity<ApplicationInfo> getInfo() {
        ApplicationInfo info = applicationInfoService.getApplicationInfo();
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
