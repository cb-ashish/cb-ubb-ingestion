package com.chargebee.ingestion.models;

import java.net.URI;
import java.util.Objects;
import com.chargebee.ingestion.models.ApplicationInfoDiskSpace;
import com.chargebee.ingestion.models.ApplicationInfoMemoryUsage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ApplicationInfo
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-22T23:18:14.984849+05:30[Asia/Kolkata]")
public class ApplicationInfo {

  @JsonProperty("version")
  private String version;

  @JsonProperty("buildDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime buildDate;

  @JsonProperty("environment")
  private String environment;

  @JsonProperty("uptime")
  private String uptime;

  @JsonProperty("memoryUsage")
  private ApplicationInfoMemoryUsage memoryUsage;

  @JsonProperty("cpuUsage")
  private Float cpuUsage;

  @JsonProperty("threadCount")
  private Integer threadCount;

  @JsonProperty("diskSpace")
  private ApplicationInfoDiskSpace diskSpace;

  @JsonProperty("serviceName")
  private String serviceName;

  @JsonProperty("commitHash")
  private String commitHash;

  @JsonProperty("releaseNotes")
  private String releaseNotes;

  public ApplicationInfo version(String version) {
    this.version = version;
    return this;
  }

  /**
   * The version of the application.
   * @return version
  */
  @NotNull 
  @Schema(name = "version", description = "The version of the application.", required = true)
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public ApplicationInfo buildDate(OffsetDateTime buildDate) {
    this.buildDate = buildDate;
    return this;
  }

  /**
   * The build date of the application.
   * @return buildDate
  */
  @NotNull @Valid 
  @Schema(name = "buildDate", description = "The build date of the application.", required = true)
  public OffsetDateTime getBuildDate() {
    return buildDate;
  }

  public void setBuildDate(OffsetDateTime buildDate) {
    this.buildDate = buildDate;
  }

  public ApplicationInfo environment(String environment) {
    this.environment = environment;
    return this;
  }

  /**
   * The environment where the application is running (e.g., production, staging).
   * @return environment
  */
  @NotNull 
  @Schema(name = "environment", description = "The environment where the application is running (e.g., production, staging).", required = true)
  public String getEnvironment() {
    return environment;
  }

  public void setEnvironment(String environment) {
    this.environment = environment;
  }

  public ApplicationInfo uptime(String uptime) {
    this.uptime = uptime;
    return this;
  }

  /**
   * The uptime of the application.
   * @return uptime
  */
  @NotNull 
  @Schema(name = "uptime", description = "The uptime of the application.", required = true)
  public String getUptime() {
    return uptime;
  }

  public void setUptime(String uptime) {
    this.uptime = uptime;
  }

  public ApplicationInfo memoryUsage(ApplicationInfoMemoryUsage memoryUsage) {
    this.memoryUsage = memoryUsage;
    return this;
  }

  /**
   * Get memoryUsage
   * @return memoryUsage
  */
  @Valid 
  @Schema(name = "memoryUsage", required = false)
  public ApplicationInfoMemoryUsage getMemoryUsage() {
    return memoryUsage;
  }

  public void setMemoryUsage(ApplicationInfoMemoryUsage memoryUsage) {
    this.memoryUsage = memoryUsage;
  }

  public ApplicationInfo cpuUsage(Float cpuUsage) {
    this.cpuUsage = cpuUsage;
    return this;
  }

  /**
   * Current CPU usage percentage.
   * @return cpuUsage
  */
  
  @Schema(name = "cpuUsage", description = "Current CPU usage percentage.", required = false)
  public Float getCpuUsage() {
    return cpuUsage;
  }

  public void setCpuUsage(Float cpuUsage) {
    this.cpuUsage = cpuUsage;
  }

  public ApplicationInfo threadCount(Integer threadCount) {
    this.threadCount = threadCount;
    return this;
  }

  /**
   * Number of active threads.
   * @return threadCount
  */
  
  @Schema(name = "threadCount", description = "Number of active threads.", required = false)
  public Integer getThreadCount() {
    return threadCount;
  }

  public void setThreadCount(Integer threadCount) {
    this.threadCount = threadCount;
  }

  public ApplicationInfo diskSpace(ApplicationInfoDiskSpace diskSpace) {
    this.diskSpace = diskSpace;
    return this;
  }

  /**
   * Get diskSpace
   * @return diskSpace
  */
  @Valid 
  @Schema(name = "diskSpace", required = false)
  public ApplicationInfoDiskSpace getDiskSpace() {
    return diskSpace;
  }

  public void setDiskSpace(ApplicationInfoDiskSpace diskSpace) {
    this.diskSpace = diskSpace;
  }

  public ApplicationInfo serviceName(String serviceName) {
    this.serviceName = serviceName;
    return this;
  }

  /**
   * Name of the service.
   * @return serviceName
  */
  
  @Schema(name = "serviceName", description = "Name of the service.", required = false)
  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public ApplicationInfo commitHash(String commitHash) {
    this.commitHash = commitHash;
    return this;
  }

  /**
   * Git commit hash for the current build.
   * @return commitHash
  */
  
  @Schema(name = "commitHash", description = "Git commit hash for the current build.", required = false)
  public String getCommitHash() {
    return commitHash;
  }

  public void setCommitHash(String commitHash) {
    this.commitHash = commitHash;
  }

  public ApplicationInfo releaseNotes(String releaseNotes) {
    this.releaseNotes = releaseNotes;
    return this;
  }

  /**
   * Description of the latest changes or updates.
   * @return releaseNotes
  */
  
  @Schema(name = "releaseNotes", description = "Description of the latest changes or updates.", required = false)
  public String getReleaseNotes() {
    return releaseNotes;
  }

  public void setReleaseNotes(String releaseNotes) {
    this.releaseNotes = releaseNotes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApplicationInfo applicationInfo = (ApplicationInfo) o;
    return Objects.equals(this.version, applicationInfo.version) &&
        Objects.equals(this.buildDate, applicationInfo.buildDate) &&
        Objects.equals(this.environment, applicationInfo.environment) &&
        Objects.equals(this.uptime, applicationInfo.uptime) &&
        Objects.equals(this.memoryUsage, applicationInfo.memoryUsage) &&
        Objects.equals(this.cpuUsage, applicationInfo.cpuUsage) &&
        Objects.equals(this.threadCount, applicationInfo.threadCount) &&
        Objects.equals(this.diskSpace, applicationInfo.diskSpace) &&
        Objects.equals(this.serviceName, applicationInfo.serviceName) &&
        Objects.equals(this.commitHash, applicationInfo.commitHash) &&
        Objects.equals(this.releaseNotes, applicationInfo.releaseNotes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(version, buildDate, environment, uptime, memoryUsage, cpuUsage, threadCount, diskSpace, serviceName, commitHash, releaseNotes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApplicationInfo {\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    buildDate: ").append(toIndentedString(buildDate)).append("\n");
    sb.append("    environment: ").append(toIndentedString(environment)).append("\n");
    sb.append("    uptime: ").append(toIndentedString(uptime)).append("\n");
    sb.append("    memoryUsage: ").append(toIndentedString(memoryUsage)).append("\n");
    sb.append("    cpuUsage: ").append(toIndentedString(cpuUsage)).append("\n");
    sb.append("    threadCount: ").append(toIndentedString(threadCount)).append("\n");
    sb.append("    diskSpace: ").append(toIndentedString(diskSpace)).append("\n");
    sb.append("    serviceName: ").append(toIndentedString(serviceName)).append("\n");
    sb.append("    commitHash: ").append(toIndentedString(commitHash)).append("\n");
    sb.append("    releaseNotes: ").append(toIndentedString(releaseNotes)).append("\n");
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

