package com.familyRead.filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.familyRead.model.Customer;

/**.
 * 用户登陆的拦截器
 * @author xyl      
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	/**.
	 * LOGGER 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);


	/**
	 *@return boolean
	 */
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler){
		boolean flag = false;
		Object customer = null;
		try {
			//1、请求到登录页面 放行  
			String url = request.getServletPath();
			if(url.contains("/login")||url.contains("/toLogin")){
				return true;
			};
				HttpSession session = request.getSession();	
				customer = session.getAttribute("customer");
			 	request.getSession().setAttribute("customer", customer);
					if(customer instanceof Customer){
						flag = true;
					}else{
						response.sendRedirect(request.getContextPath()+"/toLogin"); 
					}
					return flag;
		} catch (Exception e) {
			LOGGER.error("LoginInterceptor Error.  msg："+e.getMessage(),e);
			
		}
		return false;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		response.setDateHeader("expires", 0);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("pragma", "no-cache");
		//LOGGER.info("控制器的方法已经执行完毕，转换成视图之前的处理；");
	}
	/**
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param handler handler
	 * @param ex Exception
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
	{
		//LOGGER.info("视图已处理完后执行的方法，通常用于释放资源；");
		response.setDateHeader("expires", 0);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("pragma", "no-cache");
	}
	/**
	 * @param url 截取.htm 的url
	 * @return String
	 */
	public  String getUrlPath(String url ){
		LOGGER.info("url:"+url);
		StringBuilder builder = new StringBuilder();
		int flag = url.indexOf(".htm");
		if(flag!=-1){
			String str =url.substring(0, flag);
			builder.append(str);
			builder.append(".htm");
		}
		return builder.toString();
	}
}
