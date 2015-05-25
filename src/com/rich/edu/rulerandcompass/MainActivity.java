package com.rich.edu.rulerandcompass;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        _view = new PaintView(this);
        setContentView(_view);
    }
    
    private PaintView _view;
}
