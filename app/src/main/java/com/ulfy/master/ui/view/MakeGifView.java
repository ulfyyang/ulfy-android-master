package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.MakeGifVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_make_gif)
public class MakeGifView extends BaseView {
    @ViewById(id = R.id.generateSmallGifBT) private Button generateSmallGifBT;
    @ViewById(id = R.id.generateBigGifBT) private Button generateBigGifBT;
    @ViewById(id = R.id.gifIV) private ImageView gifIV;
    private MakeGifVM vm;

    public MakeGifView(Context context) {
        super(context);
        init(context, null);
    }

    public MakeGifView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (MakeGifVM) model;
    }
    
    /**
     * click: generateSmallGifBT
     * 生成GIF
     */
    @ViewClick(ids = R.id.generateSmallGifBT) private void generateGifBT(View v) {
//        String gifPath = BitmapUtils.bitmapBitmapToGif(CacheConfig.getTackPhotoPictureCacheDir() + "/demo1.gif", 100, true,
//                BitmapFactory.decodeResource(getResources(), R.drawable.tab_activity_true),
//                BitmapFactory.decodeResource(getResources(), R.drawable.tab_activity_false),
//                BitmapFactory.decodeResource(getResources(), R.drawable.tab_charge_true),
//                BitmapFactory.decodeResource(getResources(), R.drawable.tab_charge_false)
//        );
//        ImageUtils.loadImage(gifPath, gifIV);
    }

    /**
     * click: generateBigGifBT
     * 生成GIF
     */
    @ViewClick(ids = R.id.generateBigGifBT) private void generateBigGifBT(View v) {
//        Bitmap[] bitmaps = new Bitmap[30];
//        for (int i = 0; i < bitmaps.length; i++) {
//            bitmaps[i] = BitmapFactory.decodeResource(getResources(), R.drawable.test);
//        }
//        String gifPath = BitmapUtils.bitmapBitmapToGif(CacheConfig.getTackPhotoPictureCacheDir() + "/demo2.gif", 100, false, UiUtils.dp2px(100), UiUtils.dp2px(100), bitmaps);
//        ImageUtils.loadImage(gifPath, gifIV);
    }
}