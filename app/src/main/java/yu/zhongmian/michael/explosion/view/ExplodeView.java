package yu.zhongmian.michael.explosion.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by zhoyu on 6/29/2016.
 */
public class ExplodeView extends ImageView {
    float scale = 1.0f;
    float speed = 0.05f;
    public interface OnRedrawListener{
        public void onRedraw();
    }
    OnRedrawListener listener;
    public ExplodeView(Context context) {
        super(context);
    }

    public ExplodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExplodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnRedrawListener(OnRedrawListener onRedrawListener)
    {
        listener = onRedrawListener;
    }
    public void startExplode()
    {
        if(scale > 0) {
            scale = 0.95f;
            postInvalidate();
        }
    }

    public void reset()
    {
        scale = 1.0f;
        speed = 0.05f;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        if(scale == 1) {
            super.onDraw(canvas);
        }
        else if(scale > 0)
        {
            canvas.save();
            canvas.translate(centerX, centerY);
            canvas.scale(scale, scale);
            canvas.translate(-centerX, -centerY);
            super.onDraw(canvas);
            canvas.restore();
            scale -= speed;
            speed += 0.00001f;
            postInvalidate();
        }
        else
        {
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStrokeWidth(5f);
            paint.setTextSize(100);
            canvas.drawText("Game Over", 30, 350, paint);
        }
        if(listener != null)
        {
            listener.onRedraw();
        }
    }
}
