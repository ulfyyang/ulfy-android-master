package com.ulfy.master.application.vm;

import android.text.TextUtils;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.system.media_picker.MediaEntity;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.PickImgCM;
import com.ulfy.master.ui.view.ImagePickView;

import java.util.ArrayList;
import java.util.List;

public class ImagePickVM extends BaseVM {
    public List<PickImgCM> pickImgCMList = new ArrayList<>();
    public int maxCount = 9;                   //最多显示图片张数

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    pickImgCMList.add(new PickImgCM(0, null));
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    public void addImage(List<MediaEntity> entityList) {
        for (MediaEntity entity : entityList) {
            PickImgCM cm = new PickImgCM(entity.id, entity.filePath);
            if (pickImgCMList.size() == maxCount) {
                pickImgCMList.remove(pickImgCMList.size() - 1);
                pickImgCMList.add(cm);
            } else {
                pickImgCMList.add(pickImgCMList.size() - 1, cm);
            }
        }
    }

    public void removeImage(PickImgCM cm) {
        pickImgCMList.remove(cm);
        PickImgCM lastCM = pickImgCMList.get(pickImgCMList.size() - 1);
        if (lastCM != null && !TextUtils.isEmpty(lastCM.filePath)) {
            pickImgCMList.add(new PickImgCM(0, null));
        }
    }

    /**
     * 计算还有多少空位，用于设置下次选图的数量
     */
    public int caculateEmptyCount() {
        PickImgCM lastCM = pickImgCMList.get(pickImgCMList.size() - 1);
        if (lastCM != null && !TextUtils.isEmpty(lastCM.filePath)) {
            return 0;
        } else {
            return maxCount - (pickImgCMList.size() - 1);
        }
    }

    @Override public Class<? extends IView> getViewClass() {
        return ImagePickView.class;
    }
}