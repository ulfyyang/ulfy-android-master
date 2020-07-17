package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task.Transponder;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.TaskVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_task)
public class TaskView extends BaseView {
    @ViewById(id = R.id.loadDataBT) private Button loadDataBT;
    @ViewById(id = R.id.loadListStartPageBT) private Button loadListStartPageBT;
    @ViewById(id = R.id.loadListNextPageBT) private Button loadListNextPageBT;
    @ViewById(id = R.id.statusTV) private TextView statusTV;
    @ViewById(id = R.id.scanCodeTV) private TextView resultTV;
    private TaskVM vm;

    public TaskView(Context context) {
        super(context);
        init(context, null);
    }

    public TaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (TaskVM) model;
    }

    /*
    可在Application中配置TaskUtils.Config.checkInternet = false;
    必在Applicatin中配置TaskUtils.init(this);
     */

    /*
    TaskUtils中又很多重载的方法，可以指定本次是否检查网络、使用什么样的执行器执行任务
    任务执行器可从TaskExecutor的静态方法获取
     */

    /*
    本例对于响应器只是采用了最简单的直观实现，不同的响应器会有不同的界面效果，具体的响应器实现在ulfy-task-transponder包中
     */

    /**
     * click: loadDataBT
     * 加载普通数据
     */
    @ViewClick(ids = R.id.loadDataBT) private void loadDataBT(View v) {
        TaskUtils.loadData(getContext(), vm.loadDataTaskOnExe(), new Transponder() {
            @Override public void onNoNetConnection(Object data) {
                statusTV.setText(data.toString());
            }
            @Override protected void onNetError(Object data) {
                statusTV.setText(data.toString());
            }
            @Override protected void onStart(Object data) {
                statusTV.setText(data.toString());
            }
            @Override protected void onSuccess(Object data) {
                resultTV.setText(data + ": " +  vm.data);
            }
            @Override protected void onFail(Object data) {
                statusTV.setText(data.toString());
            }
            @Override protected void onUpdate(Object data) {
                statusTV.setText(data.toString());
            }
            @Override protected void onFinish(Object data) {
                statusTV.setText(data.toString());
            }
        });
    }


    /**
     * click: loadListStartPageBT, loadListNextPageBT
     * 加载起始页、加载下一页
     */
    @ViewClick(ids = {R.id.loadListStartPageBT, R.id.loadListNextPageBT})
    private void clickLoadListPageTask(View v) {
        switch (v.getId()) {
            case R.id.loadListStartPageBT:
                vm.loadPageDataTaskInfo.loadStartPage();
//                vm.loadPageDataTaskInfo.reloadAllPage();
                break;
            case R.id.loadListNextPageBT:
                vm.loadPageDataTaskInfo.loadNextPage();
                break;
        }
        TaskUtils.loadData(getContext(), vm.loadPageDataTaskInfo, vm.loadListDataTaskPerPageOnExe(), new Transponder() {
            @Override public void onNoNetConnection(Object data) {
                statusTV.setText(data.toString());
            }
            @Override protected void onNetError(Object data) {
                statusTV.setText(data.toString());
            }
            @Override protected void onStart(Object data) {
                statusTV.setText(data.toString());
            }
            @Override protected void onSuccess(Object data) {
                resultTV.setText(data + ": " +  vm.listPageDataList);
            }
            @Override protected void onFail(Object data) {
                statusTV.setText(data.toString());
            }
            @Override protected void onUpdate(Object data) {
                statusTV.setText(data.toString());
            }
            @Override protected void onFinish(Object data) {
                statusTV.setText(data.toString());
            }
        });
    }

}