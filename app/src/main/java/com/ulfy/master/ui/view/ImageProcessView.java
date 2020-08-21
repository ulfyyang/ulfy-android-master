package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ulfy.android.image.BitmapProcessChain;
import com.ulfy.android.image.BlackWhiteBitmapNode;
import com.ulfy.android.image.CircleBitmapNode;
import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.image.RectBitmapNode;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.DialogProcesser;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ImageProcessVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_image_process)
public class ImageProcessView extends BaseView {
    @ViewById(id = R.id.cacheSizeTV) private TextView cacheSizeTV;
    @ViewById(id = R.id.imageContainerLL) private LinearLayout imageContainerLL;
    @ViewById(id = R.id.content1IV) private ImageView content1IV;
    @ViewById(id = R.id.content2IV) private ImageView content2IV;
    @ViewById(id = R.id.content3IV) private ImageView content3IV;
    @ViewById(id = R.id.content4IV) private ImageView content4IV;
    @ViewById(id = R.id.clearCacheBT) private Button clearCacheBT;
    private ImageProcessVM vm;

    public ImageProcessView(Context context) {
        super(context);
        init(context, null);
    }

    public ImageProcessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    /*
        支持url、file格式文件 支持图片预加载 支持图片大图预览

        框架内置了多个图片处理节点，接待之间可以串联起来形成处理链。有些处理节点之间相互冲突，组合时应考虑组合的先后顺序
        目前支持的有：黑白照片、玻璃效果、内切圆、镜像倒置、圆角矩形、缩放大小

        对于图片圆角裁切来说，不同的分辨率产生的圆角的是不同的，这个是无法解决的。如果需要圆角的形状可以采用ShapeLayout
        从控件级别入手可解决该问题
     */

    @Override public void bind(IViewModel model) {
        vm = (ImageProcessVM) model;

        cacheSizeTV.setText(String.format("缓存大小：%s", ImageUtils.convertFileSizeToHumanReadableString(ImageUtils.getCacheSize())));

        ImageUtils.loadImage(vm.imageUrlList.get(0), R.drawable.drawable_loading, content1IV);
        ImageUtils.loadImage(vm.imageUrlList.get(1), R.drawable.drawable_loading, content2IV, new BlackWhiteBitmapNode());

        ImageUtils.loadImage(vm.imageUrlList.get(2), R.drawable.drawable_loading, content3IV,
                new BitmapProcessChain().connect(new BlackWhiteBitmapNode()).connect(new CircleBitmapNode(Color.RED)).build());
        ImageUtils.loadImage(vm.imageUrlList.get(3), R.drawable.drawable_loading, content4IV,
                new BitmapProcessChain().connect(new BlackWhiteBitmapNode()).connect(new RectBitmapNode(1, 1, 10)).build());
    }

    @ViewClick(ids = {R.id.content1IV, R.id.content2IV, R.id.content3IV, R.id.content4IV})
    private void previewIV(View v) {
        switch (v.getId()) {
            case R.id.content1IV:
                ImageUtils.preview(imageContainerLL, vm.imageUrlList, 0);
                break;
            case R.id.content2IV:
                ImageUtils.preview(imageContainerLL, vm.imageUrlList, 1);
                break;
            case R.id.content3IV:
                ImageUtils.preview(imageContainerLL, vm.imageUrlList, 2);
                break;
            case R.id.content4IV:
                ImageUtils.preview(imageContainerLL, vm.imageUrlList, 3);
                break;
        }
    }

    @ViewClick(ids = R.id.clearCacheBT) private void clearCacheBT(View v) {
        TaskUtils.loadData(getContext(), vm.clearCacheOnExe(), new DialogProcesser(getContext()) {
                    @Override public void onSuccess(Object tipData) {
                        ImageUtils.clearMemoryCache();
                        cacheSizeTV.setText(String.format("缓存大小：%s", ImageUtils.convertFileSizeToHumanReadableString(ImageUtils.getCacheSize())));
                    }
                }
        );
    }
}