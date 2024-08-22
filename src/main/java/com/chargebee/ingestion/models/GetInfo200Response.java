package com.chargebee.ingestion.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * GetInfo200Response
 */

@JsonTypeName("get_info_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-22T10:52:22.595673+05:30[Asia/Kolkata]")
public class GetInfo200Response {

  @JsonProperty("status")
  private String status;

  @JsonProperty("description")
  private String description;

  public GetInfo200Response status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  
  @Schema(name = "status", required = false)
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public GetInfo200Response description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  
  @Schema(name = "description", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetInfo200Response getInfo200Response = (GetInfo200Response) o;
    return Objects.equals(this.status, getInfo200Response.status) &&
        Objects.equals(this.description, getInfo200Response.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetInfo200Response {\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

