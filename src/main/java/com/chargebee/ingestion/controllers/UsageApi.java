/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.0.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.chargebee.ingestion.controllers;

import com.chargebee.ingestion.models.Error;
import com.chargebee.ingestion.models.UsageRecord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-20T10:37:27.792356+05:30[Asia/Kolkata]")
@Validated
@Tag(name = "Usage", description = "the Usage API")
public interface UsageApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /usage : Ingest a new usage record
     * Submit a new usage record to the system.
     *
     * @param usageRecord  (required)
     * @return Usage record created successfully. (status code 201)
     *         or Invalid input. (status code 400)
     *         or Unauthorized (status code 401)
     *         or Internal server error. (status code 500)
     */
    @Operation(
        operationId = "ingestUsage",
        summary = "Ingest a new usage record",
        tags = { "Usage" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Usage record created successfully.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UsageRecord.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid input.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        },
        security = {
            @SecurityRequirement(name = "FullAccessApiKey")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/usage",
        produces = { "application/json" },
        consumes = { "application/json", "application/xml" }
    )
    default ResponseEntity<UsageRecord> ingestUsage(
        @Parameter(name = "UsageRecord", description = "", required = true) @Valid @RequestBody UsageRecord usageRecord
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"attributes\" : { \"key\" : \"attributes\" }, \"id\" : \"id\", \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
