package com.blue.project.modules.maintenance.dto;

import java.time.ZonedDateTime;

public class SystemStatus {

    private ZonedDateTime dateTime;
    private Long physicalFreeMemory;
    private Long physicalMemorySize;
    private Long vmFreeMemory;
    private Long vmCurrentMemory;
    private Long vmMaxMemory;
    private Double cpuLoad;


    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getPhysicalFreeMemory() {
        return physicalFreeMemory;
    }

    public void setPhysicalFreeMemory(Long physicalFreeMemory) {
        this.physicalFreeMemory = physicalFreeMemory;
    }

    public Long getPhysicalMemorySize() {
        return physicalMemorySize;
    }

    public void setPhysicalMemorySize(Long physicalMemorySize) {
        this.physicalMemorySize = physicalMemorySize;
    }

    public Long getVmFreeMemory() {
        return vmFreeMemory;
    }

    public void setVmFreeMemory(Long vmFreeMemory) {
        this.vmFreeMemory = vmFreeMemory;
    }

    public Long getVmCurrentMemory() {
        return vmCurrentMemory;
    }

    public void setVmCurrentMemory(Long vmCurrentMemory) {
        this.vmCurrentMemory = vmCurrentMemory;
    }

    public Long getVmMaxMemory() {
        return vmMaxMemory;
    }

    public void setVmMaxMemory(Long vmMaxMemory) {
        this.vmMaxMemory = vmMaxMemory;
    }

    public Double getCpuLoad() {
        return cpuLoad;
    }

    public void setCpuLoad(Double cpuLoad) {
        this.cpuLoad = cpuLoad;
    }
}
