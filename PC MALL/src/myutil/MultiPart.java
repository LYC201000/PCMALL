package myutil;

import java.io.File;
import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class MultiPart extends HttpServlet {

	List items;
	public MultiPart(HttpServletRequest request) throws Exception{
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		items = upload.parseRequest(request);
	}
	public String getParameter(String fieldName) throws UnsupportedEncodingException{
		for(int cnt=0;cnt< items.size();cnt++) {
			FileItem item = (FileItem) items.get(cnt);
			if(item.getFieldName().equals(fieldName))
				return item.getString("utf-8");
		}
		return null;
	}
	//주어진 이름에 해당하는 업로드 파일의 경로명을 리턴하는 메서드
	public String getFilePath(String fieldName) throws UnsupportedEncodingException{
		for (int cnt = 0 ;cnt<items.size(); cnt++) {
			FileItem item = (FileItem) items.get(cnt);
			if(item.getFieldName().equals(fieldName))
				return item.getName();
			
		}
		return null;
		
	}

	public String getFileName(String fieldName) throws UnsupportedEncodingException{
		String path = getFilePath(fieldName);
		int index1 = path.lastIndexOf("/");
		int index2 = path.lastIndexOf("\\");
		int index = 0;
		if(index1 > index2)
			index = index1;
		else
			index = index2;
		if(index<0)
			return path;
		else
			return path.substring(index + 1);
	}
	
	public void saveFile(String fieldName,String path) throws Exception{
		for(int cnt = 0; cnt<items.size(); cnt++) {
			FileItem item = (FileItem) items.get(cnt);
			if(item.getFieldName().equals(fieldName)) {
				if(!item.isFormField()) {
					item.write(new File(path));
					return;
				}
			}
		}
	}
}
