package com.norobots;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        final var loginUrl = "https://login.ingbank.pl/mojeing/rest/renchecklogin";
        final var passwordUrl = "https://login.ingbank.pl/mojeing/rest/renlogin";
        final var accountsUrl = "https://login.ingbank.pl/mojeing/rest/rengetallingprds";
        var login = getLogin();
        var password = getPassword();
        var httpClient = HttpClients.custom().setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE).build();

        var loginPayload = convertObjectToStringEntity(createLoginPayload(login));
        var httpPost = createHttpPost(loginUrl, loginPayload);
        var response = httpClient.execute(httpPost);
        var entity = response.getEntity();
        String responseJson = EntityUtils.toString(entity);
        var loginResponse = new ObjectMapper().readValue(responseJson, LoginResponse.class);

        var mixedPasswordAndSalt = mixSaltAndPassword(password, loginResponse.data.salt, loginResponse.data.mask);
        var hmac = createSha1Hmac(loginResponse.data.key, mixedPasswordAndSalt);
        var passwordPayload = convertObjectToStringEntity(createPasswordPayload(login, hmac));
        httpPost = createHttpPost(passwordUrl, passwordPayload);
        response = httpClient.execute(httpPost);
        entity = response.getEntity();
        responseJson = EntityUtils.toString(entity);
        var passwordResponse = new ObjectMapper().readValue(responseJson, PasswordResponse.class);

        var accountsPayload = convertObjectToStringEntity(new GenericPayload(passwordResponse.data.token, "PL", ""));
        httpPost = createHttpPost(accountsUrl, accountsPayload);

        response = httpClient.execute(httpPost);
        entity = response.getEntity();
        responseJson = EntityUtils.toString(entity);
        var accountsResponse = new ObjectMapper().readValue(responseJson, AccountsResponse.class);
    }

    static LoginPayload createLoginPayload(String login) throws JsonProcessingException, UnsupportedEncodingException {
        var loginData = new LoginPayloadData(login);

        return new LoginPayload("", "PL", "", loginData);
    }

    static PasswordPayload createPasswordPayload(String login, String passwordHmac) {
        var passwordPayloadData = new PasswordPayloadData("D", login, passwordHmac);

        return new PasswordPayload("", "PL", "", passwordPayloadData);
    }

    static StringEntity convertObjectToStringEntity(Object object) throws JsonProcessingException, UnsupportedEncodingException {
        var objectMapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
        var stringPayload = objectMapper.writeValueAsString(object);

        return new StringEntity(stringPayload);
    }

    static HttpPost createHttpPost(String url, StringEntity object) {
        var httpPost = new HttpPost(url);
        httpPost.addHeader("content-type", "application/json");
        httpPost.addHeader("connection", "keep-alive");
        httpPost.addHeader("cookies_accepted", "true");
        httpPost.addHeader("X-Wolf-Protection", "0.5259627313757991");
        httpPost.setEntity(object);

        return httpPost;
    }

    static String getLogin() {
        System.out.println("Enter your username: ");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    static String getPassword() {
        System.out.println("Enter your password: ");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    static String createSha1Hmac(String key, String message) throws InvalidKeyException, NoSuchAlgorithmException {
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

    private static String bytesToHex(byte[] bytes) {
        final  char[] hexArray = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String mixSaltAndPassword(String password, String salt, String mask) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mask.length(); i++){
            if (mask.charAt(i) == '*'){
                result.append(password.charAt(i));
            }
            else if (mask.charAt(i) == '+') {
                result.append(salt.charAt(i));
            }
        }

        return result.toString();
    }
}
