package com.ulfy.master.ui.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.ulfy.android.system.base.UlfyBaseView;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;

@Layout(id = R.layout.view_content)
public abstract class ContentView extends UlfyBaseView {

    public ContentView(Context context) {
        super(context);
    }

    public ContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /*
    声明为protected类型，便于子类直接访问
     */
    @ViewById(id = R.id.contentFL) protected FrameLayout contentFL;

}