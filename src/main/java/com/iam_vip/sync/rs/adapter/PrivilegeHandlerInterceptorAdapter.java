/**
 * 
 */
package com.iam_vip.sync.rs.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iam_vip.sync.rs.C;
import com.iam_vip.sync.rs.u.MethodUtil;

/**
 * @author Colin
 */
public class PrivilegeHandlerInterceptorAdapter extends HandlerInterceptorAdapter implements C {
	
	private String ROOT; // ** 根目录 URI
	
	
	/**
	 * 
	 */
	public PrivilegeHandlerInterceptorAdapter() {}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
		
		System.out.println( "RequestURI " + request.getRequestURI() );
		System.out.println( "RequestURL " + request.getRequestURL() );
		
		// ** 匹配到需要拦截的 URL
		
		if ( handler instanceof HandlerMethod ) {
			
			HandlerMethod method = ( HandlerMethod ) handler;
			String className = method.getBean().getClass().getName(); // ** Controller 的类名
			String methodName = method.getMethod().getName(); // ** Controller 里执行的方法名称
			System.out.println( "Execute " + className + "." + methodName );
			
			
			if ( ROOT == null ) {
				ROOT = request.getContextPath();
			}
			
			
			if ( MethodUtil.isEnable() ) { // ** 过滤掉配置忽略的请求
				
				boolean ignore = false;
				String source = className + "." + methodName;
				
				for ( String line : MethodUtil.getMethods() ) {
					if ( source.equals( line ) ) {
						ignore = true;
						break;
					}
				}
				if ( ignore ) { return true; }
			}
			
		}
		
		return super.preHandle( request, response, handler );
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {
		
		super.postHandle( request, response, handler, modelAndView );
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) throws Exception {
		
		super.afterCompletion( request, response, handler, ex );
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterConcurrentHandlingStarted(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public void afterConcurrentHandlingStarted( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
		
		super.afterConcurrentHandlingStarted( request, response, handler );
	}
	
}
