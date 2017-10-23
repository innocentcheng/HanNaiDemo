package com.example.scada.hannaidemo.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.scada.hannaidemo.R;
import com.example.scada.hannaidemo.untils.Tools;

/**
 * Created by Scada on 2017/10/19.
 */

public class MainFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private static final int CHANGE_LOOP_VIEW = 1;
    private ViewPager mVp;
    private ViewPager mTopVp;
    private TextView mPDFNum;
    private TextView mBYQNum;
    private TextView mJCDNum;
    private LinearLayout mCenterTabDL;
    private LinearLayout mCenterTabFH;
    private LinearLayout[] mCenterTab;
    private View mCenterTabDLflag;
    private View mCenterTabFHflag;
    private TextView mCenterTabDLTv;
    private TextView mCenterTabFHTv;
    private Handler mHandler;
    private boolean mViewLoopStop;
    private boolean mTopViewAutoChange;
    private MainFragmentTopPagerAdapter mTopPagerAdapter;
    private LinearLayout mTopVpCircle;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewLoopStop = false;
        mHandler = new UiHandler();
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mPDFNum = (TextView) view.findViewById(R.id.fragment_main_pdfnum);
        mBYQNum = (TextView) view.findViewById(R.id.fragment_main_byqnum);
        mJCDNum = (TextView) view.findViewById(R.id.fragment_main_jcdnum);
        mCenterTabDL = (LinearLayout) view.findViewById(R.id.fragment_main_centertab_dianliang);
        mCenterTabFH = (LinearLayout) view.findViewById(R.id.fragment_main_centertab_fuhe);
        mCenterTabDLflag = view.findViewById(R.id.fragment_main_centertab_dlflag);
        mCenterTabFHflag = view.findViewById(R.id.fragment_main_centertab_fhflag);
        mCenterTabDLTv = (TextView) view.findViewById(R.id.fragment_main_centertab_tvdl);
        mCenterTabFHTv = (TextView) view.findViewById(R.id.fragment_main_centertab_tvfh);
        mVp = (ViewPager) view.findViewById(R.id.fragment_main_vp);
        MainFragmentCenterPagerAdapter adapter = new MainFragmentCenterPagerAdapter();
        mVp.setAdapter(adapter);
        mVp.addOnPageChangeListener(this);
        mCenterTab = new LinearLayout[]{mCenterTabDL, mCenterTabFH};
        for (int i = 0; i < mCenterTab.length; i++) {
            mCenterTab[i].setOnClickListener(this);
        }
        mTopVp = (ViewPager) view.findViewById(R.id.fragment_main_topVp);
        mTopPagerAdapter = new MainFragmentTopPagerAdapter();
        mTopVp.setAdapter(mTopPagerAdapter);
        mTopVp.addOnPageChangeListener(new TopPageChangListener());
        mTopVp.setCurrentItem(0);
        mTopVpCircle = (LinearLayout) view.findViewById(R.id.fragment_main_topVp_circle);
        for (int i = 0; i < 2; i++) {
            //指示圆点
            ImageView circleIv = new ImageView(getContext());
            circleIv.setImageResource(R.drawable.selector_bottom_circle);
            circleIv.setEnabled(false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMarginStart(Tools.dip(getContext(), 10));
            mTopVpCircle.addView(circleIv, layoutParams);
        }
        mTopVpCircle.getChildAt(0).setEnabled(true);
        loopDisplay();
        return view;
    }

    @Override
    public void onPause() {
        Tools.logD("onPause");
        super.onPause();
        mTopViewAutoChange = false;
    }

    @Override
    public void onResume() {
        Tools.logD("onResume");
        super.onResume();
        mViewLoopStop = false;
        mTopViewAutoChange = true;
    }

    @Override
    public void onDestroy() {
        Tools.logD("onDestroy");
        super.onDestroy();
        mViewLoopStop = true;
    }

    private void loopDisplay() {
        Tools.logD("进入loop方法 循环前判断线程是否还在 ");
        Tools.logD("线程是否停止 = " + mViewLoopStop);
        if (mViewLoopStop) {
            return;
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mTopViewAutoChange = true;
                    while (!mViewLoopStop) {
                        Tools.logD("loopDisplay thread ");
                        Tools.sleep(3);
                        if (mTopViewAutoChange) {
                            mHandler.obtainMessage(CHANGE_LOOP_VIEW).sendToTarget();
                        }
                    }
                }
            }).start();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Tools.logD("MainFragmentTopPagerAdapter 位置 = " + position);
        if (position == 0) {
            mCenterTabDLflag.setBackgroundColor(Color.argb(255, 0, 182, 238));
            mCenterTabDLTv.setTextColor(Color.argb(255, 0, 182, 238));
        } else {
            mCenterTabDLflag.setBackgroundColor(Color.WHITE);
            mCenterTabDLTv.setTextColor(Color.argb(255, 0, 0, 0));
        }
        if (position == 1) {
            mCenterTabFHflag.setBackgroundColor(Color.argb(255, 0, 182, 238));
            mCenterTabFHTv.setTextColor(Color.argb(255, 0, 182, 238));
        } else {
            mCenterTabFHflag.setBackgroundColor(Color.WHITE);
            mCenterTabFHTv.setTextColor(Color.argb(255, 0, 0, 0));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_main_centertab_dianliang:
                mVp.setCurrentItem(0);
                break;
            case R.id.fragment_main_centertab_fuhe:
                mVp.setCurrentItem(1);
                break;
        }
    }

    private class MainFragmentCenterPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        private View[] mView;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View viewDL = layoutInflater.inflate(R.layout.fragment_main_dianliang, null);
            View viewFH = layoutInflater.inflate(R.layout.fragment_main_fuhe, null);
            mView = new View[]{viewDL, viewFH};
            container.addView(mView[position]);
            return mView[position];
        }

        @Override
        public int getCount() {
            return 2;
        }

        // 来判断显示的是否是同一张图片，将两个参数相比较返回即可
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private class MainFragmentTopPagerAdapter extends PagerAdapter {
        private int[] mTopImageResIds = new int[2];
        private ImageView[] ivs = new ImageView[mTopImageResIds.length];
        private LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            mTopImageResIds = new int[]{R.mipmap.gongsilogo, R.mipmap.gongsilogochang};
            if (ivs[position] == null) {
                ivs[position] = (ImageView) layoutInflater.inflate(R.layout.fragment_main_topvp_iv, null);
            }
            ImageView iv = ivs[position];
            iv.setImageResource(mTopImageResIds[position]);
            container.addView(iv);
            return iv;
        }

        @Override
        public int getCount() {
            return ivs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private class TopPageChangListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < 2; i++) {
                mTopVpCircle.getChildAt(i).setEnabled(position == i);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    mTopViewAutoChange = false;
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    mTopViewAutoChange = true;
                    break;
            }
        }
    }

    private class UiHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CHANGE_LOOP_VIEW:
                    int index = mTopVp.getCurrentItem();
                    int nextIndex = index + 1;
                    if (nextIndex == mTopPagerAdapter.mTopImageResIds.length) {
                        nextIndex = 0;
                    }
                    Tools.logD("UiHandler nextIndex: " + nextIndex);
                    mTopVp.setCurrentItem(nextIndex);
                    break;
            }
        }
    }
}
