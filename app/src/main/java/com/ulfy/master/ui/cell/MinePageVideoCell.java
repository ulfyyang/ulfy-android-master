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
import com.ulfy.master.application.cm.MinePageVideoCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_mine_page_video)
public class MinePageVideoCell extends BaseCell {
    @ViewById(id = R.id.coverIV) private ImageView coverIV;
    @ViewById(id = R.id.titleTV) private TextView titleTV;
    private MinePageVideoCM cm;

    public MinePageVideoCell(Context context) {
        super(context);
        init(context, null);
    }

    public MinePageVideoCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (MinePageVideoCM) model;
        ImageUtils.loadImage(cm.cover, R.drawable.drawable_loading, R.drawable.drawable_loading_false, coverIV);
    }
}