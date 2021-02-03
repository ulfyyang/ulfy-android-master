package com.ulfy.master.ui.cell;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ulfy.android.bus.BusUtils;
import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.PickImgCM;
import com.ulfy.master.ui.base.BaseCell;
import com.ulfy.master.ui.view.ImagePickView;

@Layout(id = R.layout.cell_pick_img)
public class PickImgCell extends BaseCell {
    @ViewById(id = R.id.addFL) private FrameLayout addFL;
    @ViewById(id = R.id.imageFL) private FrameLayout imageFL;
    @ViewById(id = R.id.imageIV) private ImageView imageIV;
    @ViewById(id = R.id.deleteIV) private ImageView deleteIV;
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
        addFL.setVisibility(!TextUtils.isEmpty(cm.filePath) ? View.GONE : View.VISIBLE);
        imageFL.setVisibility(!TextUtils.isEmpty(cm.filePath) ? View.VISIBLE : View.GONE);
        if (!TextUtils.isEmpty(cm.filePath)) {
            ImageUtils.loadImage(cm.filePath, R.drawable.drawable_loading, imageIV);
        }
    }

    @ViewClick(ids = {R.id.addFL, R.id.deleteIV})
    private void clickGroup(View v) {
        switch (v.getId()) {
            case R.id.addFL:
                BusUtils.post(getContext(), new ImagePickView.OnItemClickEvent(1, cm));
                break;
            case R.id.deleteIV:
                BusUtils.post(getContext(), new ImagePickView.OnItemClickEvent(2, cm));
                break;
        }
    }
}