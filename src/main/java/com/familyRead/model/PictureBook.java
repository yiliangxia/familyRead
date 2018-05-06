package com.familyRead.model;

public class PictureBook {
    private Long id;

    private Long fileImgId;

    private Long filePdfId;
    
    private Long fileMp3Id;

    private String createTime;

    private String createBy;

    private String updateTime;

    private String updateBy;

    private String bookName;

    private Long groupId;
    
    private String desc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

	public Long getFileMp3Id() {
		return fileMp3Id;
	}

	public void setFileMp3Id(Long fileMp3Id) {
		this.fileMp3Id = fileMp3Id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
    
}