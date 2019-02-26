package com.xllusion.game.engine.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public class SpriteBlit extends C0000a {
  /* renamed from: a */
  private Bitmap[] f16a = null;
  /* renamed from: b */
  private int f17b = 0;
  /* renamed from: c */
  private int f18c = 0;
  /* renamed from: d */
  private boolean f19d = false;
  /* renamed from: q */
  private boolean f20q = false;
  /* renamed from: r */
  private boolean f21r = false;
  /* renamed from: s */
  private int f22s = 10;
  /* renamed from: t */
  private int f23t = 100;
  /* renamed from: u */
  private long f24u = 0;
  /* renamed from: v */
  private long f25v = 0;
  /* renamed from: w */
  private long f26w = 0;

  public SpriteBlit(Bitmap bitmap, int i, int i2, int i3) {
    super(0.0f, 0.0f, bitmap);
    this.f17b = i;
    this.mBitmapWidth = (int) (((float) i2) * this.f11l);
    this.mBitmapHeight = (int) (((float) i3) * this.f11l);
    int width = bitmap.getWidth() / i2;
    this.f16a = new Bitmap[i];
    for (int i4 = 0; i4 < this.f16a.length; i4++) {
      Point point = new Point();
      point.x = (i4 % width) * i2;
      point.y = (int) (Math.floor((double) (i4 / width)) * ((double) i3));
      this.f16a[i4] = Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap, point.x, point.y, i2, i3, null, true), this.mBitmapWidth, this.mBitmapHeight, true);
    }
  }

  /* renamed from: a */
  public final void mo3a(Bitmap bitmap) {
  }

  /* renamed from: a */
  public void mo1a() {
    super.mo1a();
    if (this.f21r) {
      this.f25v = System.currentTimeMillis();
      this.f26w = this.f25v - this.f24u;
      if (this.f26w >= ((long) this.f23t)) {
        this.f24u = System.currentTimeMillis();
        this.f25v = 0;
        this.f26w = 0;
        this.f18c++;
        if (this.f18c < this.f17b) {
          return;
        }
        if (this.f19d) {
          this.f18c = 0;
          return;
        }
        if (this.f20q) {
          this.f18c = 0;
        } else {
          this.f18c = this.f17b - 1;
        }
        this.f21r = false;
      }
    }
  }

  /* renamed from: a */
  public final void onDraw(Canvas canvas) {
    if (this.f10k) {
      canvas.save();
      if (this.f8i || this.f9j) {
        canvas.rotate(this.mRotateDegrees, this.mCurrentPos.x, this.mCurrentPos.y);
      }
      if (this.f16a[this.f18c] != null) {
        canvas.drawBitmap(this.f16a[this.f18c], this.mCurrentPos.x - this.f6g.x, this.mCurrentPos.y - this.f6g.y, this.mPaint);
        canvas.restore();
      }
    }
  }

  /* renamed from: b */
  public final void recycle() {
    if (this.f16a != null) {
      for (Bitmap bitmap : this.f16a) {
        if (bitmap != null) {
          bitmap.recycle();
        }
      }
    }
  }

  /* renamed from: a */
  public final void mo13a(int i) {
    this.f23t = i;
  }

  /* renamed from: b */
  public final void mo14b(int i) {
    this.f18c = i;
    if (this.f18c >= this.f17b) {
      this.f18c = this.f17b - 1;
    }
    if (this.f18c <= 0) {
      this.f18c = 0;
    }
  }

  /* renamed from: c */
  public final void mo15c() {
    this.f19d = true;
  }

  /* renamed from: d */
  public final int mo16d() {
    return this.f17b;
  }

  /* renamed from: e */
  public final void mo17e() {
    this.f24u = System.currentTimeMillis();
    this.f25v = 0;
    this.f26w = 0;
    this.f21r = true;
  }
}
