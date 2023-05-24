package com.example.demo.controller;

import com.example.demo.firebase.FcmService;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

  private final FcmService fcmService;

  @RequestMapping("/")
  public String hello() {
    String registrationToken = "xxxxxxxxxxxxxxxxxxxxxx:xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx_cwd";

    Notification notification = fcmService.createNotification(
        "通知タイトル",
        "通知本文");

    Message message = Message
        .builder()
        .putData("キー1", "値1")
        .putData("キー2", "値2")
        .putData("キー3", "値3")
        .setNotification(notification)
        .setToken(registrationToken)
        .build();

    return fcmService.sendMessage(message) + "\n";
  }
}
