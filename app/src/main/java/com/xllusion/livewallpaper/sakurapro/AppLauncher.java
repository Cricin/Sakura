package com.xllusion.livewallpaper.sakurapro;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AppLauncher extends Activity {
  private static final int REQUEST_CODE = 1;

  @Override
  public void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    Toast.makeText(this, R.string.choose_app, Toast.LENGTH_LONG).show();
    try {
      Intent intent = new Intent();
      intent.setAction("android.service.wallpaper.LIVE_WALLPAPER_CHOOSER");
      startActivityForResult(intent, REQUEST_CODE);
    } catch (ActivityNotFoundException ignore) {
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_CODE) {
      finish();
    }
  }
}
