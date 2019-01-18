package deprecated;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import org.jetbrains.anko.DimensionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
        mv = {1, 1, 13},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001oB\u0011\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005B\u001b\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB#\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0006\u0010B\u001a\u00020CJ\b\u0010D\u001a\u00020?H\u0002J\b\u0010E\u001a\u00020?H\u0002J\b\u0010F\u001a\u00020?H\u0002J\u0010\u0010G\u001a\u00020\n2\u0006\u0010H\u001a\u00020IH\u0002J\u0016\u0010J\u001a\u00020C2\u0006\u0010K\u001a\u00020I2\u0006\u0010L\u001a\u00020IJ\u0010\u0010M\u001a\u00020C2\u0006\u0010N\u001a\u00020OH\u0014J\b\u0010P\u001a\u00020CH\u0016J\u0010\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020TH\u0017J\u0016\u0010U\u001a\u00020C2\u0006\u0010H\u001a\u00020I2\u0006\u0010V\u001a\u00020%J\u000e\u0010W\u001a\u00020C2\u0006\u0010X\u001a\u00020\u000eJ\u000e\u0010W\u001a\u00020C2\u0006\u0010Y\u001a\u00020+J\u0015\u0010Z\u001a\u00020%*\u00020%2\u0006\u0010Z\u001a\u00020IH\u0082\u0002J\u001c\u0010[\u001a\u00020%*\u00020?2\u0006\u0010\\\u001a\u00020%2\u0006\u0010]\u001a\u00020?H\u0002J\u001c\u0010^\u001a\u00020I*\u00020?2\u0006\u0010_\u001a\u00020I2\u0006\u0010]\u001a\u00020?H\u0002J\u001c\u0010`\u001a\u00020I*\u00020?2\u0006\u0010a\u001a\u00020I2\u0006\u0010]\u001a\u00020?H\u0002J\f\u0010b\u001a\u00020%*\u00020%H\u0002J\f\u0010c\u001a\u00020I*\u000201H\u0002J!\u0010d\u001a\u00020C*\u0002012\u0012\u0010e\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020C0fH\u0082\bJ\f\u0010g\u001a\u00020R*\u00020%H\u0002J)\u0010h\u001a\u00020C*\u0002012\u0006\u0010]\u001a\u00020?2\u0012\u0010e\u001a\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020C0fH\u0082\bJ\u0015\u0010i\u001a\u00020%*\u00020%2\u0006\u0010j\u001a\u00020%H\u0082\u0002J\u0015\u0010k\u001a\u00020%*\u00020%2\u0006\u0010j\u001a\u00020%H\u0082\u0002J\u0015\u0010l\u001a\u00020?*\u00020?2\u0006\u0010H\u001a\u00020\nH\u0082\u0002J\f\u0010m\u001a\u00020n*\u00020?H\u0002R\u000e\u0010\f\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R+\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0011\u0010\"\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b#\u0010\u0015R\u0011\u0010$\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010*\u001a\u00020+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00102\u001a\b\u0012\u0004\u0012\u00020103X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u00104\u001a\u000205¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0011\u00108\u001a\u000205¢\u0006\b\n\u0000\u001a\u0004\b9\u00107R\u0011\u0010:\u001a\u00020;¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u0014\u0010>\u001a\b\u0012\u0004\u0012\u00020?03X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020AX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006p"},
        d2 = {"Lcom/li/myapplication/RegionImageView;", "Landroid/view/View;", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "SIZE_SUSPENSION", "bitmap", "Landroid/graphics/Bitmap;", "getBitmap", "()Landroid/graphics/Bitmap;", "setBitmap", "(Landroid/graphics/Bitmap;)V", "bitmapHeight", "getBitmapHeight", "()I", "bitmapRegion", "getBitmapRegion", "setBitmapRegion", "<set-?>", "Landroid/graphics/BitmapRegionDecoder;", "bitmapRegionDecoder", "getBitmapRegionDecoder", "()Landroid/graphics/BitmapRegionDecoder;", "setBitmapRegionDecoder", "(Landroid/graphics/BitmapRegionDecoder;)V", "bitmapRegionDecoder$delegate", "Lkotlin/properties/ReadWriteProperty;", "bitmapWidth", "getBitmapWidth", "focusPoint", "Landroid/graphics/PointF;", "getFocusPoint", "()Landroid/graphics/PointF;", "gestureDetector", "Landroid/view/GestureDetector;", "imagePath", "", "getImagePath", "()Ljava/lang/String;", "setImagePath", "(Ljava/lang/String;)V", "matrixDraw", "Landroid/graphics/Matrix;", "matrixPool", "Lcom/li/myapplication/RegionImageView$ObjectPool;", "optionRegion", "Landroid/graphics/BitmapFactory$Options;", "getOptionRegion", "()Landroid/graphics/BitmapFactory$Options;", "options", "getOptions", "paint", "Landroid/graphics/Paint;", "getPaint", "()Landroid/graphics/Paint;", "rectFPool", "Landroid/graphics/RectF;", "scaleGestureDetector", "Landroid/view/ScaleGestureDetector;", "displayHDImage", "", "getDisplayRegion", "getDisplayRegionRaw", "getSuspensionRect", "getUsableInSampleSize", "scale", "", "moveImageBy", "dx", "dy", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onGlobalLayout", "onTouchEvent", "", "event", "Landroid/view/MotionEvent;", "scaleImage", "focus", "setImage", "bm", "path", "div", "getMappingPoint", "p", "src", "getMappingX", "x", "getMappingY", "y", "getRawPosition", "getScale", "invert", "predicate", "Lkotlin/Function1;", "isInBitmap", "mapRect", "minus", "point", "plus", "times", "toRect", "Landroid/graphics/Rect;", "ObjectPool", "app_debug"}
)
public final class RImageView extends View implements OnGlobalLayoutListener {
    // $FF: synthetic field
    static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(RImageView.class), "bitmapRegionDecoder", "getBitmapRegionDecoder()Landroid/graphics/BitmapRegionDecoder;"))};
    private final int SIZE_SUSPENSION = 200;
    @Nullable
    private Bitmap bitmap;
    @Nullable
    private Bitmap bitmapRegion;
    @NotNull
    private final ReadWriteProperty bitmapRegionDecoder$delegate;
    @NotNull
    private final Options optionRegion;
    @NotNull
    private final Options options;
    @NotNull
    private String imagePath;
    @NotNull
    private final Paint paint;
    @NotNull
    private final PointF focusPoint;
    private final ScaleGestureDetector scaleGestureDetector;
    private final GestureDetector gestureDetector;
    private final RImageView.ObjectPool rectFPool;
    private final RImageView.ObjectPool matrixPool;
    private final Matrix matrixDraw;
    private HashMap _$_findViewCache;

    @Nullable
    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    public final void setBitmap(@Nullable Bitmap var1) {
        this.bitmap = var1;
    }

    @Nullable
    public final Bitmap getBitmapRegion() {
        return this.bitmapRegion;
    }

    public final void setBitmapRegion(@Nullable Bitmap var1) {
        this.bitmapRegion = var1;
    }

    @NotNull
    public final BitmapRegionDecoder getBitmapRegionDecoder() {
        return (BitmapRegionDecoder)this.bitmapRegionDecoder$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public final void setBitmapRegionDecoder(@NotNull BitmapRegionDecoder var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.bitmapRegionDecoder$delegate.setValue(this, $$delegatedProperties[0], var1);
    }

    @NotNull
    public final Options getOptionRegion() {
        return this.optionRegion;
    }

    @NotNull
    public final Options getOptions() {
        return this.options;
    }

    @NotNull
    public final String getImagePath() {
        return this.imagePath;
    }

    public final void setImagePath(@NotNull String var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.imagePath = var1;
    }

    @NotNull
    public final Paint getPaint() {
        return this.paint;
    }

    @NotNull
    public final PointF getFocusPoint() {
        return this.focusPoint;
    }

    public final int getBitmapHeight() {
        return this.bitmap != null ? this.bitmap.getHeight() : 0;
    }

    public final int getBitmapWidth() {
        return this.bitmap != null ? this.bitmap.getWidth() : 0;
    }

    public final void displayHDImage() {
        if (this.options.inSampleSize != 1) {
            Options var1 = this.optionRegion;
            var1.inSampleSize = this.getUsableInSampleSize((float)this.options.inSampleSize / this.getScale(this.matrixDraw));
            if (this.optionRegion.inSampleSize < this.options.inSampleSize) {
                RectF displayRegionRaw = this.getDisplayRegionRaw();
                this.bitmapRegion = this.getBitmapRegionDecoder().decodeRegion(this.toRect(this.times(displayRegionRaw, this.options.inSampleSize)), this.optionRegion);
                this.rectFPool.restore(displayRegionRaw);
                this.invalidate();
            }
        }
    }

    private final int getUsableInSampleSize(float scale) {
        int power;
        for(power = 1; (float)(power * 2) < scale; power *= 2) {
            ;
        }

        return power;
    }

    public final void moveImageBy(float dx, float dy) {
        this.matrixDraw.postTranslate(dx, dy);
        this.invalidate();
    }

    public final void scaleImage(float scale, @NotNull PointF focus) {
        Intrinsics.checkParameterIsNotNull(focus, "focus");
        this.focusPoint.set(focus);
        Matrix var3 = this.matrixDraw;
        float oldScale = this.getScale(var3);
        var3.postScale(scale / oldScale, scale / oldScale, focus.x, focus.y);
        this.invalidate();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(@NotNull MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        super.onTouchEvent(event);
        this.bitmapRegion = (Bitmap)null;
        this.gestureDetector.onTouchEvent(event);
        this.scaleGestureDetector.onTouchEvent(event);
        if (event.getActionMasked() == 1) {
            this.displayHDImage();
        }

        return true;
    }

    protected void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        Bitmap var10000 = this.bitmap;
        if (this.bitmap != null) {
            Bitmap var2 = var10000;
            canvas.drawBitmap(var2, this.matrixDraw, this.paint);
            var10000 = this.bitmapRegion;
            RectF displayRegionRaw;
            if (this.bitmapRegion != null) {
                Bitmap var4 = var10000;
                displayRegionRaw = this.getDisplayRegion();
                canvas.drawBitmap(var4, (Rect)null, displayRegionRaw, this.paint);
                this.rectFPool.restore(displayRegionRaw);
            }

            RectF suspensionRect = this.getSuspensionRect();
            canvas.drawBitmap(var2, (Rect)null, suspensionRect, this.paint);
            Object var22 = this.rectFPool.get();
            RectF $receiver = (RectF)var22;
            $receiver.set(0.0F, 0.0F, (float)this.getBitmapWidth(), (float)this.getBitmapHeight());
            RectF rectRawBitmap = (RectF)var22;
            displayRegionRaw = this.getDisplayRegionRaw();
            Matrix matrixRowToSus = (Matrix)this.matrixPool.get();
            matrixRowToSus.setRectToRect(rectRawBitmap, suspensionRect, ScaleToFit.FILL);
            RectF dest$iv = (RectF)access$getRectFPool$p(this).get();
            matrixRowToSus.mapRect(dest$iv, displayRegionRaw);
            Paint var12 = this.paint;
            var12.setStyle(Style.STROKE);
            var12.setColor(-16711936);
            var12.setStrokeWidth(5.0F);
            canvas.drawRect(dest$iv, var12);
            access$getRectFPool$p(this).restore();
            this.rectFPool.restore(suspensionRect, displayRegionRaw, rectRawBitmap);
        }

    }

    private final RectF getDisplayRegionRaw() {
        Object var1 = this.rectFPool.get();
        RectF $receiver = (RectF)var1;
        Object var5 = access$getMatrixPool$p(this).get();
        Matrix $receiver$iv = (Matrix)var5;
        $receiver$iv.invert($receiver$iv);
        Matrix invert$iv = (Matrix)var5;
        RectF displayRegion = this.getDisplayRegion();
        invert$iv.mapRect($receiver, displayRegion);
        this.rectFPool.restore(displayRegion);
        access$getMatrixPool$p(this).restore(invert$iv);
        return (RectF)var1;
    }

    private final RectF getDisplayRegion() {
        Object var2 = this.rectFPool.get();
        RectF displayRegion = (RectF)var2;
        displayRegion.set(0.0F, 0.0F, (float)this.getWidth(), (float)this.getHeight());
        RectF rectScreen = (RectF)var2;
        Object var8 = this.rectFPool.get();
        RectF $receiver = (RectF)var8;
        $receiver.set(0.0F, 0.0F, (float)this.getBitmapWidth(), (float)this.getBitmapHeight());
        this.matrixDraw.mapRect($receiver, $receiver);
        this.rectFPool.restore();
        RectF rectImageDisplay = (RectF)var8;
        Object var9 = this.rectFPool.get();
        $receiver = (RectF)var9;
        $receiver.setIntersect(rectScreen, rectImageDisplay);
        displayRegion = (RectF)var9;
        return displayRegion;
    }

    private final RectF getSuspensionRect() {
        if (this.getBitmapWidth() != 0 && this.getBitmapHeight() != 0) {
            RectF var10000;
            Object var1;
            float var10001;
            RectF $receiver;
            float var10003;
            int value$iv;
            float var5;
            int var7;
            float var8;
            int var9;
            float var12;
            if (this.getBitmapWidth() > this.getBitmapHeight()) {
                var1 = this.rectFPool.get();
                $receiver = (RectF)var1;
                var10001 = (float)this.getWidth();
                value$iv = this.SIZE_SUSPENSION;
                var5 = var10001;
                var7 = DimensionsKt.dip(this.getContext(), value$iv);
                var10001 = var5 - (float)var7;
                var10003 = (float)this.getWidth();
                value$iv = this.SIZE_SUSPENSION;
                var8 = var10003;
                var12 = 0.0F;
                var5 = var10001;
                var9 = DimensionsKt.dip(this.getContext(), value$iv);
                $receiver.set(var5, var12, var8, (float)var9 / (float)this.getBitmapWidth() * (float)this.getBitmapHeight());
                var10000 = (RectF)var1;
            } else {
                var1 = this.rectFPool.get();
                $receiver = (RectF)var1;
                var10001 = (float)this.getWidth();
                value$iv = this.SIZE_SUSPENSION;
                var5 = var10001;
                var7 = DimensionsKt.dip(this.getContext(), value$iv);
                var10001 = var5 - (float)var7 / (float)this.getBitmapHeight() * (float)this.getBitmapWidth();
                var10003 = (float)this.getWidth();
                value$iv = this.SIZE_SUSPENSION;
                var8 = var10003;
                var12 = 0.0F;
                var5 = var10001;
                var9 = DimensionsKt.dip(this.getContext(), value$iv);
                $receiver.set(var5, var12, var8, (float)var9);
                var10000 = (RectF)var1;
            }

            return var10000;
        } else {
            return (RectF)this.rectFPool.get();
        }
    }

    private final PointF getMappingPoint(@NotNull RectF $receiver, PointF p, RectF src) {
        return new PointF(this.getMappingX($receiver, p.x, src), this.getMappingY($receiver, p.y, src));
    }

    private final float getMappingX(@NotNull RectF $receiver, float x, RectF src) {
        return (x - src.left) / src.width() * $receiver.width() + $receiver.left;
    }

    private final float getMappingY(@NotNull RectF $receiver, float y, RectF src) {
        return (y - src.top) / src.height() * $receiver.height() + $receiver.top;
    }

    private final float getScale(@NotNull Matrix $receiver) {
        float[] values = new float[9];
        $receiver.getValues(values);
        return values[0];
    }

    private final PointF getRawPosition(@NotNull PointF $receiver) {
        PointF var2 = new PointF(-1.0F, -1.0F);
        Object var6 = access$getMatrixPool$p(this).get();
        Matrix $receiver$iv = (Matrix)var6;
        $receiver$iv.invert($receiver$iv);
        Matrix invert$iv = (Matrix)var6;
        float[] dest = new float[2];
        invert$iv.mapPoints(dest, new float[]{$receiver.x, $receiver.y});
        var2.x = dest[0];
        var2.y = dest[1];
        access$getMatrixPool$p(this).restore(invert$iv);
        return var2;
    }

    private final void mapRect(@NotNull Matrix $receiver, RectF src, Function1 predicate) {
        RectF dest = (RectF)access$getRectFPool$p(this).get();
        $receiver.mapRect(dest, src);
        predicate.invoke(dest);
        access$getRectFPool$p(this).restore();
    }

    private final void invert(@NotNull Matrix $receiver, Function1 predicate) {
        Object var5 = access$getMatrixPool$p(this).get();
        Matrix matrix = (Matrix)var5;
        matrix.invert(matrix);
        Matrix invert = (Matrix)var5;
        predicate.invoke(invert);
        access$getMatrixPool$p(this).restore(invert);
    }

    public final void setImage(@NotNull Bitmap bm) throws IOException {
        Intrinsics.checkParameterIsNotNull(bm, "bm");
        this.bitmap = bm;
        ByteArrayOutputStream var3 = new ByteArrayOutputStream();
        bm.compress(CompressFormat.PNG, 100, (OutputStream)var3);
        ByteArrayInputStream baos = new ByteArrayInputStream(var3.toByteArray());
        BitmapRegionDecoder var10001 = BitmapRegionDecoder.newInstance((InputStream)baos, false);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "BitmapRegionDecoder.newInstance(baos, false)");
        this.setBitmapRegionDecoder(var10001);
    }

    public void onGlobalLayout() {
        Options var1 = this.options;
        Bitmap var3 = this.bitmap;
        if (var3 == null) {
            var1.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(this.imagePath, var1);
            var1.inSampleSize = this.getUsableInSampleSize((float)var1.outWidth / (float)this.getMeasuredWidth());
            var1.inJustDecodeBounds = false;
            this.bitmap = BitmapFactory.decodeFile(this.imagePath, var1);
        }

        float scaleFactor = (float)this.getMeasuredWidth() / (float)this.getBitmapWidth();
        this.matrixDraw.reset();
        this.matrixDraw.postScale(scaleFactor, scaleFactor);
        this.matrixDraw.postTranslate(0.0F, (float)this.getMeasuredHeight() / 2.0F - (float)this.getBitmapHeight() * scaleFactor / (float)2);
        this.invalidate();
        this.getViewTreeObserver().removeGlobalOnLayoutListener((OnGlobalLayoutListener)this);
    }

    private final Rect toRect(@NotNull RectF $receiver) {
        return new Rect((int)$receiver.left, (int)$receiver.top, (int)$receiver.right, (int)$receiver.bottom);
    }

    private final boolean isInBitmap(@NotNull PointF $receiver) {
        byte var2 = 0;
        boolean var10000;
        if (RangesKt.intRangeContains((ClosedRange)(new IntRange(var2, this.getBitmapWidth())), $receiver.x)) {
            var2 = 0;
            if (RangesKt.intRangeContains((ClosedRange)(new IntRange(var2, this.getBitmapHeight())), $receiver.y)) {
                var10000 = true;
                return var10000;
            }
        }

        var10000 = false;
        return var10000;
    }

    private final PointF plus(@NotNull PointF $receiver, PointF point) {
        Intrinsics.checkParameterIsNotNull($receiver, "receiver$0");
        PointF var3 = new PointF();
        var3.x = $receiver.x + point.x;
        var3.y = $receiver.y + point.y;
        return var3;
    }

    private final PointF minus(@NotNull PointF $receiver, PointF point) {
        Intrinsics.checkParameterIsNotNull($receiver, "receiver$0");
        PointF var3 = new PointF();
        var3.x = $receiver.x - point.x;
        var3.y = $receiver.y - point.y;
        return var3;
    }

    private final PointF div(@NotNull PointF $receiver, float div) {
        Intrinsics.checkParameterIsNotNull($receiver, "receiver$0");
        PointF var3 = new PointF();
        var3.x = $receiver.x / div;
        var3.y = $receiver.y / div;
        return var3;
    }

    private final RectF times(@NotNull RectF $receiver, int scale) {
        Intrinsics.checkParameterIsNotNull($receiver, "receiver$0");
        $receiver.left *= (float)scale;
        $receiver.right *= (float)scale;
        $receiver.top *= (float)scale;
        $receiver.bottom *= (float)scale;
        return $receiver;
    }

    public RImageView(@Nullable Context context) {
        super(context);
        this.bitmapRegionDecoder$delegate = Delegates.INSTANCE.notNull();
        this.optionRegion = new Options();
        Options var2 = new Options();
        var2.inPreferredConfig = Config.RGB_565;
        this.options = var2;
        this.imagePath = "";
        this.paint = new Paint();
        this.focusPoint = new PointF();
        this.rectFPool = (RImageView.ObjectPool)(new RImageView.ObjectPool() {
            @NotNull
            protected RectF reset(@NotNull RectF t) {
                Intrinsics.checkParameterIsNotNull(t, "t");
                t.setEmpty();
                return t;
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object reset(Object var1) {
                return this.reset((RectF)var1);
            }

            @NotNull
            protected RectF generateAnObject() {
                return new RectF();
            }

        });
        this.matrixPool = (RImageView.ObjectPool)(new RImageView.ObjectPool() {
            @NotNull
            protected Matrix reset(@NotNull Matrix t) {
                Intrinsics.checkParameterIsNotNull(t, "t");
                t.reset();
                return t;
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object reset(Object var1) {
                return this.reset((Matrix)var1);
            }

            @NotNull
            protected Matrix generateAnObject() {
                return new Matrix();
            }

        });
        this.matrixDraw = (Matrix)this.matrixPool.get();
        this.scaleGestureDetector = new ScaleGestureDetector(this.getContext(), (OnScaleGestureListener)(new SimpleOnScaleGestureListener() {
            private float scaleFactorOld = 1.0F;

            public boolean onScaleBegin(@NotNull ScaleGestureDetector detector) {
                Intrinsics.checkParameterIsNotNull(detector, "detector");
                RImageView.this.getFocusPoint().set(detector.getFocusX(), detector.getFocusY());
                PointF rawPosition = RImageView.this.getRawPosition(RImageView.this.getFocusPoint());
                if (!RImageView.this.isInBitmap(rawPosition)) {
                    RImageView.this.getFocusPoint().set(1.0F, 1.0F);
                    return false;
                } else {
                    RImageView.this.getFocusPoint().set(detector.getFocusX(), detector.getFocusY());
                    this.scaleFactorOld = RImageView.this.getScale(RImageView.this.matrixDraw);
                    return true;
                }
            }

            public boolean onScale(@NotNull ScaleGestureDetector detector) {
                Intrinsics.checkParameterIsNotNull(detector, "detector");
                RImageView.this.scaleImage(this.scaleFactorOld * detector.getScaleFactor(), RImageView.this.getFocusPoint());
                return false;
            }
        }));
        this.gestureDetector = new GestureDetector(this.getContext(), (OnGestureListener)(new SimpleOnGestureListener() {
            public boolean onScroll(@NotNull MotionEvent e1, @NotNull MotionEvent e2, float distanceX, float distanceY) {
                Intrinsics.checkParameterIsNotNull(e1, "e1");
                Intrinsics.checkParameterIsNotNull(e2, "e2");
                RImageView.this.moveImageBy(-distanceX, -distanceY);
                return true;
            }
        }));
        this.getViewTreeObserver().addOnGlobalLayoutListener((OnGlobalLayoutListener)this);
    }

    public RImageView(@Nullable Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.bitmapRegionDecoder$delegate = Delegates.INSTANCE.notNull();
        this.optionRegion = new Options();
        Options var3 = new Options();
        var3.inPreferredConfig = Config.RGB_565;
        this.options = var3;
        this.imagePath = "";
        this.paint = new Paint();
        this.focusPoint = new PointF();
        this.rectFPool = (RImageView.ObjectPool)(new RImageView.ObjectPool() {
            @NotNull
            protected RectF reset(@NotNull RectF t) {
                Intrinsics.checkParameterIsNotNull(t, "t");
                t.setEmpty();
                return t;
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object reset(Object var1) {
                return this.reset((RectF)var1);
            }

            @NotNull
            protected RectF generateAnObject() {
                return new RectF();
            }

        });
        this.matrixPool = (RImageView.ObjectPool)(new RImageView.ObjectPool() {
            @NotNull
            protected Matrix reset(@NotNull Matrix t) {
                Intrinsics.checkParameterIsNotNull(t, "t");
                t.reset();
                return t;
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object reset(Object var1) {
                return this.reset((Matrix)var1);
            }

            @NotNull
            protected Matrix generateAnObject() {
                return new Matrix();
            }

        });
        this.matrixDraw = (Matrix)this.matrixPool.get();
        this.scaleGestureDetector = new ScaleGestureDetector(this.getContext(), (OnScaleGestureListener)(new SimpleOnScaleGestureListener() {
            private float scaleFactorOld = 1.0F;

            public boolean onScaleBegin(@NotNull ScaleGestureDetector detector) {
                Intrinsics.checkParameterIsNotNull(detector, "detector");
                RImageView.this.getFocusPoint().set(detector.getFocusX(), detector.getFocusY());
                PointF rawPosition = RImageView.this.getRawPosition(RImageView.this.getFocusPoint());
                if (!RImageView.this.isInBitmap(rawPosition)) {
                    RImageView.this.getFocusPoint().set(1.0F, 1.0F);
                    return false;
                } else {
                    RImageView.this.getFocusPoint().set(detector.getFocusX(), detector.getFocusY());
                    this.scaleFactorOld = RImageView.this.getScale(RImageView.this.matrixDraw);
                    return true;
                }
            }

            public boolean onScale(@NotNull ScaleGestureDetector detector) {
                Intrinsics.checkParameterIsNotNull(detector, "detector");
                RImageView.this.scaleImage(this.scaleFactorOld * detector.getScaleFactor(), RImageView.this.getFocusPoint());
                return false;
            }
        }));
        this.gestureDetector = new GestureDetector(this.getContext(), (OnGestureListener)(new SimpleOnGestureListener() {
            public boolean onScroll(@NotNull MotionEvent e1, @NotNull MotionEvent e2, float distanceX, float distanceY) {
                Intrinsics.checkParameterIsNotNull(e1, "e1");
                Intrinsics.checkParameterIsNotNull(e2, "e2");
                RImageView.this.moveImageBy(-distanceX, -distanceY);
                return true;
            }
        }));
        this.getViewTreeObserver().addOnGlobalLayoutListener((OnGlobalLayoutListener)this);
    }

    public RImageView(@Nullable Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.bitmapRegionDecoder$delegate = Delegates.INSTANCE.notNull();
        this.optionRegion = new Options();
        Options var4 = new Options();
        var4.inPreferredConfig = Config.RGB_565;
        this.options = var4;
        this.imagePath = "";
        this.paint = new Paint();
        this.focusPoint = new PointF();
        this.rectFPool = (RImageView.ObjectPool)(new RImageView.ObjectPool() {
            @NotNull
            protected RectF reset(@NotNull RectF t) {
                Intrinsics.checkParameterIsNotNull(t, "t");
                t.setEmpty();
                return t;
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object reset(Object var1) {
                return this.reset((RectF)var1);
            }

            @NotNull
            protected RectF generateAnObject() {
                return new RectF();
            }

        });
        this.matrixPool = (RImageView.ObjectPool)(new RImageView.ObjectPool() {
            @NotNull
            protected Matrix reset(@NotNull Matrix t) {
                Intrinsics.checkParameterIsNotNull(t, "t");
                t.reset();
                return t;
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object reset(Object var1) {
                return this.reset((Matrix)var1);
            }

            @NotNull
            protected Matrix generateAnObject() {
                return new Matrix();
            }

        });
        this.matrixDraw = (Matrix)this.matrixPool.get();
        this.scaleGestureDetector = new ScaleGestureDetector(this.getContext(), (OnScaleGestureListener)(new SimpleOnScaleGestureListener() {
            private float scaleFactorOld = 1.0F;

            public boolean onScaleBegin(@NotNull ScaleGestureDetector detector) {
                Intrinsics.checkParameterIsNotNull(detector, "detector");
                RImageView.this.getFocusPoint().set(detector.getFocusX(), detector.getFocusY());
                PointF rawPosition = RImageView.this.getRawPosition(RImageView.this.getFocusPoint());
                if (!RImageView.this.isInBitmap(rawPosition)) {
                    RImageView.this.getFocusPoint().set(1.0F, 1.0F);
                    return false;
                } else {
                    RImageView.this.getFocusPoint().set(detector.getFocusX(), detector.getFocusY());
                    this.scaleFactorOld = RImageView.this.getScale(RImageView.this.matrixDraw);
                    return true;
                }
            }

            public boolean onScale(@NotNull ScaleGestureDetector detector) {
                Intrinsics.checkParameterIsNotNull(detector, "detector");
                RImageView.this.scaleImage(this.scaleFactorOld * detector.getScaleFactor(), RImageView.this.getFocusPoint());
                return false;
            }
        }));
        this.gestureDetector = new GestureDetector(this.getContext(), (OnGestureListener)(new SimpleOnGestureListener() {
            public boolean onScroll(@NotNull MotionEvent e1, @NotNull MotionEvent e2, float distanceX, float distanceY) {
                Intrinsics.checkParameterIsNotNull(e1, "e1");
                Intrinsics.checkParameterIsNotNull(e2, "e2");
                RImageView.this.moveImageBy(-distanceX, -distanceY);
                return true;
            }
        }));
        this.getViewTreeObserver().addOnGlobalLayoutListener((OnGlobalLayoutListener)this);
    }

    // $FF: synthetic method
    @NotNull
    public static final RImageView.ObjectPool access$getRectFPool$p(RImageView $this) {
        return $this.rectFPool;
    }

    // $FF: synthetic method
    @NotNull
    public static final RImageView.ObjectPool access$getMatrixPool$p(RImageView $this) {
        return $this.matrixPool;
    }

    public View _$_findCachedViewById(int var1) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }

        View var2 = (View)this._$_findViewCache.get(var1);
        if (var2 == null) {
            var2 = this.findViewById(var1);
            this._$_findViewCache.put(var1, var2);
        }

        return var2;
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }

    }

    @Metadata(
            mv = {1, 1, 13},
            bv = {1, 0, 3},
            k = 1,
            d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\u000e\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u0010\u001a\u00028\u0000¢\u0006\u0002\u0010\u000fJ\u0015\u0010\u0011\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0013J\u001f\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0016\"\u00028\u0000¢\u0006\u0002\u0010\u0017R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u0005¨\u0006\u0018"},
            d2 = {"Lcom/li/myapplication/RegionImageView$ObjectPool;", "T", "", "capacity", "", "(I)V", "objects", "Ljava/util/Queue;", "getObjects", "()Ljava/util/Queue;", "size", "getSize", "()I", "setSize", "generateAnObject", "()Ljava/lang/Object;", "get", "reset", "t", "(Ljava/lang/Object;)Ljava/lang/Object;", "restore", "", "", "([Ljava/lang/Object;)V", "app_debug"}
    )
    public abstract static class ObjectPool {
        @NotNull
        private final Queue objects;
        private int size;

        @NotNull
        public final Queue getObjects() {
            return this.objects;
        }

        public final int getSize() {
            return this.size;
        }

        public final void setSize(int var1) {
            this.size = var1;
        }

        public final Object get() {
            return this.objects.size() == 0 ? this.generateAnObject() : this.reset(this.objects.poll());
        }

        public final void restore(@NotNull Object... t) {
            Intrinsics.checkParameterIsNotNull(t, "t");
            Object[] var4 = t;
            int var5 = t.length;

            for(int var3 = 0; var3 < var5; ++var3) {
                Object e = var4[var3];
                if (this.objects.size() < this.size) {
                    this.objects.offer(e);
                }
            }

        }

        protected abstract Object reset(Object var1);

        protected abstract Object generateAnObject();

        public ObjectPool() {
            this.size = 16;
            this.objects = (Queue)(new LinkedList());
        }
    }
}

