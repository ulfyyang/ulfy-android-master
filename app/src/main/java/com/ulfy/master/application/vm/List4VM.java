package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.List4CM;
import com.ulfy.master.ui.view.List4View;

import java.util.ArrayList;
import java.util.List;

public class List4VM extends BaseVM {
    public List<List4CM> avatarCMList = new ArrayList<>();

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    avatarCMList.add(new List4CM("https://i2.gqxz.com/uploads/ueditor/image/20200401/1585731188170324.jpeg"));
                    avatarCMList.add(new List4CM("https://www.keaidian.com/uploads/allimg/200306/1cadba16d14d4bd6b30cd643379613b5400x400.jpeg"));
                    avatarCMList.add(new List4CM("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT6uY3-zGm-0O0wDZWCrA5CNgw8pXuRpwB06w&usqp=CAU"));
                    avatarCMList.add(new List4CM("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQPuiosHLum3ekN-ak8l9uMspRy2w8u13QbFw&usqp=CAU"));
                    avatarCMList.add(new List4CM("https://lh3.googleusercontent.com/proxy/tHILbVB9CWtSpMs6Cu-lTZGXEC6GKlEy5UFUcZadT0Cik5o3X0th0ZW-NVA_hcsLu2oC1Op_OSE0Xio6WH-pn1IMT44CIOaZNZEMbVLH05lZ4Wk7aBrBlWE"));
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return List4View.class;
    }
}