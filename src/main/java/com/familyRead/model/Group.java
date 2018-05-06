package com.familyRead.model;

public class Group {
    private Long id;

    private String groupName;

    private String createTime;

    private String careteBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getCareteBy() {
        return careteBy;
    }

    public void setCareteBy(String careteBy) {
        this.careteBy = careteBy == null ? null : careteBy.trim();
    }
}