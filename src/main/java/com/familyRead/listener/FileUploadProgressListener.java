package com.familyRead.listener;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import com.familyRead.context.Progress;


@Component
public class FileUploadProgressListener implements ProgressListener {
	private HttpSession session;

	public void setSession(HttpSession session) {
		this.session = session;
		Progress status = new Progress();// 保存上传状态
		session.setAttribute("status", status);
	}

	public void update(long bytesRead, long contentLength, int items) {
		Progress status = (Progress) session.getAttribute("status");
		status.setBytesRead(bytesRead);
		status.setContentLength(contentLength);
		status.setItems(items);

	}

}