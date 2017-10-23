package com.example.scada.hannaidemo.ui;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.scada.hannaidemo.R;
import com.example.scada.hannaidemo.untils.Tools;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private LinearLayout[] mBottomTab;
    private TextView[] mBottomTabTv;
    private TextView mBottomTabShouYeTv;
    private TextView mBottomTabNengHaoTv;
    private TextView mBottomTabJianCeTv;
    private TextView mBottomTabYunWeiTv;
    private LinearLayout mBottomTabShouYe;
    private LinearLayout mBottomTabNengHao;
    private LinearLayout mBottomTabJianCe;
    private LinearLayout mBottomTabYunWei;
    private ViewPager mFragmentBaseLayout;
    private Fragment mMainFragment;
    private Fragment mNengHaoFragment;
    private Fragment mJianCeFragment;
    private Fragment mYunWeiFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBottomTabShouYe = (LinearLayout) findViewById(R.id.bottom_tab_shouye);
        mBottomTabNengHao = (LinearLayout) findViewById(R.id.bottom_tab_nenghao);
        mBottomTabJianCe = (LinearLayout) findViewById(R.id.bottom_tab_jiance);
        mBottomTabYunWei = (LinearLayout) findViewById(R.id.bottom_tab_yunwei);
        mBottomTabShouYeTv = (TextView) findViewById(R.id.bottom_tab_shouye_tv);
        mBottomTabNengHaoTv = (TextView) findViewById(R.id.bottom_tab_nenghao_tv);
        mBottomTabJianCeTv = (TextView) findViewById(R.id.bottom_tab_jiance_tv);
        mBottomTabYunWeiTv = (TextView) findViewById(R.id.bottom_tab_yunwei_tv);
        mFragmentBaseLayout = (ViewPager) findViewById(R.id.fragment_base_layout);
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(this.getSupportFragmentManager());
        mFragmentBaseLayout.setAdapter(adapter);
        mFragmentBaseLayout.addOnPageChangeListener(this);
        mBottomTab = new LinearLayout[]{mBottomTabShouYe, mBottomTabNengHao, mBottomTabJianCe, mBottomTabYunWei};
        mBottomTabTv = new TextView[]{mBottomTabShouYeTv,mBottomTabNengHaoTv,mBottomTabJianCeTv,mBottomTabYunWeiTv};
//        Tools.logD("tab数组长度 = " + mBottomTab.length);
        for (int i = 0; i < mBottomTab.length; i++) {
            mBottomTab[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_tab_shouye:
                mFragmentBaseLayout.setCurrentItem(0);
                break;
            case R.id.bottom_tab_nenghao:
                mFragmentBaseLayout.setCurrentItem(1);
                break;
            case R.id.bottom_tab_jiance:
                mFragmentBaseLayout.setCurrentItem(2);
                break;
            case R.id.bottom_tab_yunwei:
                mFragmentBaseLayout.setCurrentItem(3);
                break;
        }
    }

    private class MainFragmentPagerAdapter extends FragmentPagerAdapter {

        MainFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                default:
                    if (mMainFragment == null) {
                        mMainFragment = new MainFragment();
                    }
                    return mMainFragment;
                case 1:
                    if (mNengHaoFragment == null) {
                        mNengHaoFragment = new NengHaoFragment();
                    }
                    return mNengHaoFragment;
                case 2:
                    if (mJianCeFragment == null) {
                        mJianCeFragment = new JianCeFragment();
                    }
                    return mJianCeFragment;
                case 3:
                    if (mYunWeiFragment == null) {
                        mYunWeiFragment = new YunWeiFragment();
                    }
                    return mYunWeiFragment;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < 4; i++) {
            if (position == i) {
                mBottomTab[i].setBackgroundColor(getResources().getColor(R.color.colorBottomBlue));
                mBottomTabTv[i].setTextColor(getResources().getColor(R.color.colorTextBlue));
            } else {
                mBottomTab[i].setBackgroundColor(Color.argb(255, 255, 255, 255));
                mBottomTabTv[i].setTextColor(Color.GRAY);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
