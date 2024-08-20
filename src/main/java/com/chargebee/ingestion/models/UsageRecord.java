package com.chargebee.ingestion.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * UsageRecord
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-20T10:37:27.792356+05:30[Asia/Kolkata]")
public class UsageRecord {

  @JsonProperty("id")
  private String id;

  @JsonProperty("timestamp")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime timestamp;

  @JsonProperty("attributes")
  @Valid
  private Map<String, String> attributes = new HashMap<>();

  public UsageRecord id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique idempotency key for the usage record.
   * @return id
  */
  
  @Schema(name = "id", description = "Unique idempotency key for the usage record.", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public UsageRecord timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Indicates the usage record timestamp.
   * @return timestamp
  */
  @NotNull @Valid 
  @Schema(name = "timestamp", description = "Indicates the usage record timestamp.", required = true)
  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public UsageRecord attributes(Map<String, String> attributes) {
    this.attributes = attributes;
    return this;
  }

  public UsageRecord putAttributesItem(String key, String attributesItem) {
    this.attributes.put(key, attributesItem);
    return this;
  }

  /**
   * Get attributes
   * @return attributes
  */
  @NotNull 
  @Schema(name = "attributes", required = true)
  public Map<String, String> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, String> attributes) {
    this.attributes = attributes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UsageRecord usageRecord = (UsageRecord) o;
    return Objects.equals(this.id, usageRecord.id) &&
        Objects.equals(this.timestamp, usageRecord.timestamp) &&
        Objects.equals(this.attributes, usageRecord.attributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, timestamp, attributes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsageRecord {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

