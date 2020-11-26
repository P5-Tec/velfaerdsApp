package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

public class touchActivityHandler extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private float x1, x2;
    private static final int MIN_DISTANCE = 500;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.gestureDetector = new GestureDetector(this, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            //press
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;

            //lift
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                //horizontal swipe
                float valueX = x2 - x1;
                if (Math.abs(valueX) > MIN_DISTANCE) {
                    if (x2 > x1) {
                        //swipe left / back
                        if (this.toString().contains("MainActivity")) {startActivity(/* "Backward" */forward(this, the24Strength.class));}
                        else{backward(); }
                    } else {
                        //swipe right / forward
                        if (this.toString().contains("MainActivity")){startActivity(forward(this, introPage.class));}
                        else if (this.toString().contains("the24Strength")){backward();}
                        else if (this.toString().contains("introPage")){startActivity(forward(this, customAvatar.class));}
                        else if (this.toString().contains("customAvatar")){startActivity(forward(this, questionsPage.class));}
                        else if (this.toString().contains("questionsPage")) {
                            if (questionsPage.answered == questionsPage.count){ startActivity(forward(this, selectPage.class)); }
                            else{ Toast.makeText(this, "Besvar alle spørgsmål for at fortsætte", Toast.LENGTH_SHORT).show(); } }
                        else if (this.toString().contains("selectPage")){startActivity(forward(this, emailPage.class));}
                    }
                }
        }
        return super.onTouchEvent(event);
    }

    //Forward er altid swipe ( højre mod venstre )
    public Intent forward(Context context, Class toClass) {
        return new Intent(context, toClass);
    }

    //Backward er altid swipe ( venstre mod højre )
    public void backward() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}