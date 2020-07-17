package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.views.ChartView;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ChartViewVM;
import com.ulfy.master.ui.base.BaseView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Layout(id = R.layout.view_chart_view)
public class ChartViewView extends BaseView {
    @ViewById(id = R.id.refreshBT) private Button refreshBT;
    @ViewById(id = R.id.charCV) private ChartView charCV;
    private ChartViewVM vm;

    public ChartViewView(Context context) {
        super(context);
        init(context, null);
    }

    public ChartViewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (ChartViewVM) model;
    }
    
    /**
     * click: refreshBT
     * 刷新表格数据
     */
    @ViewClick(ids = R.id.refreshBT) private void refreshBT(View v) {
        List<ChartView.Data> dataList = new ArrayList<>();
        dataList.add(new ChartView.Data("03.11", new Random().nextInt(100)));
        dataList.add(new ChartView.Data("03.11", new Random().nextInt(100)));
        dataList.add(new ChartView.Data("03.11", new Random().nextInt(100)));
        dataList.add(new ChartView.Data("03.11", new Random().nextInt(100)));
        dataList.add(new ChartView.Data("03.11", new Random().nextInt(100)));
        dataList.add(new ChartView.Data("03.11", new Random().nextInt(100)));
        dataList.add(new ChartView.Data("03.11", new Random().nextInt(100)));
        dataList.add(new ChartView.Data("03.11", new Random().nextInt(100)));
        charCV.setDataList(dataList);
    }
}