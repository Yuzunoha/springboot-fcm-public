package com.example.demo.controller;

import java.nio.ByteBuffer;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/byte")
public class ByteController {

  @RequestMapping("/")
  public String index() {
    String str1 = "こんにちは。お元気ですか。";

    ByteBuffer byteBuffer1 = ByteBuffer.wrap(str1.getBytes());

    String hexStr = byteBufferToString(byteBuffer1);
    System.out.println(hexStr);

    ByteBuffer byteBuffer2 = stringToByteBuffer(hexStr);

    String str2 = new String(byteBuffer2.array());
    System.out.println(str2);

    return str2;
  }

  public String byteBufferToString(ByteBuffer byteBuffer) {
    byte[] byteArray = byteBuffer.array();
    return new String(Hex.encodeHex(byteArray));
  }

  public ByteBuffer stringToByteBuffer(String hexStr) {
    try {
      byte[] byteArray = Hex.decodeHex(hexStr.toCharArray());
      return ByteBuffer.wrap(byteArray);
    } catch (DecoderException e) {
      e.printStackTrace();
      return null;
    }
  }
}
