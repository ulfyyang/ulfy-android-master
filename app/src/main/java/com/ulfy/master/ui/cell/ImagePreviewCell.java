package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.ImagePreviewCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_image_preview)
public class ImagePreviewCell extends BaseCell {
    @ViewById(id = R.id.contentIV) private ImageView contentIV;
    private ImagePreviewCM cm;

    public ImagePreviewCell(Context context) {
        super(context);
        init(context, null);
    }

    public ImagePreviewCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (ImagePreviewCM) model;
        ImageUtils.loadImage(cm.imageUrl, R.drawable.drawable_loading, R.drawable.drawable_loading_false, contentIV);
    }
}