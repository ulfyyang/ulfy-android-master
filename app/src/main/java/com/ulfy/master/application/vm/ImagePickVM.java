package com.ulfy.master.application.vm;

import android.text.TextUtils;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.PickImgCM;
import com.ulfy.master.ui.view.ImagePickView;

import java.util.ArrayList;
import java.util.List;

public class ImagePickVM extends BaseVM {
    public List<PickImgCM> pickImgCMList = new ArrayList<>();
    public int maxImgCount = 9;                   //最多显示图片张数

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    pickImgCMList.add(new PickImgCM());
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    public void addImg(int fm_id,String filePath){
        PickImgCM pickImgCM=new PickImgCM();
        pickImgCM.fm_id=fm_id;
        pickImgCM.filePath=filePath;
        if(pickImgCMList.size()==maxImgCount){
            pickImgCMList.remove(pickImgCMList.size()-1);
            pickImgCMList.add(pickImgCM);
        }else {
            pickImgCMList.add(pickImgCMList.size()-1,pickImgCM);
        }
    }

    public void removeImg(PickImgCM pickImgCM){
        pickImgCMList.remove(pickImgCM);
        PickImgCM lastPickImgCM=pickImgCMList.get(pickImgCMList.size()-1);
        if(lastPickImgCM!=null && !TextUtils.isEmpty(lastPickImgCM.filePath)){
            pickImgCMList.add(new PickImgCM());
        }
    }

    @Override public Class<? extends IView> getViewClass() {
        return ImagePickView.class;
    }
}