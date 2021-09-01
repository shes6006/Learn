package eoschu.httpMessageConverter.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import eoschu.httpMessageConverter.bean.UserInfo;

@Controller
public class FirstController {
//處理 RequestBody / RequestEntity / ResponseBody / ResponseBody回傳Bean json值(使用jackson)

	@RequestMapping(value="/testRequestBody")
	public String testRequestBody(@RequestBody String thisBody) {
		System.out.println(thisBody);
		return "success";
	}
	@RequestMapping(value="/testRequestEntity")
	public String testRequestEntity(RequestEntity<String> thisEntity) {
		System.out.println(thisEntity.getHeaders());
		System.out.println(thisEntity.getBody());
		return "success";
	}
	@RequestMapping("/testResponseBody")
	@ResponseBody
	public String testResponseBody() {
		return "我來拉～～";
	}
	@RequestMapping("/returnBean")
	@ResponseBody
	public UserInfo returnBean() {
		return new UserInfo("eoschu", "123445", "eos@123.com", "0988777000");
	}
	
	@RequestMapping("/testAjax")
	@ResponseBody
	public String testAjax(String username, String password){
	    System.out.println("username:"+username+",password:"+password);
	    return "hello,ajax";
	}

//處理 攔截器Interceptor / 異常處理 ExceptionHandler

	//@ExceptionHandler用于设置所标识方法处理的异常
    @ExceptionHandler(ArithmeticException.class)
    //ex表示当前请求处理中出现的异常对象
    public String handleArithmeticException(Exception ex, Model model){
        model.addAttribute("ex", ex);
        return "error";
    }
}
