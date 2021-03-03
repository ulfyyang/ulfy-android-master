package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.system.AppUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ViewToBitmapVM;
import com.ulfy.master.application.vm.ViewToBitmapViewVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_view_to_bitmap)
public class ViewToBitmapView extends BaseView {
    @ViewById(id = R.id.loadToImageBT) private Button loadToImageBT;
    @ViewById(id = R.id.viewToBitmap1BT) private Button viewToBitmap1BT;
    @ViewById(id = R.id.contentIV) private ImageView contentIV;
    @ViewById(id = R.id.viewToBitmap2BT) private Button viewToBitmap2BT;
    private ViewToBitmapVM vm;

    public ViewToBitmapView(Context context) {
        super(context);
        init(context, null);
    }

    public ViewToBitmapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (ViewToBitmapVM) model;
    }

    @ViewClick(ids = R.id.loadToImageBT) private void loadToImageBT(View v) {
        ImageUtils.loadImage(vm.pictureUrl, R.drawable.drawable_loading, contentIV);
    }

    /**
     * 这种生成方式是直接拿页面上已有的部分生成Bitmap，因为已经存在了，所以直接绘制就可以了
     */
    @ViewClick(ids = R.id.viewToBitmap1BT) private void viewToBitmap1BT(View v) {
        Bitmap bitmap = Bitmap.createBitmap(contentIV.getWidth(), contentIV.getHeight(), Bitmap.Config.ARGB_8888);
        contentIV.draw(new Canvas(bitmap));
        AppUtils.insertPictureToSystem(bitmap, "App生成", () -> UiUtils.show("生成完毕，请到相册中查看"));
    }

    /**
     * 这种生成方式相当于凭空构造一个View来生成图片，这个View是不会在页面上显示的
     */
    @ViewClick(ids = R.id.viewToBitmap2BT) private void viewToBitmap2BT(View v) {
        View view = UiUtils.createView(getContext(), new ViewToBitmapViewVM(vm.pictureUrl));

        /*
            这里可以指定View可用的最大宽高，方便限定图片的大小（实际情况根据实际来定）
         */
        view.measure(MeasureSpec.makeMeasureSpec(UiUtils.screenWidth(), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(UiUtils.screenHeight() * 3 / 4, MeasureSpec.EXACTLY));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        /*
            适当等待内部的数据加载完毕（如果其中显示的图片已经在别得地方显示了，则说明其已经在内存中了，这时候就不需要等待了）
            因为在内存中的图片可以直接显示出来
         */
        postDelayed(new Runnable() {
            @Override public void run() {
                Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
                view.draw(new Canvas(bitmap));
                AppUtils.insertPictureToSystem(bitmap, "App生成", () -> UiUtils.show("生成完毕，请到相册中查看"));
            }
        }, 500);
    }
}