package com.ayantsoft.payroll.test;

import org.apache.commons.codec.binary.Base64;

public class Base64Convertor {
	public static void main(String[] args) {
		String plainClientCredentials="my-trusted-client:secret";		
		String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));
		System.out.println(base64ClientCredentials);
	}
}