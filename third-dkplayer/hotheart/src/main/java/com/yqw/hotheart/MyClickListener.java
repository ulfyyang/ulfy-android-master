package com.yqw.hotheart;

import android.os.Handler;
import android.view.View;

import java.util.LinkedList;
import java.util.List;


/**
 * 双击和单击冲突的解决方案
 * Created by HeTingwei on 2018/4/30.
 */

public class MyClickListener implements View.OnClickListener {
    private static int timeout = 200;//双击间四百毫秒延时
    private int clickCount = 0;//记录连续点击次数
    private Handler handler;
    private MyClickCallBack myClickCallBack;
    private List<Run> runs = new LinkedList<>();

    public MyClickListener(MyClickCallBack myClickCallBack) {
        this.myClickCallBack = myClickCallBack;
        handler = new Handler();
    }

    public interface MyClickCallBack {
        void onSimpleClick();//点击一次的回调
        void onDoubleClick();//连续点击两次的回调
    }

    @Override public void onClick(View view) {
        clickCount++;
        // 单击需要延迟触发，但是如果次数达到2了可以直接触发
        if (clickCount == 2 && myClickCallBack != null) {
            myClickCallBack.onDoubleClick();
        }
        handler.postDelayed(getNewRun(), timeout);//延时timeout后执行run方法中的代码
    }

    Run getNewRun() {
        for (Run run : runs) {
            run.last = false;
        }
        Run run = new Run();
        run.last = true;
        runs.add(run);
        return run;
    }

    class Run implements Runnable {
        public boolean last;                // 是否是最后一个，最后一个才会重置时间

        @Override public void run() {
            if (clickCount == 1) {
                myClickCallBack.onSimpleClick();
            }
            runs.remove(this);
            if (last) {
                clickCount = 0;//计数清零
            }
        }
    }
}
