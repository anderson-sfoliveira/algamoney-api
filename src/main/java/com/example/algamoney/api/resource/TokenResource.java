package com.example.algamoney.api.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.config.property.AlgamoneyApiProperty;
import com.example.algamoney.api.wrapper.CustomHttpServletResponseWrapper;

@RestController
@RequestMapping("/tokens")
public class TokenResource {

	@Autowired
	private AlgamoneyApiProperty algamoneyApiProperty;
	
	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest req, HttpServletResponse resp) {
		
		if (algamoneyApiProperty.getSeguranca().isEnableHttps()) {
			Cookie refreshTokenCookie = new Cookie("refreshToken", null);

			refreshTokenCookie.setHttpOnly(true);
			refreshTokenCookie.setSecure(algamoneyApiProperty.getSeguranca().isEnableHttps());
			refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");
			refreshTokenCookie.setMaxAge(0);
			refreshTokenCookie.setDomain(algamoneyApiProperty.getDomainTokenCookie());

			CustomHttpServletResponseWrapper customHttpServletResponseWrapper = new CustomHttpServletResponseWrapper(resp);
			customHttpServletResponseWrapper.addCookie(refreshTokenCookie);
		} else {
			Cookie cookie = new Cookie("refreshToken", null);
			cookie.setHttpOnly(true);
			cookie.setSecure(algamoneyApiProperty.getSeguranca().isEnableHttps());
			cookie.setPath(req.getContextPath() + "/oauth/token");
			cookie.setMaxAge(0);
			
			resp.addCookie(cookie);
		}
		
		resp.setStatus(HttpStatus.NO_CONTENT.value());
	}
}
