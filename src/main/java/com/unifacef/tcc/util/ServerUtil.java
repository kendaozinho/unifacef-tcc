package com.unifacef.tcc.util;

import java.net.InetAddress;

public final class ServerUtil {
  private ServerUtil() {
  }

  public static String getHostName() {
    try {
      return InetAddress.getLocalHost().getHostName();
    } catch (Throwable t) {
      System.err.println("Unable to get server host name: " + t.toString());
    }
    return null;
  }
}
