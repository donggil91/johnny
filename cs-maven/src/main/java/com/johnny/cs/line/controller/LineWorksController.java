package com.johnny.cs.line.controller;

import com.johnny.cs.line.domain.request.AuthorizationCodeRequest;
import com.johnny.cs.line.domain.response.AccessCodeResponse;
import com.johnny.cs.line.service.LineWorksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LineWorksController {

    @Value("${api.auth.state}")
    private String state;

    private final LineWorksService lineWorksService;

    @GetMapping("/line/sixshop")
    public ResponseEntity<Void> lineXSixShop(final AuthorizationCodeRequest request) throws IOException {
       if ( ! request.getState().equals(state)) {
           return ResponseEntity.badRequest().build();
       }

       AccessCodeResponse accessToken = lineWorksService.getAccessToken(request.getCode());
       if ( ! accessToken.getErrorCode().equals("00")) {
           return ResponseEntity.status(401).build();
       }

       lineWorksService.testApi(accessToken.getAccessToken());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/line/test")
    public ResponseEntity<String> lineTest() throws InterruptedException {
        lineWorksService.getAccessCode();
        return ResponseEntity.ok("일단 OK");
    }
}
