package com.xllusion.livewallpaper.sakurapro;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.service.wallpaper.WallpaperService;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import java.util.Random;

import static android.graphics.Bitmap.createScaledBitmap;

public class SakuraPro extends WallpaperService {

  public Engine onCreateEngine() {
    return new SakuraEngine();
  }

  final class SakuraEngine extends Engine implements SharedPreferences.OnSharedPreferenceChangeListener, SensorEventListener {
    private float f27A = 0.5f;
    private Bitmap mMoonBitmap = null;
    private PointF mMoonPointF = new PointF();
    private boolean mShowMountain = false;
    private int mMountainGravity = 0;
    private boolean mParallax = true;
    private float mParallaxPos = 0.0f;
    private DrawThread mDrawThread;
    private boolean mVisible = false;
    private SharedPreferences mPreference;
    private SensorManager mSensorManager;
    private float mSpeed = 1.0f;
    private boolean mSmoothEnabled = false;
    private boolean mSeedEnabled = true;
    private boolean mShowMoon = true;
    private String mLeafType = "Random";
    private boolean mAccelerateEnabled = true;
    private int mFallingMode = 1;
    private int mFps = 60;
    private Rect mBackgroundBitmapRect = new Rect();
    private Rect mSurfaceRect = new Rect();
    private Bitmap mBitmap = null;
    private int mSurfaceWidth;
    private int mSurfaceHeight;
    private int mOrientation = 0;
    private PointF f53s = new PointF();
    private float mAcceletation = 0.01f;
    private SakuraItem[] mSakuraItems;
    private int mTotalFlowerCount = 12;
    private SeedItem[] mSeedItems;
    private int mSeedCount = 10;
    private Paint mSeedPaint = new Paint();

    SakuraEngine() {
      Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
      this.mSurfaceWidth = display.getWidth();
      this.mSurfaceHeight = display.getHeight();
      ScreenSizeRatio.setSize(Math.max(this.mSurfaceWidth, this.mSurfaceHeight));
      mPreference = getSharedPreferences("sakurasettings", MODE_PRIVATE);
      mPreference.registerOnSharedPreferenceChangeListener(this);
      onSharedPreferenceChanged(this.mPreference, "");

      if (mVisible) {
        startDrawThread();
      }
    }

    @SuppressWarnings("ConstantConditions")
    public final void onSharedPreferenceChanged(SharedPreferences prefs, String str) {
      stopDrawThread();
      SakuraTheme.initBackground(prefs.getString("theme", "Cream"));
      SakuraTheme.setTheme(Integer.parseInt(prefs.getString("type", "1")));
      SakuraTheme.initSakura(prefs.getString("color", "Cream"));
      if (prefs.getBoolean("lock_petal_color", false)) {
        SakuraTheme.initFlowerAndPetals(prefs.getString("color", "Cream"));
      } else {
        SakuraTheme.initFlowerAndPetals(prefs.getString("petal_color", "Cream"));
      }
      mTotalFlowerCount = Integer.parseInt(prefs.getString("total", "18"));
      mSpeed = Float.parseFloat(prefs.getString("speed", "1.0"));
      mAcceletation = Float.parseFloat(prefs.getString("acceleration", "0.01"));
      mSeedEnabled = prefs.getBoolean("seed", true);
      mShowMoon = prefs.getBoolean("moon", true);
      mLeafType = prefs.getString("leaf_type", "Random");
      mFallingMode = Integer.parseInt(prefs.getString("falling_mode", "1"));
      mShowMountain = prefs.getBoolean("mountain", true);
      mMountainGravity = Integer.parseInt(prefs.getString("mountain_pos", "0"));
      mAccelerateEnabled = prefs.getBoolean("accelerate", true);
      mSmoothEnabled = prefs.getBoolean("smooth", false);
      mParallax = prefs.getBoolean("parallax", true);
      mParallaxPos = Float.parseFloat(prefs.getString("parallax_pos", "0"));
      mFps = Integer.parseInt(prefs.getString("fps", "60"));

      synchronized (getSurfaceHolder()) {
        initBackgroundLocked();
      }
    }

    private void initBackgroundLocked() {
      final Resources res = getResources();

      BitmapFactory.Options options = new BitmapFactory.Options();
      options.inDither = true;
      options.inScaled = false;
      options.inPreferredConfig = Bitmap.Config.RGB_565;

      mBitmap = Bitmap.createBitmap(mSurfaceWidth, mSurfaceHeight, Bitmap.Config.RGB_565);
      Bitmap background = BitmapFactory.decodeResource(res, SakuraTheme.mBackground, options);
      background = Bitmap.createScaledBitmap(background, mSurfaceWidth, mSurfaceHeight, false);

      Bitmap star = BitmapFactory.decodeResource(res, SakuraTheme.mStars, options);
      Bitmap branchLeft = BitmapFactory.decodeResource(res, SakuraTheme.mBranchLeft, options);
      Bitmap branchRight = BitmapFactory.decodeResource(res, SakuraTheme.mBranchRight, options);
      Bitmap sakuraLeft = BitmapFactory.decodeResource(res, SakuraTheme.mSakuraLeft, options);
      Bitmap sakuraRight = BitmapFactory.decodeResource(res, SakuraTheme.mSakuraRight, options);

      Canvas canvas = new Canvas(mBitmap);
      canvas.setDensity(Bitmap.DENSITY_NONE);

      canvas.drawBitmap(background, 0, 0, null);
      canvas.drawBitmap(star, 0, 0, null);
      canvas.drawBitmap(branchLeft, 0, 100, null);
      canvas.drawBitmap(sakuraLeft, 0, 100, null);
      canvas.drawBitmap(branchRight, mSurfaceWidth - branchRight.getWidth(), 0, null);
      canvas.drawBitmap(sakuraRight, mSurfaceWidth - sakuraRight.getWidth(), 0, null);

      if (mShowMountain) {
        Bitmap mountain = BitmapFactory.decodeResource(res, R.drawable.mountain, options);
        int mountainWidth = mountain.getWidth();
        int mountainLeft = 0;
        if (mMountainGravity == 0) {
          mountainLeft = (mSurfaceWidth - mountainWidth) / 2;
        } else if (mMountainGravity == 1) {
          mountainLeft = mSurfaceWidth - mountainWidth;
        }
        canvas.drawBitmap(mountain, mountainLeft, mSurfaceHeight - 210, null);
      }

      initSakuraItems();
      onSizeChanged();
      if (mSeedEnabled) {
        initBgPaintAndSeedItem();
        initSeeds();
      }
      if (mShowMoon) {
        options.inDither = true;
        options.inScaled = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        mMoonBitmap = BitmapFactory.decodeResource(res, R.drawable.moon, options);
      }
    }

    public final void onCreate(SurfaceHolder surfaceHolder) {
      super.onCreate(surfaceHolder);
      mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
      for (Sensor registerListener : mSensorManager.getSensorList(1)) {
        mSensorManager.registerListener(this, registerListener, 1);
      }
      setTouchEventsEnabled(true);
    }

    public final void onDestroy() {
      super.onDestroy();
      stopDrawThread();
      if (mSakuraItems != null) {
        for (SakuraItem item : mSakuraItems) {
          item.recycle();
        }
      }
      mSakuraItems = null;
      if (!(mBitmap == null || mBitmap.isRecycled())) {
        mBitmap.recycle();
      }
      mBitmap = null;
      if (mMoonBitmap != null) {
        mMoonBitmap.recycle();
      }
      mMoonBitmap = null;
      System.gc();
      mSensorManager.unregisterListener(this);
      mPreference.unregisterOnSharedPreferenceChangeListener(this);
    }

    public final void onVisibilityChanged(boolean visible) {
      mVisible = visible;
      if (visible) {
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        for (Sensor registerListener : this.mSensorManager.getSensorList(1)) {
          this.mSensorManager.registerListener(this, registerListener, 1);
        }
        startDrawThread();
      } else {
        mSensorManager.unregisterListener(this);
        stopDrawThread();
      }
      if (!mParallax) {
        m22a(mParallaxPos);
      }
    }

    public final void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
      mSurfaceWidth = width;
      mSurfaceHeight = height;
      mSurfaceRect.right = width;
      mSurfaceRect.bottom = height;
      mOrientation = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
      ScreenSizeRatio.setSize(Math.max(mSurfaceWidth, mSurfaceHeight));
      synchronized (getSurfaceHolder()) {
        initBackgroundLocked();
      }
      m22a(0.0f);
    }

    public final void onSurfaceDestroyed(SurfaceHolder surfaceHolder) {
      super.onSurfaceDestroyed(surfaceHolder);
      stopDrawThread();
    }

    @Override
    public final void onOffsetsChanged(float xOffset, float yOffset,
                                       float xOffsetStep, float yOffsetStep,
                                       int xPixelOffset, int yPixelOffset) {
      if (mParallax) {
        if (mSeedEnabled) {
          float f5 = xOffset >= this.f27A ? xOffset * 10.0f : (-xOffset) * 10.0f;
          for (SeedItem seedItem : this.mSeedItems) {
            PointF pointF = seedItem.mSeedPos;
            pointF.x += f5;
          }
          this.f27A = xOffset;
        }
        m22a(xOffset);
      }
    }

    @Override
    public final void onTouchEvent(MotionEvent event) {
      if (event.getAction() == MotionEvent.ACTION_DOWN) {
        try {
          float x = event.getX();
          float y = event.getY();
          for (SakuraItem a : this.mSakuraItems) {
            a.onTouch(x, y);
          }
        } catch (Exception ignore) {
        }
      }
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public final void onSensorChanged(SensorEvent sensorEvent) {
      if (!this.mAccelerateEnabled) {
        this.f53s.x = 0.0f;
        this.f53s.y = 0.0f;
      } else if (this.mOrientation == 0) {
        this.f53s.x = -sensorEvent.values[0];
        this.f53s.y = sensorEvent.values[1];
      } else if (this.mOrientation == 1) {
        this.f53s.x = sensorEvent.values[1];
        this.f53s.y = -sensorEvent.values[0];
      } else if (this.mOrientation == 2) {
        this.f53s.x = sensorEvent.values[0];
        this.f53s.y = -sensorEvent.values[1];
      } else if (this.mOrientation == 3) {
        this.f53s.x = -sensorEvent.values[1];
        this.f53s.y = sensorEvent.values[0];
      }
    }

    /* renamed from: a */
    private void m22a(float f) {
      int i = (int) (((float) (ScreenSizeRatio.sDefaultSize - this.mSurfaceWidth)) * f);
      mBackgroundBitmapRect.left = i;
      mBackgroundBitmapRect.top = 0;
      mBackgroundBitmapRect.right = this.mSurfaceWidth + i;
      mBackgroundBitmapRect.bottom = this.mSurfaceHeight;
      mMoonPointF.x = (340.0f - ((float) i)) + (60.0f * f);
    }

    final void onDraw(Canvas canvas) {
      if (this.mBitmap != null) {
        canvas.drawBitmap(mBitmap, 0, 0, null);
      }
      if (mShowMoon && this.mMoonBitmap != null) {
        canvas.drawBitmap(mMoonBitmap, mMoonPointF.x, 70.0f, null);
      }
      if (mSakuraItems != null) {
        for (SakuraItem sakuraItem : this.mSakuraItems) {
          sakuraItem.f73a.x = this.f53s.x;
          sakuraItem.mo1a();
          sakuraItem.mo36b(mSurfaceWidth, mSurfaceHeight);
          sakuraItem.onDraw(canvas);
        }
      }
      if (mSeedEnabled && mSeedItems != null) {
        for (SeedItem seedItem : this.mSeedItems) {
          PointF pointF = seedItem.mSeedPos;
          pointF.x += seedItem.mNextPosDistance.x;
          pointF = seedItem.mSeedPos;
          pointF.y += seedItem.mNextPosDistance.y;
          if (seedItem.mSeedPos.x - 20.0f > ((float) this.mSurfaceWidth)) {
            seedItem.mSeedPos.x = -20.0f;
            seedItem.mNextPosDistance.x = (float) ((Math.random() * 4.0d) + 1.0d);
          } else if (seedItem.mSeedPos.x + 20.0f < 0.0f) {
            seedItem.mSeedPos.x = (float) (this.mSurfaceWidth + 20);
            seedItem.mNextPosDistance.x = (float) (-((Math.random() * 4.0d) + 1.0d));
          }
          if (seedItem.mSeedPos.y - 20.0f > ((float) this.mSurfaceHeight)) {
            seedItem.mSeedPos.y = -20.0f;
            seedItem.mNextPosDistance.y = (float) ((Math.random() * 2.0d) + 1.0d);
          } else if (seedItem.mSeedPos.y + 20.0f < 0.0f) {
            seedItem.mSeedPos.y = (float) (this.mSurfaceHeight + 20);
            seedItem.mNextPosDistance.y = (float) ((Math.random() * 2.0d) + 1.0d);
          }
          canvas.drawCircle(seedItem.mSeedPos.x, seedItem.mSeedPos.y, 2.0f, mSeedPaint);
        }
      }
    }

    private void startDrawThread() {
      if (this.mDrawThread == null) {
        this.mDrawThread = new DrawThread(this, getSurfaceHolder(), mFps);
        this.mDrawThread.mRunning = true;
        this.mDrawThread.start();
      }
    }

    private void stopDrawThread() {
      if (this.mDrawThread != null) {
        this.mDrawThread.mRunning = false;
        Thread thread = this.mDrawThread;
        this.mDrawThread = null;
        thread.interrupt();
      }
    }

    private void initSakuraItems() {
      this.mSakuraItems = new SakuraItem[mTotalFlowerCount];
      BitmapFactory.Options options = new BitmapFactory.Options();
      options.inDither = true;
      options.inScaled = false;
      options.inPreferredConfig = Bitmap.Config.RGB_565;
      Bitmap flower = BitmapFactory.decodeResource(getResources(), SakuraTheme.mSakuraFlower, options);
      Bitmap petal1 = BitmapFactory.decodeResource(getResources(), SakuraTheme.mSakuraPetal1, options);
      Bitmap petal2 = BitmapFactory.decodeResource(getResources(), SakuraTheme.mSakuraPatal2, options);
      for (int i = 0; i < mTotalFlowerCount; i++) {
        Bitmap bitmap = null;
        int i2 = 0;
        int i3 = 1;
        int i4;
        if (mLeafType.equals("Flower")) {
          i3 = (int) ((Math.random() * 20.0d) + 32.0d);
          bitmap = createScaledBitmap(flower, i3, i3, true);
          i4 = i3;
          i3 = 1;
          i2 = i4;
        } else {
          int nextInt;
          if (mLeafType.equals("Petal")) {
            nextInt = new Random().nextInt(2);
            if (nextInt == 0) {
              bitmap = petal1;
              i2 = 35;
              i3 = 45;
            } else if (nextInt == 1) {
              bitmap = petal2;
              i2 = 35;
              i3 = 45;
            }
          } else {
            nextInt = new Random().nextInt(3);
            if (nextInt == 0) {
              i3 = (int) ((Math.random() * 20.0d) + 32.0d);
              bitmap = createScaledBitmap(flower, i3, i3, true);
              i4 = i3;
              i3 = 1;
              i2 = i4;
            } else if (nextInt == 1) {
              bitmap = petal1;
              i2 = 35;
              i3 = 45;
            } else if (nextInt == 2) {
              bitmap = petal2;
              i2 = 35;
              i3 = 45;
            }
          }
        }
        Paint paint = new Paint();
        if (mSmoothEnabled) {
          paint.setAntiAlias(false);
          paint.setDither(true);
          paint.setFilterBitmap(true);
          paint.setAlpha((int) ((Math.random() * 80.0d) + 170.0d));
        } else {
          paint.setAntiAlias(false);
          paint.setDither(true);
          paint.setFilterBitmap(false);
          paint.setAlpha((int) ((Math.random() * 80.0d) + 170.0d));
        }
        SakuraItem sakuraItem = new SakuraItem(bitmap, i3, i2, i2);
        sakuraItem.setPaint(paint);
        sakuraItem.mo15c();
        sakuraItem.mo8b((float) (sakuraItem.bitmapWidth() / 2), (float) (sakuraItem.bitmapHeight() / 2));
        sakuraItem.mo9c((float) (this.mSurfaceWidth / 2), (float) (this.mSurfaceHeight / 2));
        sakuraItem.mo12h();
        sakuraItem.mo13a((int) (((Math.random() * 40.0d) + 40.0d) / ((double) this.mSpeed)));
        sakuraItem.mo17e();
        sakuraItem.f74b = this.mAcceletation;
        sakuraItem.mSpeed = this.mSpeed;
        sakuraItem.mFallingMode = this.mFallingMode;
        this.mSakuraItems[i] = sakuraItem;
      }
    }

    private void onSizeChanged() {
      for (SakuraItem a : this.mSakuraItems) {
        a.onSurfaceSizeChanged(this.mSurfaceWidth, this.mSurfaceHeight);
      }
    }

    private void initBgPaintAndSeedItem() {
      this.mSeedPaint.setColor(0xaaffffff);
      this.mSeedPaint.setStyle(Paint.Style.FILL);
      this.mSeedItems = new SeedItem[this.mSeedCount];
      for (int i = 0; i < this.mSeedCount; i++) {
        SeedItem seedItem = new SeedItem();
        seedItem.mSeedPos = new PointF(0.0f, 0.0f);
        seedItem.mNextPosDistance = new PointF(0.0f, 0.0f);
        this.mSeedItems[i] = seedItem;
      }
    }

    private void initSeeds() {
      for (SeedItem seedItem : mSeedItems) {
        long round = Math.round(Math.random());
        if (round == 0) {
          seedItem.mNextPosDistance.x = (float) ((Math.random() * 4.0d) + 1.0d);
          seedItem.mNextPosDistance.y = (float) ((Math.random() * 2.0d) + 1.0d);
          seedItem.mSeedPos.x = -20.0f;
          seedItem.mSeedPos.y = ((float) ((Math.random() * ((double) this.mSurfaceHeight)) / 2.0d)) - 20.0f;
        } else if (round == 1) {
          seedItem.mNextPosDistance.x = (float) (-((Math.random() * 4.0d) + 1.0d));
          seedItem.mNextPosDistance.y = (float) ((Math.random() * 2.0d) + 1.0d);
          seedItem.mSeedPos.x = (float) (this.mSurfaceWidth + 20);
          seedItem.mSeedPos.y = ((float) ((Math.random() * ((double) this.mSurfaceHeight)) / 2.0d)) - 20.0f;
        }
      }
    }
  }

}
