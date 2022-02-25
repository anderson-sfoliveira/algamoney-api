package com.example.algamoney.api.cors;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.algamoney.api.config.property.AlgamoneyApiProperty;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	@Autowired
	private AlgamoneyApiProperty algamoneyApiProperty;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String[] allowDomain = algamoneyApiProperty.getOriginPermitida();
		Set<String> allowedOrigins = new HashSet<String>(Arrays.asList (allowDomain));                  
        
        String originHeader = req.getHeader("Origin");
        
        if (allowedOrigins.contains(originHeader)) {
        	resp.setHeader("Access-Control-Allow-Origin", originHeader);
        } else {
        	allowedOrigins.forEach(p -> resp.setHeader("Access-Control-Allow-Origin", p));
        }
        
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		
		if ("OPTIONS".equals(req.getMethod()) && allowedOrigins.contains(originHeader)) {
			resp.setHeader("Access-Control-Allow-Methods", "POST, DELETE, GET, PUT, OPTIONS");
			resp.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
			resp.setHeader("Access-Control-Max-Age", "10"); // tempo do pre-flight para fazer a requisição OPTIONS novamente = 10 segundos
			
			resp.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, resp);
		}
	}

}
