package com.refresh.constraintdemo;

import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeScroll;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

public class MainActivity extends AppCompatActivity {
    boolean change = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ConstraintLayout constraintLayout = findViewById(R.id.constraint);
        final ConstraintSet constraintSet = new ConstraintSet();
        final ConstraintSet constraintSeto = new ConstraintSet();
        constraintSet.clone(this, R.layout.activity_main2);
        constraintSeto.clone(constraintLayout);
        Transition transition=null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            transition = new ChangeBounds();
        }
        final Transition transitionx=transition;
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!change) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        transitionx.setInterpolator(new OvershootInterpolator(2));
                        TransitionManager.beginDelayedTransition(constraintLayout, transitionx);
                    }
                    constraintSet.applyTo(constraintLayout);
                    change = true;
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        transitionx.setInterpolator(new AccelerateDecelerateInterpolator());
                        TransitionManager.beginDelayedTransition(constraintLayout, transitionx);
                    }
                    change = false;
                    constraintSeto.applyTo(constraintLayout);
                }
            }
        });
    }
}
