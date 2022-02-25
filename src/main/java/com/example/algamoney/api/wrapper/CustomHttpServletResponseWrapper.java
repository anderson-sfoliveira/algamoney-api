package com.example.algamoney.api.wrapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CustomHttpServletResponseWrapper extends HttpServletResponseWrapper {

	public CustomHttpServletResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	@Override
	public void addCookie(Cookie cookie) {
		super.addHeader("Set-Cookie", getCookieValue(cookie));
	}

	private String getCookieValue(Cookie cookie) {

		StringBuilder builder = new StringBuilder();
		builder.append(cookie.getName()).append('=').append(cookie.getValue());
		builder.append(";Path=").append(cookie.getPath());

		if (cookie.isHttpOnly()) {
			builder.append(";HttpOnly");
		}

		if (cookie.getSecure()) {
			builder.append(";Secure;SameSite=None");
		} else {
			builder.append(";SameSite=Strict");
		}

		builder.append(";Domain=" + cookie.getDomain());
		builder.append(";Max-Age=" + cookie.getMaxAge());
		return builder.toString();
	}
}
