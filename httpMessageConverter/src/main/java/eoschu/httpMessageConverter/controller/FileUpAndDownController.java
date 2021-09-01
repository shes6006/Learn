package eoschu.httpMessageConverter.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUpAndDownController {

	@RequestMapping("/testDownload")
	public ResponseEntity<byte[]> testDownload(HttpSession session) throws IOException {
		ServletContext servletContext = session.getServletContext();
		String realPath = servletContext.getRealPath("/static/img/1.jpg");
		System.out.println(realPath);
		InputStream inputStream = new FileInputStream(realPath);
		byte[] myBytes = new byte[inputStream.available()];
		inputStream.read(myBytes);
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=1.jpg");
		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(myBytes, headers, statusCode);
		inputStream.close();
		System.out.println(responseEntity.getHeaders());
		System.out.println(responseEntity.getBody());
		return responseEntity;
	}

	@RequestMapping("/testUpload")
	public String testUpload(MultipartFile photo, HttpSession session) throws IllegalStateException, IOException {
		// 獲取上傳文件名
		String fileName = photo.getOriginalFilename();
		// 寫文件後綴名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		// 將UUID作為文件名
		String uuid = UUID.randomUUID().toString();
		// 通過ServletContext獲取服務器中photo目錄的路徑
		ServletContext servletContext = session.getServletContext();
		String photoPath = servletContext.getRealPath("photo");
		// 判斷photoPath所對應路徑是否存在
		File file = new File(photoPath);
		//若不存在，則新建目錄
		if (!file.exists()) {
			file.mkdir();
		}
		String finalPath = photoPath + File.separator + fileName;
		photo.transferTo(new File(finalPath));
		return "success";
	}
//	//文件下載上傳
	;
//    @RequestMapping("/testDownload")
//    public ResponseEntity<byte[]> testDownload(HttpSession session) throws IOException{
//    	//獲取ServeletContext物件
//    	ServletContext servletContext = session.getServletContext();
//    	//建立img絕對路徑
//    	String realPath = servletContext.getRealPath("/static/img/1.jpg");
//    	System.out.println(realPath);
//    	//建立輸入流，將網路路徑從inputStream中接到電腦裡
//    	InputStream inputStream = new FileInputStream(realPath);
//    	//建立字元組，確認數據流裡中所有的字節的總數
//    	byte[] myBytes = new byte[inputStream.available()];
//    	//將bytes字元組放到read的暫存空間
//    	inputStream.read(myBytes);
//    	//創建HttpHeaders 
//    	MultiValueMap<String, String> headers = new HttpHeaders();
//    	//內容組成：“附件”下載方式/預設名稱
//        headers.add("Content-Disposition", "attachment;filename=1.jpg");
//    	HttpStatus statusCode = HttpStatus.OK;
//    	//創建ResponseEntity，將
//    	ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(myBytes, headers, statusCode);
//    	//關閉輸入流
//    	inputStream.close();
//    	System.out.println(responseEntity.getHeaders());
//    	System.out.println(responseEntity.getBody());
//    	return responseEntity;
//    }
}
