package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_extension.UiBackgrounder;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.BackgrounderVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_backgrounder)
public class BackgrounderView extends BaseView {
    @ViewById(id = R.id.startBT) private Button startBT;
    @ViewById(id = R.id.statusTV) private TextView statusTV;
    @ViewById(id = R.id.contentTV) private TextView contentTV;
    private UiBackgrounder backgrounder = new UiBackgrounder();
    private BackgrounderVM vm;

    public BackgrounderView(Context context) {
        super(context);
        init(context, null);
    }

    public BackgrounderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        backgrounder.setUiBackgrounderExecuteBody(new UiBackgrounder.UiBackgrounderExecuteBody() {
            @Override public void onExecute(UiBackgrounder backgrounder) {
                // 运行在后台线程
                try {
                    Thread.sleep(1000);
                } catch (Exception e) { }
                vm.content = "加载的内容";
            }
        }).setOnBackgrounderStartListener(new UiBackgrounder.OnBackgrounderStartListener() {
            @Override public void onBackgrounderStart(UiBackgrounder backgrounder) {
                statusTV.setText("执行中...");
            }
        }).setOnBackgrounderFinishListener(new UiBackgrounder.OnBackgrounderFinishListener() {
            @Override public void onBackgrounderFinish(UiBackgrounder backgrounder) {
                statusTV.setText("执行结束");
                contentTV.setText(vm.content);
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (BackgrounderVM) model;
    }

    /*
    UiTimer和UiBackgrounder之间都有一个互相连接的方法，可以使得在其中一个执行完毕之后自动启动下一个被连接的任务
    这两个工具适合编写页面临时使用的小范围事件，应当将其作为实现页面部分效果的快速工具对待
     */

    /**
     * click: startBT
     * 启动一个后台任务
     */
    @ViewClick(ids = R.id.startBT) private void startBT(View v) {
        backgrounder.start();
    }
}