package com.chargebee.ingestion.service;

import com.chargebee.ingestion.models.ApplicationInfo;
import com.chargebee.ingestion.models.ApplicationInfoDiskSpace;
import com.chargebee.ingestion.models.ApplicationInfoMemoryUsage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Properties;

@Service
public class ApplicationInfoService {

    @Value("${application.version}")
    private String version;

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${application.name}")
    private String applicationName;


    public ApplicationInfo getApplicationInfo() {
        Properties properties = System.getProperties();
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();

        OffsetDateTime buildDate = OffsetDateTime.now(ZoneId.of("UTC")); // Example, adjust as needed
        String uptime = formatUptime(ManagementFactory.getRuntimeMXBean().getUptime());
        ApplicationInfoMemoryUsage memoryUsage = getMemoryUsage();
        float cpuUsage = getCpuUsage(osBean);
        int threadCount = threadBean.getThreadCount();
        ApplicationInfoDiskSpace diskSpace = getDiskSpace();
        String commitHash = properties.getProperty("application.commitHash", "unknown");
        String releaseNotes = properties.getProperty("application.releaseNotes", "No release notes available");

        return new ApplicationInfo()
                .version(version)
                .buildDate(buildDate)
                .environment(profile)
                .uptime(uptime)
                .memoryUsage(memoryUsage)
                .cpuUsage(cpuUsage)
                .threadCount(threadCount)
                .diskSpace(diskSpace)
                .serviceName(applicationName)
                .commitHash(commitHash)
                .releaseNotes(releaseNotes);
    }

    private String formatUptime(long uptimeMillis) {
        long seconds = uptimeMillis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        return String.format("%d days, %d hours, %d minutes", days, hours % 24, minutes % 60);
    }

    private ApplicationInfoMemoryUsage getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        return new ApplicationInfoMemoryUsage()
                .total((int) runtime.totalMemory())
                .used((int) (runtime.totalMemory() - runtime.freeMemory()))
                .free((int) runtime.freeMemory());
    }

    private float getCpuUsage(OperatingSystemMXBean osBean) {
        // This is a simplified example. In a real scenario, consider using a more precise method.
        return (float) osBean.getSystemLoadAverage();
    }

    private ApplicationInfoDiskSpace getDiskSpace() {
        // Example disk space usage. Adjust with actual values.
        File file = new File("/");
        long total = file.getTotalSpace();
        long free = file.getFreeSpace();
        long used = total - free;
        return new ApplicationInfoDiskSpace()
                .total((int) total)
                .used((int) used)
                .free((int) free);
    }
}
