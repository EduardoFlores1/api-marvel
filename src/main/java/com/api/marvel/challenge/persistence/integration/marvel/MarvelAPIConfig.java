package com.api.marvel.challenge.persistence.integration.marvel;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MarvelAPIConfig {

    @Qualifier("md5Encoder")
    private final PasswordEncoder md5Encoder;

    private long timestamp = new Date(System.currentTimeMillis()).getTime();

    @Value("${integration.marvel.public-key}")
    private String publicKey;

    @Value("${integration.marvel.private-key}")
    private String privateKey;

    public MarvelAPIConfig(PasswordEncoder md5Encoder) {
        this.md5Encoder = md5Encoder;
    }

    private String getHash() {
        String hashDecoded = Long.toString(timestamp).concat(privateKey).concat(publicKey);
        return md5Encoder.encode(hashDecoded);
    }

    public Map<String, String> getAuthenticationQueryParams() {
        Map<String, String> securityQueryParams = new HashMap<>();
        securityQueryParams.put("ts", Long.toString(timestamp));
        securityQueryParams.put("apiKey", publicKey);
        securityQueryParams.put("hash", this.getHash());
        return securityQueryParams;
    }
}
