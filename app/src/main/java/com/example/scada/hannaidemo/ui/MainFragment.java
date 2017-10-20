package com.example.scada.hannaidemo.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        MainFragmentTopPagerAdapter topPagerAdapter = new MainFragmentTopPagerAdapter();
        mTopVp.setAdapter(topPagerAdapter);
        mTopVp.addOnPageChangeListener(new TopPageChangListener());
        mTopVp.setCurrentItem(1);
        
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
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
//            return super.instantiateItem(container, position);
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
//            super.destroyItem(container, position, object);
            View view = (View) object;
            container.removeView(view);
        }
    }

    private class MainFragmentTopPagerAdapter extends PagerAdapter {
        private ImageView[] ivs = new ImageView[2];
        private LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        private int[] src = new int[2];

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            src = new int[]{R.mipmap.gongsilogo, R.mipmap.gongsilogochang};
            if (ivs[position] == null) {
                ivs[position] = (ImageView) layoutInflater.inflate(R.layout.fragment_main_topvp_iv, null);
            }
            ImageView iv = ivs[position];
            iv.setImageResource(src[position]);
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
            //super.destroyItem(container, position, object);
            View view = (View) object;
            container.removeView(view);
        }
    }

    private class TopPageChangListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected( int position) {
            Tools.logD("位置 = " + position);
            if (position == 0) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3 * 1000);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mTopVp.setCurrentItem(1);
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3 * 1000);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mTopVp.setCurrentItem(0);
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    
}
