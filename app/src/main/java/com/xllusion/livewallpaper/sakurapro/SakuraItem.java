package com.xllusion.livewallpaper.sakurapro;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.xllusion.game.engine.sprite.SpriteBlit;

public class SakuraItem extends SpriteBlit {
  /* renamed from: a */
  public PointF f73a = new PointF(0.0f, 0.0f);
  /* renamed from: b */
  public float f74b = 0.01f;
  /* renamed from: c */
  public float mSpeed = 1.0f;
  /* renamed from: d */
  public int mFallingMode = 1;
  /* renamed from: q */
  private PointF f77q = new PointF(0.0f, 0.0f);

  public SakuraItem(Bitmap bitmap, int i, int i2, int i3) {
    super(bitmap, i, i2, i3);
  }

  /* renamed from: a */
  public final void mo1a() {
    if (this.f13n.y <= this.mSpeed * -1.2f) {
      this.f13n.y = this.mSpeed * -1.2f;
    }
    if (this.f13n.y >= this.mSpeed * 1.2f) {
      this.f13n.y = this.mSpeed * 1.2f;
    }
    PointF pointF;
    if (this.mFallingMode == 0) {
      this.f14o.x = ((this.f73a.x * this.f74b) * 0.2f) + this.f77q.x;
      this.f14o.y = this.f74b + this.f77q.y;
      pointF = this.f13n;
      pointF.y *= 0.99f;
    } else {
      this.f14o.x = ((this.f73a.x * this.f74b) * 0.2f) + this.f77q.x;
      this.f14o.y = (this.f74b * 0.1f) + this.f77q.y;
      pointF = this.f13n;
      pointF.x *= 0.98f;
    }
    super.mo1a();
  }

  /* renamed from: a */
  public final boolean onTouch(float f, float f2) {
    float f3 = f - this.mCurrentPos.x;
    float f4 = f2 - this.mCurrentPos.y;
    if (Math.sqrt((double) ((f3 * f3) + (f4 * f4))) < 250.0d) {
      double cos;
      double atan2 = Math.atan2((double) f4, (double) f3);
      if (this.mFallingMode == 0) {
        cos = ((((double) this.mCurrentPos.x) + (Math.cos(atan2) * 250.0d)) - ((double) f)) * 0.009999999776482582d;
        atan2 = (((Math.sin(atan2) * 250.0d) + ((double) this.mCurrentPos.y)) - ((double) f2)) * 0.019999999552965164d;
      } else {
        cos = ((((double) this.mCurrentPos.x) + (Math.cos(atan2) * 250.0d)) - ((double) f)) * 0.019999999552965164d;
        atan2 = (((Math.sin(atan2) * 250.0d) + ((double) this.mCurrentPos.y)) - ((double) f2)) * 0.009999999776482582d;
      }
      if (atan2 >= 1.0d) {
        atan2 = 1.0d;
      }
      if (atan2 <= -0.1d) {
        atan2 = -0.1d;
      }
      if (atan2 > 0.0d) {
        this.f77q.y = (float) (0.009999999776482582d * atan2);
      }
      if (cos > 0.0d) {
        this.f77q.x = (float) (0.0020000000949949026d * cos);
      }
      if (cos < 0.0d) {
        this.f77q.x = (float) (0.0020000000949949026d * cos);
      }
      PointF pointF = this.f13n;
      pointF.x = (float) (((double) pointF.x) - cos);
      PointF pointF2 = this.f13n;
      pointF2.y = (float) (((double) pointF2.y) - atan2);
    }
    return true;
  }

  /* renamed from: a */
  public final void onSurfaceSizeChanged(int width, int height) {
    long round = Math.round(Math.random() * 1.0d);
    if (this.mFallingMode != 0) {
      this.f13n.x = ((float) ((Math.random() * 1.0d) - 0.5d)) * this.mSpeed;
      this.f13n.y = ((float) ((Math.random() * 1.0d) + 0.1d)) * this.mSpeed;
      this.mCurrentPos.x = (float) (Math.random() * ((double) width));
      this.mCurrentPos.y = (float) ((Math.random() * ((double) height)) - 100.0d);
    } else if (round == 0) {
      this.f13n.x = ((float) ((Math.random() * 1.0d) + 0.2d)) * this.mSpeed;
      this.f13n.y = ((float) ((Math.random() * 1.0d) + 0.1d)) * this.mSpeed;
      this.mCurrentPos.x = -30.0f;
      this.mCurrentPos.y = (float) ((Math.random() * ((double) height)) - 100.0d);
    } else if (round == 1) {
      this.f13n.x = ((float) (-((Math.random() * 1.0d) + 0.2d))) * this.mSpeed;
      this.f13n.y = ((float) ((Math.random() * 1.0d) + 0.1d)) * this.mSpeed;
      this.mCurrentPos.x = (float) (width + 30);
      this.mCurrentPos.y = (float) ((Math.random() * ((double) height)) - 100.0d);
    }
    mo2a((float) ((((double) this.mSpeed) * Math.random()) * 1.0d));
    mo14b((int) (Math.random() * ((double) mo16d())));
  }

  /* renamed from: b */
  public final void mo36b(int i, int i2) {
    if ((this.mCurrentPos.x - ((float) this.mBitmapWidth)) - 30.0f > ((float) i)) {
      if (this.mFallingMode == 0) {
        this.mCurrentPos.x = -30.0f;
        this.f13n.x = ((float) ((Math.random() * 1.0d) + 0.5d)) * this.mSpeed;
      } else {
        this.mCurrentPos.x = (float) (Math.random() * ((double) i));
        this.mCurrentPos.y = -30.0f;
        this.f13n.x = ((float) ((Math.random() * 2.0d) - 1.0d)) * this.mSpeed;
        this.f13n.y = ((float) ((Math.random() * 1.0d) + 0.1d)) * this.mSpeed;
      }
      this.f73a.x = 0.0f;
      this.f73a.y = 0.0f;
      mo14b((int) (Math.random() * ((double) mo16d())));
    } else if ((this.mCurrentPos.x + ((float) this.mBitmapWidth)) + 30.0f < 0.0f) {
      if (this.mFallingMode == 0) {
        this.mCurrentPos.x = (float) (i + 30);
        this.f13n.x = ((float) (-((Math.random() * 1.0d) + 0.5d))) * this.mSpeed;
      } else {
        this.mCurrentPos.x = (float) (Math.random() * ((double) i));
        this.mCurrentPos.y = -30.0f;
        this.f13n.x = ((float) ((Math.random() * 2.0d) - 1.0d)) * this.mSpeed;
        this.f13n.y = ((float) ((Math.random() * 1.0d) + 0.1d)) * this.mSpeed;
      }
      this.f73a.x = 0.0f;
      this.f73a.y = 0.0f;
      mo14b((int) (Math.random() * ((double) mo16d())));
    }
    if ((this.mCurrentPos.y - ((float) this.mBitmapHeight)) - 30.0f > ((float) i2)) {
      this.mCurrentPos.x = (float) (Math.random() * ((double) i));
      this.mCurrentPos.y = -30.0f;
      this.f73a.x = 0.0f;
      this.f73a.y = 0.0f;
      this.f13n.x = ((float) ((Math.random() * 3.0d) - 1.5d)) * this.mSpeed;
      this.f13n.y = ((float) ((Math.random() * 1.0d) + 0.1d)) * this.mSpeed;
      mo14b((int) (Math.random() * ((double) mo16d())));
    }
  }
}
