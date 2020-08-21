package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.ImagePreviewCM;
import com.ulfy.master.application.vm.ImagePreviewVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_image_preview)
public class ImagePreviewView extends BaseView {
    @ViewById(id = R.id.previewBT) private Button previewBT;
    @ViewById(id = R.id.imageContainerLL) private LinearLayout imageContainerLL;
    @ViewById(id = R.id.content1IV) private ImageView content1IV;
    @ViewById(id = R.id.content2IV) private ImageView content2IV;
    @ViewById(id = R.id.content3IV) private ImageView content3IV;
    @ViewById(id = R.id.content4IV) private ImageView content4IV;
    @ViewById(id = R.id.contentRV) private RecyclerView contentRV;
    private RecyclerAdapter<ImagePreviewCM> imageAdapter = new RecyclerAdapter<>();
    private ImagePreviewVM vm;

    public ImagePreviewView(Context context) {
        super(context);
        init(context, null);
    }

    public ImagePreviewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.gridLayout(contentRV).vertical(2).dividerDp(Color.TRANSPARENT, 10, 50, 0, 0);
        contentRV.setAdapter(imageAdapter);
        imageAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<ImagePreviewCM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, ImagePreviewCM model) {
                ImageUtils.preview(contentRV, vm.imageUrlWithContainerList, position);
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (ImagePreviewVM) model;
        ImageUtils.loadImage(vm.imageUrlWithContainerList.get(0), R.drawable.drawable_loading, R.drawable.drawable_loading_false, content1IV);
        ImageUtils.loadImage(vm.imageUrlWithContainerList.get(1), R.drawable.drawable_loading, R.drawable.drawable_loading_false, content2IV);
        ImageUtils.loadImage(vm.imageUrlWithContainerList.get(2), R.drawable.drawable_loading, R.drawable.drawable_loading_false, content3IV);
        ImageUtils.loadImage(vm.imageUrlWithContainerList.get(3), R.drawable.drawable_loading, R.drawable.drawable_loading_false, content4IV);
        imageAdapter.setData(vm.imageCMList);
        imageAdapter.notifyDataSetChanged();
    }

    @ViewClick(ids = R.id.previewBT) private void previewBT(View v) {
        ImageUtils.preview(getContext(), vm.imageUrlWithoutContainerList, 0);
    }

    @ViewClick(ids = {R.id.content1IV, R.id.content2IV, R.id.content3IV, R.id.content4IV})
    private void previewIV(View v) {
        switch (v.getId()) {
            case R.id.content1IV:
                ImageUtils.preview(imageContainerLL, vm.imageUrlWithContainerList, 0);
                break;
            case R.id.content2IV:
                ImageUtils.preview(imageContainerLL, vm.imageUrlWithContainerList, 1);
                break;
            case R.id.content3IV:
                ImageUtils.preview(imageContainerLL, vm.imageUrlWithContainerList, 2);
                break;
            case R.id.content4IV:
                ImageUtils.preview(imageContainerLL, vm.imageUrlWithContainerList, 3);
                break;
        }
    }
}