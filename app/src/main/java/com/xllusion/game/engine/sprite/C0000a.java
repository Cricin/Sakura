package com.xllusion.game.engine.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/* renamed from: com.xllusion.game.engine.sprite.a */
public class C0000a {
  /* renamed from: a */
  private PointF f0a = new PointF();
  /* renamed from: b */
  private PointF f1b = new PointF();
  /* renamed from: c */
  private float f2c = 0.0f;
  /* renamed from: d */
  private Bitmap mBitmap = null;
  /* renamed from: e */
  protected int mBitmapWidth = 0;
  /* renamed from: f */
  protected int mBitmapHeight = 0;
  /* renamed from: g */
  protected PointF f6g = new PointF();
  /* renamed from: h */
  protected float mRotateDegrees = 0.0f;
  /* renamed from: i */
  protected boolean f8i = false;
  /* renamed from: j */
  protected boolean f9j = false;
  /* renamed from: k */
  protected boolean f10k = true;
  /* renamed from: l */
  protected float f11l = 1.0f;
  /* renamed from: m */
  protected PointF mCurrentPos = new PointF();
  /* renamed from: n */
  protected PointF f13n = new PointF();
  /* renamed from: o */
  protected PointF f14o = new PointF();
  /* renamed from: p */
  protected Paint mPaint = null;

  public C0000a(float f, float f2, Bitmap bitmap) {
    mo3a(bitmap);
    this.f1b.x = 0.0f;
    this.f1b.y = 0.0f;
  }

  /* renamed from: a */
  public void mo3a(Bitmap bitmap) {
    if (this.mBitmap != null) {
      this.mBitmap.recycle();
    }
    this.mBitmap = Bitmap.createScaledBitmap(bitmap, (int) (((float) bitmap.getWidth()) * this.f11l), (int) (((float) bitmap.getHeight()) * this.f11l), true);
    this.mBitmapWidth = this.mBitmap.getWidth();
    this.mBitmapHeight = this.mBitmap.getHeight();
  }

  /* renamed from: a */
  public final void setPaint(Paint paint) {
    this.mPaint = paint;
  }

  /* renamed from: f */
  public final int bitmapWidth() {
    return this.mBitmapWidth;
  }

  /* renamed from: g */
  public final int bitmapHeight() {
    return this.mBitmapHeight;
  }

  /* renamed from: b */
  public final void mo8b(float f, float f2) {
    this.f6g.x = f;
    this.f6g.y = f2;
  }

  /* renamed from: c */
  public final void mo9c(float f, float f2) {
    this.f0a.x = f;
    this.f0a.y = f2;
  }

  /* renamed from: h */
  public final void mo12h() {
    this.f8i = true;
  }

  /* renamed from: a */
  public final void mo2a(float f) {
    this.f2c = f;
  }

  /* renamed from: a */
  public void mo1a() {
    PointF pointF = this.f13n;
    pointF.x += this.f14o.x;
    pointF = this.f13n;
    pointF.y += this.f14o.y;
    pointF = this.mCurrentPos;
    pointF.x += this.f13n.x;
    pointF = this.mCurrentPos;
    pointF.y += this.f13n.y;
    if (this.f8i) {
      this.mRotateDegrees += this.f2c;
    } else if (this.f9j) {
      this.mRotateDegrees = ((float) Math.toDegrees((double) ((float) Math.atan2((double) (this.mCurrentPos.y - this.f0a.y), (double) (this.mCurrentPos.x - this.f0a.x))))) - 90.0f;
    }
  }

  public boolean onTouch(float f, float f2) {
    float f3 = f - this.mCurrentPos.x;
    float f4 = f2 - this.mCurrentPos.y;
    if (Math.sqrt((double) ((f3 * f3) + (f4 * f4))) < ((double) this.mBitmapWidth)) {
      return true;
    }
    return false;
  }

  public void onDraw(Canvas canvas) {
    if (this.mBitmap != null && this.f10k) {
      canvas.save();
      if (this.f8i || this.f9j) {
        canvas.rotate(this.mRotateDegrees, this.mCurrentPos.x, this.mCurrentPos.y);
      }
      canvas.drawBitmap(this.mBitmap, mCurrentPos.x - f6g.x, mCurrentPos.y - f6g.y, mPaint);
      canvas.restore();
    }
  }

  public void recycle() {
    if (this.mBitmap != null) {
      this.mBitmap.recycle();
    }
  }
}
