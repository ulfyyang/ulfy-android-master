package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.List4CM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_list4)
public class List4Cell extends BaseCell {
    @ViewById(id = R.id.avatarIV) private ImageView avatarIV;
    private List4CM cm;

    public List4Cell(Context context) {
        super(context);
        init(context, null);
    }

    public List4Cell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (List4CM) model;
        ImageUtils.loadImage(cm.url, R.drawable.drawable_loading, R.drawable.drawable_loading_false, avatarIV);
    }
}