package com.lw.P2PInvest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lw.P2PInvest.R;
import com.lw.P2PInvest.common.AppManager;

/**
 * 欢迎页面
 */
public class SpashActivity extends AppCompatActivity {

    private RelativeLayout rl_welcome;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 全屏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_spash);
        AppManager.getInstance().add(this);
        rl_welcome = (RelativeLayout) findViewById(R.id.rl_welcome);
        goMainActivity();
    }

    private void goMainActivity() {

        AlphaAnimation animation = new AlphaAnimation(0, 1f);
        animation.setDuration(3000);
        rl_welcome.setAnimation(animation);
        // 延迟3s进入主界面方式一：
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                Intent intent = new Intent(SpashActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });

        // 延迟3s进入主界面方式二：
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Intent intent = new Intent(SpashActivity.this, MainActivity.class);
//                startActivity(intent);
//
//                finish();
//            }
//        }.start();
        // 延迟3s进入主界面方式三：
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //注意:这个run方法里面的是主线程，也就是说可以刷新UI
                Toast.makeText(SpashActivity.this, "进入主界面", Toast.LENGTH_SHORT).show();
                System.out.println("handle in thread:" + Thread.currentThread());//handle in thread:Thread[main,5,main]
                Intent intent = new Intent(SpashActivity.this, MainActivity.class);
                startActivity(intent);
//                finish();
                AppManager.getInstance().remove(SpashActivity.this);
            }
        }, 3000);

        // 延迟3s进入主界面方式四：通过Handle的handleMessage()方法

    }
}
