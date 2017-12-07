package com.sourcey.shuttlestopper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by afrahm on 12/6/2017.
 */

public class DisplayMap extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_map);
        ButterKnife.bind(this);

    }

}
