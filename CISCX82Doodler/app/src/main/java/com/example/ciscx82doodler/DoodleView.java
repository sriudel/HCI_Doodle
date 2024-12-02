// Step 1: Create Custom View Class - DoodleView.java
package com.example.ciscx82doodler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DoodleView extends View {
    private Paint paint;
    private ArrayList<PathWithPaint> paths;
    private Path currentPath;

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLACK); // Default color
        paint.setStrokeWidth(20);    // Increased default brush size
        paint.setStyle(Paint.Style.STROKE);
        paint.setAlpha(255);         // Default opacity
        paint.setAntiAlias(true);

        paths = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Draw all previous paths
        for (PathWithPaint pwp : paths) {
            canvas.drawPath(pwp.path, pwp.paint);
        }
        // Draw the current path
        if (currentPath != null) {
            canvas.drawPath(currentPath, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentPath = new Path();
                currentPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                if (currentPath != null) {
                    currentPath.lineTo(x, y);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (currentPath != null) {
                    paths.add(new PathWithPaint(currentPath, new Paint(paint)));
                    currentPath = null;
                }
                break;
        }
        invalidate();
        return true;
    }

    public void setBrushSize(float size) {
        paint.setStrokeWidth(size);
    }

    public void setBrushColor(int color) {
        paint.setColor(color);
    }

    public void setOpacity(int opacity) {
        paint.setAlpha(opacity);
    }

    public void clearCanvas() {
        paths.clear();
        invalidate();
    }

    private class PathWithPaint {
        Path path;
        Paint paint;

        PathWithPaint(Path path, Paint paint) {
            this.path = path;
            this.paint = paint;
        }
    }
}
