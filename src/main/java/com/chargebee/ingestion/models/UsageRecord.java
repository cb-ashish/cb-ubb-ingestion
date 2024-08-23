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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-23T22:24:33.752125+05:30[Asia/Kolkata]")
public class UsageRecord {

  @JsonProperty("id")
  private String id;

  @JsonProperty("usage_timestamp")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime usageTimestamp;

  @JsonProperty("attributes")
  @Valid
  private Map<String, String> attributes = new HashMap<>();

  @JsonProperty("subscription_id")
  private String subscriptionId;

  @JsonProperty("site_id")
  private String siteId;

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

  public UsageRecord usageTimestamp(OffsetDateTime usageTimestamp) {
    this.usageTimestamp = usageTimestamp;
    return this;
  }

  /**
   * Indicates the usage record timestamp.
   * @return usageTimestamp
  */
  @NotNull @Valid 
  @Schema(name = "usage_timestamp", description = "Indicates the usage record timestamp.", required = true)
  public OffsetDateTime getUsageTimestamp() {
    return usageTimestamp;
  }

  public void setUsageTimestamp(OffsetDateTime usageTimestamp) {
    this.usageTimestamp = usageTimestamp;
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

  public UsageRecord subscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
    return this;
  }

  /**
   * Get subscriptionId
   * @return subscriptionId
  */
  
  @Schema(name = "subscription_id", required = false)
  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public UsageRecord siteId(String siteId) {
    this.siteId = siteId;
    return this;
  }

  /**
   * Get siteId
   * @return siteId
  */
  
  @Schema(name = "site_id", required = false)
  public String getSiteId() {
    return siteId;
  }

  public void setSiteId(String siteId) {
    this.siteId = siteId;
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
        Objects.equals(this.usageTimestamp, usageRecord.usageTimestamp) &&
        Objects.equals(this.attributes, usageRecord.attributes) &&
        Objects.equals(this.subscriptionId, usageRecord.subscriptionId) &&
        Objects.equals(this.siteId, usageRecord.siteId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, usageTimestamp, attributes, subscriptionId, siteId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsageRecord {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    usageTimestamp: ").append(toIndentedString(usageTimestamp)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    siteId: ").append(toIndentedString(siteId)).append("\n");
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

