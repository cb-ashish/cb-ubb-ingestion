package com.chargebee.ingestion.controllers;

import com.chargebee.ingestion.models.GetInfo200Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-22T10:52:22.595673+05:30[Asia/Kolkata]")
@Controller
@RequestMapping("${openapi.usageIngestion.base-path:/v1}")
public class DefaultApiController implements DefaultApi {

    private final NativeWebRequest request;

    @Autowired
    public DefaultApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<GetInfo200Response> getInfo() {
        return new ResponseEntity<>(new GetInfo200Response().status("200").description("UP"), HttpStatus.OK);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
