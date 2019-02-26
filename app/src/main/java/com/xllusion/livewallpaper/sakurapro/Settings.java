package com.xllusion.livewallpaper.sakurapro;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class Settings extends PreferenceActivity implements OnSharedPreferenceChangeListener {
  protected void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    getPreferenceManager().setSharedPreferencesName("sakurasettings");
    addPreferencesFromResource(R.xml.settings);
    getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    (findPreference("share_app")).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
      @Override
      public boolean onPreferenceClick(Preference preference) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", Settings.this.getString(R.string.app_name));
        intent.putExtra("android.intent.extra.TEXT", "https://market.android.com/details?id=com.xllusion.livewallpaper.sakurapro");
        try {
          Settings.this.startActivity(Intent.createChooser(intent, Settings.this.getString(R.string.share_app_title_label)));
        } catch (ActivityNotFoundException e) {
        }
        return true;
      }
    });
    initPetalColor();
    initAcceleration();
    initMountainPos();
    initParallaxPos();
  }

  protected void onDestroy() {
    getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    super.onDestroy();
  }

  public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
    if (str.equals("lock_petal_color")) {
      initPetalColor();
    } else if (str.equals("accelerate")) {
      initAcceleration();
    } else if (str.equals("mountain")) {
      initMountainPos();
    } else if (str.equals("parallax")) {
      initParallaxPos();
    }
  }

  private void initPetalColor() {
    boolean z = getSharedPreferences("sakurasettings", 0).getBoolean("lock_petal_color", false);
    ListPreference listPreference = (ListPreference) findPreference("petal_color");
    if (z) {
      listPreference.setEnabled(false);
    } else {
      listPreference.setEnabled(true);
    }
  }

  private void initAcceleration() {
    boolean z = getSharedPreferences("sakurasettings", 0).getBoolean("accelerate", true);
    ListPreference listPreference = (ListPreference) findPreference("acceleration");
    if (z) {
      listPreference.setEnabled(true);
    } else {
      listPreference.setEnabled(false);
    }
  }

  private void initMountainPos() {
    boolean z = getSharedPreferences("sakurasettings", 0).getBoolean("mountain", false);
    ListPreference listPreference = (ListPreference) findPreference("mountain_pos");
    if (z) {
      listPreference.setEnabled(true);
    } else {
      listPreference.setEnabled(false);
    }
  }

  private void initParallaxPos() {
    boolean z = getSharedPreferences("sakurasettings", 0).getBoolean("parallax", true);
    ListPreference listPreference = (ListPreference) findPreference("parallax_pos");
    if (z) {
      listPreference.setEnabled(false);
    } else {
      listPreference.setEnabled(true);
    }
  }
}
