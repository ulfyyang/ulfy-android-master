package com.ulfy.master.ui.cell;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.PickImgCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_pick_img)
public class PickImgCell extends BaseCell {
    @ViewById(id = R.id.flAdd) private FrameLayout flAdd;
    @ViewById(id = R.id.flPicked) private FrameLayout flPicked;
    @ViewById(id = R.id.ivPicked) private ImageView ivPicked;
    @ViewById(id = R.id.ivDel) private ImageView ivDel;
    private PickImgCM cm;

    public PickImgCell(Context context) {
        super(context);
        init(context, null);
    }

    public PickImgCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (PickImgCM) model;
            if (!TextUtils.isEmpty(cm.filePath)) {
                flAdd.setVisibility(GONE);
                flPicked.setVisibility(VISIBLE);
                ImageUtils.loadImage(cm.filePath, R.drawable.drawable_loading, ivPicked);
            } else {
                flAdd.setVisibility(VISIBLE);
                flPicked.setVisibility(GONE);
        }
    }

    @ViewClick(ids = {R.id.flAdd, R.id.ivDel})
    private void clickGroup(View v) {
        switch (v.getId()) {
            case R.id.flAdd:
               cm.addImg();
                break;
            case R.id.ivDel:
                cm.removeImg();
                break;
            default:
                break;
        }
    }
}