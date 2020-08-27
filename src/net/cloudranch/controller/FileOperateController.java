package net.cloudranch.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.cloudranch.utils.BasicUtils;
import net.sf.json.JSONObject;

@Controller
public class FileOperateController {
	/**
	 * 文件上传
	 * @param request
	 * @param path1 相对路径
	 * @param account 账号
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject uploadFile(HttpServletRequest request,
			@RequestParam("path") String path1,
			@RequestParam("account") String account,
			@RequestParam("file") MultipartFile file) {
		JSONObject json = new JSONObject();
		if(!file.isEmpty()) {
			String fileName = account + "-" + new Date().getTime() + BasicUtils.getRandomString(6);
			String path = BasicUtils.getStoragePath(path1.replace(",", "\\"));
			File f = new File(path);
			if(!f.exists()) {
				f.mkdirs();
			}
			String oldName = file.getOriginalFilename();
			String fileType = oldName.substring(oldName.lastIndexOf("."), oldName.length());
			fileName = fileName + fileType;
			json.put("fileName", fileName);
			File ff = new File(path + "\\" +fileName);
			try {
				file.transferTo(ff);
			} catch (Exception e) {
				return json;
			}
		}else {
			json.put("fileName", "error");
		}
		return json;
	}
	/**
	 * 文件下载
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/downloadFile")
	ResponseEntity<byte[]> downloadFile(@RequestParam("name") String name,@RequestParam("fileName") String fileName) throws IOException  {
		String path = BasicUtils.getStoragePath(name+"\\"+fileName);
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentDispositionFormData("attachment", fileName); 
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
        ResponseEntity<byte[]> result = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
        return result; 
	}
	
}
