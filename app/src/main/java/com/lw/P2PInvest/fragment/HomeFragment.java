package com.lw.P2PInvest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lw.P2PInvest.R;

/**
 * Created by lw on 2017/2/11.
 * 首頁
 */
public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container,false) ;
        TextView tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_title.setText("首页");
        return rootView;
    }
}
