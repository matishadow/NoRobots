package com.norobots;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.norobots.pocos.PasswordPayload;
import com.norobots.pocos.PasswordPayloadData;
import com.norobots.pocos.PasswordResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class PasswordRequestSender extends GenericRequestSender {
    private final String passwordUrl = "https://login.ingbank.pl/mojeing/rest/renlogin";

    public PasswordResponse SendPasswordRequest(HttpClient httpClient, String login, String password, String salt, String mask, String key) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        var mixedPasswordAndSalt = mixSaltAndPassword(password, salt, mask);
        var hmac = createSha1Hmac(key, mixedPasswordAndSalt);
        var passwordPayload = convertObjectToStringEntity(createPasswordPayload(login, hmac));
        var httpPost = createHttpPost(passwordUrl, passwordPayload);
        var response = httpClient.execute(httpPost);
        var entity = response.getEntity();
        var responseJson = EntityUtils.toString(entity);

        return new ObjectMapper().readValue(responseJson, PasswordResponse.class);
    }

    private String mixSaltAndPassword(String password, String salt, String mask) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == '*') {
                result.append(password.charAt(i));
            } else if (mask.charAt(i) == '+') {
                result.append(salt.charAt(i));
            }
        }

        return result.toString();
    }

    private String createSha1Hmac(String key, String message) throws InvalidKeyException, NoSuchAlgorithmException {
        final String hmacName = "HmacSHA1";
        Mac sha1Hmac;
        String result;

        byte[] byteKey = key.getBytes(StandardCharsets.UTF_8);
        sha1Hmac = Mac.getInstance(hmacName);
        SecretKeySpec keySpec = new SecretKeySpec(byteKey, hmacName);
        sha1Hmac.init(keySpec);
        byte[] mac_data = sha1Hmac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        result = bytesToHex(mac_data);

        return result;
    }

    private String bytesToHex(byte[] bytes) {
        final char[] hexArray = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private PasswordPayload createPasswordPayload(String login, String passwordHmac) {
        var passwordPayloadData = new PasswordPayloadData("D", login, passwordHmac);

        return new PasswordPayload("", "PL", "", passwordPayloadData);
    }
}
