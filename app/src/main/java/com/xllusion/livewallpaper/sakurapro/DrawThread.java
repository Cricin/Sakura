package com.xllusion.livewallpaper.sakurapro;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/* renamed from: com.xllusion.livewallpaper.sakurapro.c */
public final class DrawThread extends Thread {
  boolean mRunning;
  private final SurfaceHolder mHolder;
  private int mDrawInterval;
  private long mLastDrawTime;
  private SakuraPro.SakuraEngine mEngine;

  DrawThread(SakuraPro.SakuraEngine engine,
             SurfaceHolder surfaceHolder,
             int fps) {
    this.mEngine = engine;
    this.mHolder = surfaceHolder;
    this.mLastDrawTime = System.currentTimeMillis();
    this.mDrawInterval = 1000 / fps;
  }

  public final void run() {
    Canvas canvas = null;
    long timeElapsedSinceLastDraw;
    while (mRunning) {
      if (!mHolder.getSurface().isValid()) continue;
      try {
        timeElapsedSinceLastDraw = System.currentTimeMillis() - this.mLastDrawTime;
        if (timeElapsedSinceLastDraw < ((long) this.mDrawInterval)) {
          Thread.sleep(((long) mDrawInterval) - timeElapsedSinceLastDraw);
        }
        this.mLastDrawTime = System.currentTimeMillis();
        canvas = mHolder.lockCanvas();
        synchronized (mHolder) {
          mEngine.onDraw(canvas);
          mHolder.unlockCanvasAndPost(canvas);
        }
      } catch (InterruptedException e) {
        break;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}