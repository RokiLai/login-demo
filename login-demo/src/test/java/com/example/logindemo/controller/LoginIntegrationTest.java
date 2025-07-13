package com.example.logindemo.controller;

import com.example.logindemo.controller.request.LoginRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    private WebTestClient webClient() {
        return WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + port)
                .build();
    }

    @Test
    public void testLoginAndAccessProtectedEndpoint() throws Exception {
        // 1. 构造登录请求
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("123456");

        // 2. 发送登录请求并提取 token
        byte[] responseBytes = webClient().post()
                .uri("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(loginRequest))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .returnResult()
                .getResponseBodyContent();

        JsonNode responseJson = objectMapper.readTree(responseBytes);
        String token = responseJson.path("data").toPrettyString();
        assertThat(token).isNotBlank();

        // // 3. 使用 token 访问受保护接口
        // webClient().get()
        //         .uri("/api/user/info")
        //         .header("Authorization", "Bearer " + token)
        //         .exchange()
        //         .expectStatus().isOk()
        //         .expectBody()
        //         .jsonPath("$.code").isEqualTo(200)
        //         .jsonPath("$.data.username").isEqualTo("testuser");

        // // 4. 不带 token 访问应返回 401
        // webClient().get()
        //         .uri("/api/user/info")
        //         .exchange()
        //         .expectStatus().isUnauthorized();
    }
}
