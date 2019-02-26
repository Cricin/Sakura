package com.xllusion.livewallpaper.sakurapro;

class SakuraTheme {
  /* renamed from: a */
  static int mBackground = 0;
  /* renamed from: b */
  static int mBranchLeft = 0;
  /* renamed from: c */
  static int mBranchRight = 0;
  /* renamed from: d */
  static int mStars = R.drawable.stars;
  /* renamed from: e */
  static int mSakuraLeft = 0;
  /* renamed from: f */
  static int mSakuraRight = 0;
  /* renamed from: g */
  static int mSakuraFlower = 0;
  /* renamed from: h */
  static int mSakuraPetal1 = 0;
  /* renamed from: i */
  static int mSakuraPatal2 = 0;
  /* renamed from: j */
  private static int mCurrentTheme = 1;

  /* renamed from: a */
  static void initBackground(String str) {
    if (str == null) return;
    if (str.equals("Cream")) {
      mBackground = R.drawable.bg2;
      mBranchLeft = R.drawable.branch2_l;
      mBranchRight = R.drawable.branch2_r;
    } else if (str.equals("Pink")) {
      mBackground = R.drawable.bg3;
      mBranchLeft = R.drawable.branch3_l;
      mBranchRight = R.drawable.branch3_r;
    } else if (str.equals("Lime")) {
      mBackground = R.drawable.bg4;
      mBranchLeft = R.drawable.branch4_l;
      mBranchRight = R.drawable.branch4_r;
    } else if (str.equals("Aqua")) {
      mBackground = R.drawable.bg5;
      mBranchLeft = R.drawable.branch5_l;
      mBranchRight = R.drawable.branch5_r;
    } else if (str.equals("Mint")) {
      mBackground = R.drawable.bg6;
      mBranchLeft = R.drawable.branch6_l;
      mBranchRight = R.drawable.branch6_r;
    } else if (str.equals("Indigo")) {
      mBackground = R.drawable.bg7;
      mBranchLeft = R.drawable.branch7_l;
      mBranchRight = R.drawable.branch7_r;
    } else if (str.equals("Navy")) {
      mBackground = R.drawable.bg8;
      mBranchLeft = R.drawable.branch8_l;
      mBranchRight = R.drawable.branch8_r;
    } else if (str.equals("Lavender")) {
      mBackground = R.drawable.bg9;
      mBranchLeft = R.drawable.branch9_l;
      mBranchRight = R.drawable.branch9_r;
    } else if (str.equals("Crimson")) {
      mBackground = R.drawable.bg10;
      mBranchLeft = R.drawable.branch10_l;
      mBranchRight = R.drawable.branch10_r;
    } else if (str.equals("Orange")) {
      mBackground = R.drawable.bg11;
      mBranchLeft = R.drawable.branch11_l;
      mBranchRight = R.drawable.branch11_r;
    } else if (str.equals("Olive")) {
      mBackground = R.drawable.bg12;
      mBranchLeft = R.drawable.branch12_l;
      mBranchRight = R.drawable.branch12_r;
    } else if (str.equals("Black")) {
      mBackground = R.drawable.bg13;
      mBranchLeft = R.drawable.branch13_l;
      mBranchRight = R.drawable.branch13_r;
    } else if (str.equals("Gold")) {
      mBackground = R.drawable.bg14;
      mBranchLeft = R.drawable.branch14_l;
      mBranchRight = R.drawable.branch14_r;
    } else {
      mBackground = R.drawable.bg1;
      mBranchLeft = R.drawable.branch1_l;
      mBranchRight = R.drawable.branch1_r;
    }
  }

  /* renamed from: a */
  static void setTheme(int newTheme) {
    mCurrentTheme = newTheme;
  }

  /* renamed from: b */
  static void initSakura(String str) {
    if (mCurrentTheme == 2) {
      if (str.equals("Cream")) {
        mSakuraLeft = R.drawable.sakura2_l;
        mSakuraRight = R.drawable.sakura2_l;
      } else if (str.equals("Pink")) {
        mSakuraLeft = R.drawable.sakura3_l;
        mSakuraRight = R.drawable.sakura3_r;
      } else if (str.equals("Lime")) {
        mSakuraLeft = R.drawable.sakura4_l;
        mSakuraRight = R.drawable.sakura4_r;
      } else if (str.equals("Aqua")) {
        mSakuraLeft = R.drawable.sakura5_l;
        mSakuraRight = R.drawable.sakura5_r;
      } else if (str.equals("Mint")) {
        mSakuraLeft = R.drawable.sakura6_l;
        mSakuraRight = R.drawable.sakura6_r;
      } else if (str.equals("Indigo")) {
        mSakuraLeft = R.drawable.sakura7_l;
        mSakuraRight = R.drawable.sakura7_r;
      } else if (str.equals("Navy")) {
        mSakuraLeft = R.drawable.sakura8_l;
        mSakuraRight = R.drawable.sakura8_l;
      } else if (str.equals("Lavender")) {
        mSakuraLeft = R.drawable.sakura9_l;
        mSakuraRight = R.drawable.sakura9_l;
      } else if (str.equals("Crimson")) {
        mSakuraLeft = R.drawable.sakura10_l;
        mSakuraRight = R.drawable.sakura10_l;
      } else {
        mSakuraLeft = R.drawable.sakura1_l;
        mSakuraRight = R.drawable.sakura1_r;
      }
    } else if (str.equals("Cream")) {
      mSakuraLeft = R.drawable.sakura2s_l;
      mSakuraRight = R.drawable.sakura2s_r;
    } else if (str.equals("Pink")) {
      mSakuraLeft = R.drawable.sakura3s_l;
      mSakuraRight = R.drawable.sakura3s_r;
    } else if (str.equals("Lime")) {
      mSakuraLeft = R.drawable.sakura4s_l;
      mSakuraRight = R.drawable.sakura4s_r;
    } else if (str.equals("Aqua")) {
      mSakuraLeft = R.drawable.sakura5s_l;
      mSakuraRight = R.drawable.sakura5s_r;
    } else if (str.equals("Mint")) {
      mSakuraLeft = R.drawable.sakura6s_l;
      mSakuraRight = R.drawable.sakura6s_r;
    } else if (str.equals("Indigo")) {
      mSakuraLeft = R.drawable.sakura7s_l;
      mSakuraRight = R.drawable.sakura7s_r;
    } else if (str.equals("Navy")) {
      mSakuraLeft = R.drawable.sakura8s_l;
      mSakuraRight = R.drawable.sakura8s_r;
    } else if (str.equals("Lavender")) {
      mSakuraLeft = R.drawable.sakura9s_l;
      mSakuraRight = R.drawable.sakura9s_l;
    } else if (str.equals("Crimson")) {
      mSakuraLeft = R.drawable.sakura10s_l;
      mSakuraRight = R.drawable.sakura10s_l;
    } else {
      mSakuraLeft = R.drawable.sakura1s_l;
      mSakuraRight = R.drawable.sakura1s_l;
    }
  }

  /* renamed from: c */
  static void initFlowerAndPetals(String str) {
    if (mCurrentTheme == 2) {
      switch (str) {
        case "Cream":
          mSakuraFlower = R.drawable.sakura2_1;
          mSakuraPetal1 = R.drawable.sakura2_2;
          mSakuraPatal2 = R.drawable.sakura2_3;
          break;
        case "Pink":
          mSakuraFlower = R.drawable.sakura3_1;
          mSakuraPetal1 = R.drawable.sakura3_2;
          mSakuraPatal2 = R.drawable.sakura3_3;
          break;
        case "Lime":
          mSakuraFlower = R.drawable.sakura4_1;
          mSakuraPetal1 = R.drawable.sakura4_2;
          mSakuraPatal2 = R.drawable.sakura4_3;
          break;
        case "Aqua":
          mSakuraFlower = R.drawable.sakura5_1;
          mSakuraPetal1 = R.drawable.sakura5_2;
          mSakuraPatal2 = R.drawable.sakura5_3;
          break;
        case "Mint":
          mSakuraFlower = R.drawable.sakura6_1;
          mSakuraPetal1 = R.drawable.sakura6_2;
          mSakuraPatal2 = R.drawable.sakura6_3;
          break;
        case "Indigo":
          mSakuraFlower = R.drawable.sakura7_1;
          mSakuraPetal1 = R.drawable.sakura7_2;
          mSakuraPatal2 = R.drawable.sakura7_3;
          break;
        case "Navy":
          mSakuraFlower = R.drawable.sakura8_1;
          mSakuraPetal1 = R.drawable.sakura8_2;
          mSakuraPatal2 = R.drawable.sakura8_3;
          break;
        case "Lavender":
          mSakuraFlower = R.drawable.sakura9_1;
          mSakuraPetal1 = R.drawable.sakura9_2;
          mSakuraPatal2 = R.drawable.sakura9_3;
          break;
        case "Crimson":
          mSakuraFlower = R.drawable.sakura10_1;
          mSakuraPetal1 = R.drawable.sakura10_2;
          mSakuraPatal2 = R.drawable.sakura10_3;
          break;
        default:
          mSakuraFlower = R.drawable.sakura1_1;
          mSakuraPetal1 = R.drawable.sakura1_2;
          mSakuraPatal2 = R.drawable.sakura1_3;
          break;
      }
    } else if (str.equals("Cream")) {
      mSakuraFlower = R.drawable.sakura2_1s;
      mSakuraPetal1 = R.drawable.sakura2_2;
      mSakuraPatal2 = R.drawable.sakura2_3;
    } else if (str.equals("Pink")) {
      mSakuraFlower = R.drawable.sakura3_1s;
      mSakuraPetal1 = R.drawable.sakura3_2;
      mSakuraPatal2 = R.drawable.sakura3_3;
    } else if (str.equals("Lime")) {
      mSakuraFlower = R.drawable.sakura4_1s;
      mSakuraPetal1 = R.drawable.sakura4_2;
      mSakuraPatal2 = R.drawable.sakura4_3;
    } else if (str.equals("Aqua")) {
      mSakuraFlower = R.drawable.sakura5_1s;
      mSakuraPetal1 = R.drawable.sakura5_2;
      mSakuraPatal2 = R.drawable.sakura5_3;
    } else if (str.equals("Mint")) {
      mSakuraFlower = R.drawable.sakura6_1s;
      mSakuraPetal1 = R.drawable.sakura6_2;
      mSakuraPatal2 = R.drawable.sakura6_3;
    } else if (str.equals("Indigo")) {
      mSakuraFlower = R.drawable.sakura7_1s;
      mSakuraPetal1 = R.drawable.sakura7_2;
      mSakuraPatal2 = R.drawable.sakura7_3;
    } else if (str.equals("Navy")) {
      mSakuraFlower = R.drawable.sakura8_1s;
      mSakuraPetal1 = R.drawable.sakura8_2;
      mSakuraPatal2 = R.drawable.sakura8_3;
    } else if (str.equals("Lavender")) {
      mSakuraFlower = R.drawable.sakura9_1s;
      mSakuraPetal1 = R.drawable.sakura9_2;
      mSakuraPatal2 = R.drawable.sakura9_3;
    } else if (str.equals("Crimson")) {
      mSakuraFlower = R.drawable.sakura10_1s;
      mSakuraPetal1 = R.drawable.sakura10_2;
      mSakuraPatal2 = R.drawable.sakura10_3;
    } else {
      mSakuraFlower = R.drawable.sakura1_1s;
      mSakuraPetal1 = R.drawable.sakura1_2;
      mSakuraPatal2 = R.drawable.sakura1_3;
    }
  }
}
