package com.rich.edu.rulerandcompass;

import java.util.ArrayList;

import com.rich.edu.rulerandcompass.geo.GeoEntity;
import com.rich.edu.rulerandcompass.geo.Segment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PaintView  extends View
{
	
    @Override
    protected void onDraw(Canvas canvas) 
    {
    	for(GeoEntity e : drewEntities)
    	{
    		e.Draw(canvas, _paint);
    		if(curEntity != null)
    		{
    			e.CalcIntersection(curEntity);

    		}
    	}
    	
    	if(curEntity != null)
    	{
    		curEntity.Draw(canvas, _paint);
    	}
    }
	
	
	

	public boolean onTouchEvent(MotionEvent event)
	{
		int maskedAction = event.getActionMasked();
		
		if(maskedAction == MotionEvent.ACTION_DOWN)
		{
			handleStartTouch(event.getX(), event.getY());
		}
		else if(maskedAction== MotionEvent.ACTION_MOVE)
		{
			handleMoveTouch(event.getX(), event.getY());
			invalidate();
		}
		else if(maskedAction == MotionEvent.ACTION_UP)
		{
			handleMoveTouch(event.getX(), event.getY());
			invalidate();
	    	drewEntities.add(curEntity);
	    	curEntity = null;
		}
		
		return true;
	}
	

    private void handleStartTouch(float x, float y)
    {
    	_preX = x;
    	_preY = y;
    }
    
    private void handleMoveTouch(float x, float y)
    {
    	if(curEntity == null)
    	{
    		curEntity = createEntity(_preX, _preY, x, y);
    	}
    	else
    	{
    		curEntity.Reset(_preX, _preY, x, y);
    	}
    }
    
    
    //TODO
    //only a temp helper creater
    private GeoEntity createEntity(float s_x, float s_y, float e_x, float e_y)
    {
    	return new Segment(s_x, s_y, e_x, e_y);
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) 
    {
        int curW = _bitmap != null ? _bitmap.getWidth() : 0;
        int curH = _bitmap != null ? _bitmap.getHeight() : 0;
        if (curW >= w && curH >= h) {
            return;
        }

        if (curW < w) curW = w;
        if (curH < h) curH = h;

        Bitmap newBitmap = Bitmap.createBitmap(curW, curH, Bitmap.Config.ARGB_8888);
        

        Canvas newCanvas = new Canvas();
        newCanvas.setBitmap(newBitmap);
        if (_bitmap != null) 
        {
            newCanvas.drawBitmap(_bitmap, 0, 0, null);
        }
        _bitmap = newBitmap;

    }
    
	
	public PaintView(Context context) 
	{
		super(context);
        init();
	}

	public PaintView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
        init();
	}

    private void init() 
    {
        setFocusable(true);

        _paint.setAntiAlias(true);
        _paint.setColor(Color.RED);
        _paint.setStrokeWidth(1);
        _paint.setStyle(Paint.Style.STROKE);
    }
    
    private Bitmap _bitmap;
    private final Paint _paint = new Paint();

    private float _preX;
    private float _preY;
    private float neastJoinX;
    private float neastJoinY;
    
    private ArrayList<GeoEntity> drewEntities = new ArrayList<GeoEntity>();
    private GeoEntity curEntity = null;
}
