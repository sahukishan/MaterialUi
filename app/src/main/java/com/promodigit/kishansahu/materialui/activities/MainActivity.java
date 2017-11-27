package com.promodigit.kishansahu.materialui.activities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;

import com.promodigit.kishansahu.materialui.R;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    int mScreen_Width, mScreen_Height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        mScreen_Height = displayMetrics.heightPixels;
        mScreen_Width = displayMetrics.widthPixels;

        relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout);
        relativeLayout.addView(new CustomView(this));
    }

    class CustomView extends View {

        public CustomView(Context context) {
            super(context);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Path path = new Path();
            path.moveTo(0, 0);
            path.lineTo(0, (float) (mScreen_Height / 3.5));
            path.lineTo((float) (mScreen_Width - mScreen_Width / 3), (float) (mScreen_Height / 2.5));
            path.lineTo((float) (mScreen_Width - mScreen_Width / 3) + (mScreen_Width / 5), (float) (mScreen_Height / 2.5));
            final RectF arrowOval = new RectF();
            arrowOval.set((float) (mScreen_Width - mScreen_Width / 3),
                    (float) (mScreen_Height / 2.6),
                    (float) (mScreen_Width - mScreen_Width / 3) + (mScreen_Width / 5),
                    (float) (mScreen_Height / 2.4));
//            path.addArc(arrowOval, 180, -180);


            int x1 = (mScreen_Width - mScreen_Width / 3);
            int x2 = (mScreen_Width - mScreen_Width / 3) + (mScreen_Width / 5);
            int y1 = (int) (mScreen_Height / 2.6);
            int y2 = (int) (mScreen_Height / 2.4);
            int midX = x1 + ((x2 - x1) / 2);
            int midY = y1 + ((y2 - y1) / 2);
            float xDiff = midX - x1;
            float yDiff = midY - y1;
            double angle = (Math.atan2(yDiff, xDiff) * (180 / Math.PI)) - 90;
            double angleRadians = Math.toRadians(angle);
            float pointX = (float) (midX + 180 * Math.cos(angleRadians));
            float pointY = (float) (midY + 180 * Math.sin(angleRadians));


//            path.cubicTo();
//            path.cubicTo(x1, y1, pointX, pointY, x2, y2);

            path.lineTo(mScreen_Width, (float) (mScreen_Height / 3));
            path.lineTo(mScreen_Width, 0);
            path.close();

            GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{0xFFAA00FF, 0xFF4A148C});
            gd.setCornerRadius(0f);

            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setShadowLayer(8, 0, 0, Color.YELLOW);
            paint.setShader(new LinearGradient(0, 0, 0, getHeight(), 0xFFAA00FF, 0xFF4A148C, Shader.TileMode.MIRROR));

            // Important for certain APIs
            setLayerType(LAYER_TYPE_SOFTWARE, paint);

            canvas.drawPath(path, paint);


//            Paint circlePaint = new Paint();
//            circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
//            circlePaint.setAntiAlias(true);
//            circlePaint.setStrokeWidth(2);
//            circlePaint.setColor(Color.CYAN);

            float centerWidth = canvas.getWidth() / 2; //get center x of display
            float centerHeight = canvas.getHeight() / 2; //get center y of display
            float circleRadius = 20; //set radius
//            float circleDistance = 200; //set distance between both circles


            //to draw an arrow, just lines needed, so style is only STROKE
//            circlePaint.setStyle(Paint.Style.STROKE);
//            circlePaint.setColor(Color.RED);

            //create a path to draw on
            Path arrowPath = new Path();

            //create an invisible oval. the oval is for "behind the scenes" ,to set the path´
            //area. Imagine this is an egg behind your circles. the circles are in the middle of this egg
//            arrowOval.set(centerWidth,
//                    centerHeight - 80,
//                    centerWidth + circleDistance,
//                    centerHeight + 80);

            //add the oval to path
            arrowPath.addArc(arrowOval, 180, -180);

            //draw path on canvas
//            canvas.drawPath(arrowPath, paint);
//            canvas.drawRect(arrowOval, paint);
        }
    }
}
