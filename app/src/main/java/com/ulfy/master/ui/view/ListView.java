package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ListVM;
import com.ulfy.master.ui.activity.ContactBookActivity;
import com.ulfy.master.ui.activity.CountryCodeActivity;
import com.ulfy.master.ui.activity.List1Activity;
import com.ulfy.master.ui.activity.List2Activity;
import com.ulfy.master.ui.activity.List3Activity;
import com.ulfy.master.ui.activity.List4Activity;
import com.ulfy.master.ui.activity.List5Activity;
import com.ulfy.master.ui.activity.StaggeredAutoFullActivity;
import com.ulfy.master.ui.activity.StaggeredRandomRatioActivity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_list)
public class ListView extends BaseView {
    @ViewById(id = R.id.list1TV) private TextView list1TV;
    @ViewById(id = R.id.list2TV) private TextView list2TV;
    private ListVM vm;

    public ListView(Context context) {
        super(context);
        init(context, null);
    }

    public ListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (ListVM) model;
    }

    /**
     * click: list1TV
     * 列表页一
     */
    @ViewClick(ids = R.id.list1TV) private void list1TV(View v) {
        List1Activity.startActivity();
    }

    /**
     * click: list2TV
     * 列表页二
     */
    @ViewClick(ids = R.id.list2TV) private void list2TV(View v) {
        List2Activity.startActivity();
    }

    /**
     * click: list3TV
     * 列表页三
     */
    @ViewClick(ids = R.id.list3TV) private void list3TV(View v) {
        List3Activity.startActivity();
    }

    /**
     * click: list4TV
     * 列表页四
     */
    @ViewClick(ids = R.id.list4TV) private void list4TV(View v) {
        List4Activity.startActivity();
    }

    /**
     * click: list5TV
     * 列表项五
     */
    @ViewClick(ids = R.id.list5TV) private void list5TV(View v) {
        List5Activity.startActivity();
    }

    /**
     * click: list6TV
     * 列表项六：国家列表
     */
    @ViewClick(ids = R.id.list6TV) private void list6TV(View v) {
        CountryCodeActivity.startActivity();
    }

    /**
     * click: list7TV
     * 列表七：联系人列表
     */
    @ViewClick(ids = R.id.list7TV) private void list7TV(View v) {
        ContactBookActivity.startActivity();
    }

    /**
     * click: list8TV
     * 列表八：瀑布流列表
     */
    @ViewClick(ids = R.id.list8TV) private void list8TV(View v) {
        StaggeredAutoFullActivity.startActivity();
    }

    /**
     * click: list9TV
     * 列表九：瀑布流列表
     */
    @ViewClick(ids = R.id.list9TV) private void list9TV(View v) {
        StaggeredRandomRatioActivity.startActivity();
    }
}