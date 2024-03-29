package com.example.algamoney.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {

	private String[] originPermitida;

	private String domainTokenCookie;

	private final Seguranca seguranca = new Seguranca();

	public Seguranca getSeguranca() {
		return seguranca;
	}

	public String[] getOriginPermitida() {
		return originPermitida;
	}

	public void setOriginPermitida(String[] originPermitida) {
		this.originPermitida = originPermitida;
	}

	public String getDomainTokenCookie() {
		return domainTokenCookie;
	}

	public void setDomainTokenCookie(String domainTokenCookie) {
		this.domainTokenCookie = domainTokenCookie;
	}

	public static class Seguranca {

		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}

	}

}
