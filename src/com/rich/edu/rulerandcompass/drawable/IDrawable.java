package com.rich.edu.rulerandcompass.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface IDrawable 
{
	void Draw(Canvas canvas, Paint paint);
	void DrawHintPoints(Canvas canvas);
	void DrawIntersectionPoints(Canvas canvas);
}
