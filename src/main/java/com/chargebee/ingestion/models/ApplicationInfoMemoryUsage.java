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
 * Current memory usage of the application.
 */

@Schema(name = "ApplicationInfo_memoryUsage", description = "Current memory usage of the application.")
@JsonTypeName("ApplicationInfo_memoryUsage")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-22T23:18:14.984849+05:30[Asia/Kolkata]")
public class ApplicationInfoMemoryUsage {

  @JsonProperty("total")
  private Integer total;

  @JsonProperty("used")
  private Integer used;

  @JsonProperty("free")
  private Integer free;

  public ApplicationInfoMemoryUsage total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * Total memory allocated.
   * @return total
  */
  
  @Schema(name = "total", description = "Total memory allocated.", required = false)
  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public ApplicationInfoMemoryUsage used(Integer used) {
    this.used = used;
    return this;
  }

  /**
   * Memory currently in use.
   * @return used
  */
  
  @Schema(name = "used", description = "Memory currently in use.", required = false)
  public Integer getUsed() {
    return used;
  }

  public void setUsed(Integer used) {
    this.used = used;
  }

  public ApplicationInfoMemoryUsage free(Integer free) {
    this.free = free;
    return this;
  }

  /**
   * Free memory available.
   * @return free
  */
  
  @Schema(name = "free", description = "Free memory available.", required = false)
  public Integer getFree() {
    return free;
  }

  public void setFree(Integer free) {
    this.free = free;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApplicationInfoMemoryUsage applicationInfoMemoryUsage = (ApplicationInfoMemoryUsage) o;
    return Objects.equals(this.total, applicationInfoMemoryUsage.total) &&
        Objects.equals(this.used, applicationInfoMemoryUsage.used) &&
        Objects.equals(this.free, applicationInfoMemoryUsage.free);
  }

  @Override
  public int hashCode() {
    return Objects.hash(total, used, free);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApplicationInfoMemoryUsage {\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    used: ").append(toIndentedString(used)).append("\n");
    sb.append("    free: ").append(toIndentedString(free)).append("\n");
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

