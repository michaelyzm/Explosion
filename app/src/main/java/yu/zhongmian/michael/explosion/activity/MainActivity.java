package yu.zhongmian.michael.explosion.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

import yu.zhongmian.michael.explosion.R;
import yu.zhongmian.michael.explosion.view.ExplodeView;

/**
 * Created by zhoyu on 6/29/2016.
 */
public class MainActivity extends Activity {
    ExplodeView explodeView;
    TextView restart;
    TextView duration;
    long beginTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        explodeView = (ExplodeView)findViewById(R.id.activity_main_explode_view);
        restart = (TextView)findViewById(R.id.activity_main_restart);
        duration = (TextView)findViewById(R.id.activity_main_duration);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explodeView.reset();
                beginTime = 0;
            }
        });
        explodeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explodeView.startExplode();
                if(beginTime == 0)
                {
                    beginTime = System.currentTimeMillis();
                }
            }
        });

        explodeView.setOnRedrawListener(new ExplodeView.OnRedrawListener() {
            @Override
            public void onRedraw() {
                if(beginTime != 0) {
                    long durationTime = System.currentTimeMillis() - beginTime;
                    long seconds = durationTime / 1000;
                    long milliseconds = durationTime % 1000;
                    String time = String.valueOf(seconds) + "." + String.format("%03d", milliseconds);
                    duration.setText(time);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
