package com.familyRead.model;

import java.util.Date;

public class PictureBook {
    private Long id;

    private Long fileImgId;

    private Long filePdfId;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private String bookName;

    private String groupId;

    private Long fileMp3Id;

    private String remark;

    private String groupName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFileImgId() {
        return fileImgId;
    }

    public void setFileImgId(Long fileImgId) {
        this.fileImgId = fileImgId;
    }

    public Long getFilePdfId() {
        return filePdfId;
    }

    public void setFilePdfId(Long filePdfId) {
        this.filePdfId = filePdfId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public Long getFileMp3Id() {
        return fileMp3Id;
    }

    public void setFileMp3Id(Long fileMp3Id) {
        this.fileMp3Id = fileMp3Id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }
}