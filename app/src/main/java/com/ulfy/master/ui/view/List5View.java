package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.adapter.RecyclerGroupAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_transponder.RecyclerViewPageLoader;
import com.ulfy.android.task_transponder_smart.SmartRefresher;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.List5ChildCM;
import com.ulfy.master.application.cm.List5GroupCM;
import com.ulfy.master.application.vm.List5VM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_list5)
public class List5View extends BaseView {
    @ViewById(id = R.id.headerFL) private FrameLayout headerFL;
    @ViewById(id = R.id.smartSRL) private SmartRefreshLayout smartSRL;
    @ViewById(id = R.id.dataRV) private RecyclerView dataRV;
    private RecyclerGroupAdapter<List5GroupCM, List5ChildCM> groupAdapter = new RecyclerGroupAdapter<>();
    private SmartRefresher groupRefresher;
    private RecyclerViewPageLoader groupLoader;
    private List5VM vm;

    public List5View(Context context) {
        super(context);
        init(context, null);
    }

    public List5View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // 纵向线性布局
//        RecyclerViewUtils.linearLayout(dataRV).vertical();
        // 纵向线性布局带分割线
//        RecyclerViewUtils.linearLayout(dataRV).vertical().dividerDp(getResources().getColor(R.color.line), 0.5f, 1, 1);
        // 纵向表格布局
//        RecyclerViewUtils.gridLayout(dataRV).vertical(2);
        // 纵向表格布局带分割线
        RecyclerViewUtils.gridLayout(dataRV).vertical(2).dividerDp(getResources().getColor(R.color.line), 0.5f, 0.5f, 1, 1);

        groupAdapter.setHeaderView(headerFL);
        dataRV.setAdapter(groupAdapter);
        // 由于分组适配器内部显示的即包含组也包含子组内容，因此常规的单击事件只支持识别到 IViewModel 级别，且其中的 position 是按照不分组适配器来计算的
        groupAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<IViewModel>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, IViewModel model) {
                UiUtils.show("点击了：" + model + "-" + position);
            }
        });
        groupAdapter.setOnGroupItemClickListener(new RecyclerGroupAdapter.OnGroupItemClickListener<List5GroupCM, List5ChildCM>() {
            @Override public void onGroupItemClick(ViewGroup parent, View view, int position, int groupPosition, List5GroupCM model) {
                UiUtils.show("点击了：" + model.name + "-" + position + "-" + groupPosition);
            }
            @Override public void onChildItemClick(ViewGroup parent, View view, int position, int groupPosition, int childPosition, List5ChildCM model) {
                UiUtils.show("点击了：" + model.name + "-" + position + "-" + groupPosition + "-" + childPosition);
            }
        });

        groupRefresher = new SmartRefresher(smartSRL, new SmartRefresher.OnRefreshSuccessListener() {
            @Override public void onRefreshSuccess(SmartRefresher smartRefresher) {
                bind(vm);
            }
        });
        groupLoader = new RecyclerViewPageLoader(dataRV, groupAdapter, null);
    }

    @Override public void bind(IViewModel model) {
        vm = (List5VM) model;
        groupRefresher.updateExecuteBody(vm.list5GroupTaskInfo, vm.loadDataPerPageOnExe());
        groupLoader.updateExecuteBody(vm.list5GroupTaskInfo, vm.loadDataPerPageOnExe());
        groupAdapter.setData(vm.list5GroupCMList);
        groupAdapter.notifyDataSetChanged();
        groupLoader.notifyDataSetChanged();
    }
}