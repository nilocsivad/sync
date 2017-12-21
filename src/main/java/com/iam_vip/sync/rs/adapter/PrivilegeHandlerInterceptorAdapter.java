/**
 * 
 */
package com.iam_vip.sync.rs.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iam_vip.sync.rs.C;

/**
 * @author Colin
 */
public class PrivilegeHandlerInterceptorAdapter extends HandlerInterceptorAdapter implements C {
	
	
	/**
	 * 
	 */
	public PrivilegeHandlerInterceptorAdapter() {}
	
	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
		return super.preHandle( request, response, handler );
	}
	
	
}
