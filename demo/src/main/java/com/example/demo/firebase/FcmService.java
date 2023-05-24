package com.example.demo.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class FcmService {

  public Notification createNotification(String title, String body) {
    Notification.Builder notificationBuilder = Notification.builder();
    notificationBuilder.setTitle(title);
    notificationBuilder.setBody(body);
    return notificationBuilder.build();
  }

  public String sendMessage(Message message) {
    try {
      if (FirebaseApp.getApps().isEmpty()) {
        FirebaseOptions options = FirebaseOptions
          .builder()
          .setCredentials(GoogleCredentials.getApplicationDefault())
          .build();
        FirebaseApp.initializeApp(options);
      }
      return FirebaseMessaging.getInstance().send(message);
    } catch (IOException | FirebaseMessagingException e) {
      e.printStackTrace();
      return e.getMessage();
    }
  }
}
