package com.lw.P2PInvest.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.lw.P2PInvest.R;
import com.lw.P2PInvest.bean.JsonIndex;
import com.lw.P2PInvest.common.AppNetConfig;
import com.lw.P2PInvest.utils.UiUtils;
import com.lw.P2PInvest.view.RoundProgress;
import com.lw.P2PInvest.weidget.PicassoImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

/**
 * Created by lw on 2017/2/11.
 * 首頁
 */
public class HomeFragment extends Fragment {
    @InjectView(R.id.tv_home_product)
    TextView tv_home_product;

    @InjectView(R.id.tv_home_yearrate)
    TextView tv_home_yearrate;
    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.roundProgress)
    RoundProgress roundProgress;

    private List<JsonIndex.ImageArrBean> carouseImagesList;

    private List<String> imageUrlList = new ArrayList<>();
    private List<String> bannerTitleList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        TextView tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        ButterKnife.inject(this, rootView);
        tv_title.setText("首页");
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        AsyncHttpClient client = new AsyncHttpClient();
        // 访问的url
        String INDEX = AppNetConfig.INDEX;
        client.post(INDEX, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String json = new String(responseBody);
                parseJson(json);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(UiUtils.getContext(), "网络失败", Toast.LENGTH_SHORT).show();
            }
        });

        initBanaerTitle();
    }

    /**
     * 初始化Banaer的title
     */
    private void initBanaerTitle() {
        bannerTitleList.add("分享砍学费");
        bannerTitleList.add("人脉总动员");
        bannerTitleList.add("借出53亿");
        bannerTitleList.add("购物街");
    }

    /**
     * 解析json
     *
     * @param json json数据
     */
    private void parseJson(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        JsonIndex jsonIndex = jsonObject.parseObject(json, JsonIndex.class);
        tv_home_product.setText(jsonIndex.getProInfo().getName());
        tv_home_yearrate.setText(jsonIndex.getProInfo().getYearRate());
        final String progress = jsonIndex.getProInfo().getProgress();
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < Integer.parseInt(progress); i++) {
                    roundProgress.setProgress(i + 1);
                    SystemClock.sleep(20);
                    //重新绘制20
                    //This method can be invoked from outside of the UI thread only when this View is attached to a window.
                    roundProgress.postInvalidate();

                }
            }
        }.start();
        // 给图片数组地址赋值
        carouseImagesList = jsonIndex.getImageArr();
        for (int i = 0; i < carouseImagesList.size(); i++) {
            imageUrlList.add(carouseImagesList.get(i).getIMAURL());
        }
        initBanaer();
    }

    /**
     * 初始化轮播图
     */
    private void initBanaer() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        // 设置图片加载器
        banner.setImageLoader(new PicassoImageLoader());
        //设置图片集合
        banner.setImages(imageUrlList);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(bannerTitleList);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
