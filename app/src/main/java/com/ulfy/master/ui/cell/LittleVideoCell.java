package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.LittleVideoCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_little_video)
public class LittleVideoCell extends BaseCell {
    @ViewById(id = R.id.coverIV) private ImageView coverIV;
    @ViewById(id = R.id.countLikeTV) private TextView countLikeTV;
    @ViewById(id = R.id.titleTV) private TextView titleTV;
    private LittleVideoCM cm;

    public LittleVideoCell(Context context) {
        super(context);
        init(context, null);
    }

    public LittleVideoCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (LittleVideoCM) model;
        ImageUtils.loadImage(cm.imageUrl, R.drawable.drawable_loading, coverIV);
    }
}