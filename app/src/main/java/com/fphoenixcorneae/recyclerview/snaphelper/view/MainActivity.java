package com.fphoenixcorneae.recyclerview.snaphelper.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.fphoenixcorneae.recyclerview.snaphelper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wkz
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnPager)
    Button mBtnPager;
    @BindView(R.id.btnCenter)
    Button mBtnCenter;
    @BindView(R.id.btnLeft)
    Button mBtnLeft;
    @BindView(R.id.btnRight)
    Button mBtnRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnPager)
    public void onMBtnPagerClicked() {
        startActivity(new Intent(this, PagerSnapActivity.class));
    }

    @OnClick(R.id.btnCenter)
    public void onMBtnCenterClicked() {
        startActivity(new Intent(this, CenterSnapActivity.class));
    }

    @OnClick(R.id.btnLeft)
    public void onMBtnLeftClicked() {
        startActivity(new Intent(this, StartSnapActivity.class));
    }

    @OnClick(R.id.btnRight)
    public void onMBtnRightClicked() {
        startActivity(new Intent(this, EndSnapActivity.class));
    }
}
