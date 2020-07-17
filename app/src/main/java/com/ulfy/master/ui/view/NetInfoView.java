package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.DialogProcesser;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.NetInfoVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_net_info)
public class NetInfoView extends BaseView {
    @ViewById(id = R.id.getMovieInfoIV) private Button getMovieInfoIV;
    @ViewById(id = R.id.movieInfoTV) private TextView movieInfoTV;
    private NetInfoVM vm;

    /*
    访问接口过程中的网络日志可以通过 ulfy-log 过滤来查看，框架中打印的日志（不仅仅是网络）都会通过该标签打印
     */

    public NetInfoView(Context context) {
        super(context);
        init(context, null);
    }

    public NetInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (NetInfoVM) model;
    }

    @ViewClick(ids = R.id.getMovieInfoIV) private void getMovieInfoIV(View v) {
        TaskUtils.loadData(getContext(), vm.loadMovieInfoOnExe(), new DialogProcesser(getContext()) {
                    @Override public void onSuccess(Object tipData) {
                        movieInfoTV.setText(vm.movie.toString());
                    }
                }
        );
    }
}