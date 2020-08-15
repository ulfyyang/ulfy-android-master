package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.StaggeredAutoFullCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_staggered_auto_full)
public class StaggeredAutoFullCell extends BaseCell {
    @ViewById(id = R.id.coverIV) private ImageView coverIV;
    private StaggeredAutoFullCM cm;

    public StaggeredAutoFullCell(Context context) {
        super(context);
        init(context, null);
    }

    public StaggeredAutoFullCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /*
    瀑布流要求其中的内容必须能够撑开内容
        1. 如图片可以通过adjustViewBounds属性来由图片撑起来
        2. 控件可以通过比例布局来动态的设置视图的宽高比例来撑起来
        3. 控件可以动态设置具体的宽高来撑起来
     */

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (StaggeredAutoFullCM) model;
        ImageUtils.loadImage(cm.url, R.drawable.drawable_loading, coverIV);
    }
}