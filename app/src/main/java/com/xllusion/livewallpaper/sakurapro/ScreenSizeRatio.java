package com.xllusion.livewallpaper.sakurapro;

class ScreenSizeRatio {

  static int sDefaultSize = 1024;

  static void setSize(int size) {
    if (size > sDefaultSize) {
      sDefaultSize = size;
    } else {
      sDefaultSize = 1024;
    }
  }
}
