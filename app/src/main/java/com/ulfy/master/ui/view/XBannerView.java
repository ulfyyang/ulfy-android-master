package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.stx.xhb.androidx.XBanner;
import com.stx.xhb.androidx.entity.SimpleBannerInfo;
import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.XBannerVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_xbanner)
public class XBannerView extends BaseView {
    @ViewById(id = R.id.normal1XB) private XBanner normal1XB;
    @ViewById(id = R.id.normal2XB) private XBanner normal2XB;
    @ViewById(id = R.id.clip1XB) private XBanner clip1XB;
    @ViewById(id = R.id.clip2XB) private XBanner clip2XB;
    private XBannerVM vm;

    public XBannerView(Context context) {
        super(context);
        init(context, null);
    }

    public XBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        normal1XB.loadImage(new XBanner.XBannerAdapter() {
            @Override public void loadBanner(XBanner banner, Object model, View view, int position) {
                ((ImageView)view).setScaleType(ImageView.ScaleType.CENTER_CROP);
                ImageUtils.loadImage(((SimpleBannerInfo)model).getXBannerUrl().toString(), R.drawable.drawable_loading, R.drawable.drawable_loading_false, (ImageView) view);
            }
        });
        normal1XB.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override public void onItemClick(XBanner banner, Object model, View view, int position) {
                UiUtils.show("点击了：" + ((SimpleBannerInfo)model).getXBannerUrl().toString() + " position:" + position);
            }
        });
        normal2XB.loadImage(new XBanner.XBannerAdapter() {
            @Override public void loadBanner(XBanner banner, Object model, View view, int position) {
                ((ImageView)view).setScaleType(ImageView.ScaleType.CENTER_CROP);
                ImageUtils.loadImage(((SimpleBannerInfo)model).getXBannerUrl().toString(), R.drawable.drawable_loading, R.drawable.drawable_loading_false, (ImageView) view);
            }
        });
        normal2XB.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override public void onItemClick(XBanner banner, Object model, View view, int position) {
                UiUtils.show("点击了：" + ((SimpleBannerInfo)model).getXBannerUrl().toString() + " position:" + position);
            }
        });
        clip1XB.loadImage(new XBanner.XBannerAdapter() {
            @Override public void loadBanner(XBanner banner, Object model, View view, int position) {
                ((ImageView)view).setScaleType(ImageView.ScaleType.CENTER_CROP);
                ImageUtils.loadImage(((SimpleBannerInfo)model).getXBannerUrl().toString(), R.drawable.drawable_loading, R.drawable.drawable_loading_false, (ImageView) view);
            }
        });
        clip1XB.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override public void onItemClick(XBanner banner, Object model, View view, int position) {
                UiUtils.show("点击了：" + ((SimpleBannerInfo)model).getXBannerUrl().toString() + " position:" + position);
            }
        });
        clip2XB.loadImage(new XBanner.XBannerAdapter() {
            @Override public void loadBanner(XBanner banner, Object model, View view, int position) {
                ImageUtils.loadImage(((SimpleBannerInfo)model).getXBannerUrl().toString(), R.drawable.drawable_loading, R.drawable.drawable_loading_false, view.findViewById(R.id.bannerIV));
            }
        });
        clip2XB.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override public void onItemClick(XBanner banner, Object model, View view, int position) {
                UiUtils.show("点击了：" + ((SimpleBannerInfo)model).getXBannerUrl().toString() + " position:" + position);
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (XBannerVM) model;
        normal1XB.setAutoPlayAble(vm.xBannerDataList.size() > 1);
        normal1XB.setBannerData(vm.xBannerDataList);
        normal2XB.setAutoPlayAble(vm.xBannerDataList.size() > 1);
        normal2XB.setBannerData(vm.xBannerDataList);
        clip1XB.setAutoPlayAble(vm.xBannerDataList.size() > 1);
        clip1XB.setBannerData(vm.xBannerDataList);
        clip2XB.setAutoPlayAble(vm.xBannerDataList.size() > 1);
        clip2XB.setBannerData(R.layout.cell_xbanner, vm.xBannerDataList);
    }
}