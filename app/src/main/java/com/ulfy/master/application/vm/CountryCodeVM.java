package com.ulfy.master.application.vm;

import com.google.common.io.ByteStreams;
import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.master.MainApplication;
import com.ulfy.master.R;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.GroupCountryCodeCM;
import com.ulfy.master.domain.entity.CountryCode;
import com.ulfy.master.domain.entity.CountryGroup;
import com.ulfy.master.ui.view.CountryCodeView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryCodeVM extends BaseVM {
    public List<String> groupNameList;
    public List<CountryGroup> countryGroupList;
    public List<GroupCountryCodeCM> groupCountryCodeCMList = new ArrayList<>();

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");

                    groupNameList = getGroupNameList();
                    countryGroupList = getCountryGroupList(groupNameList);

                    for (CountryGroup countryGroup : countryGroupList) {
                        groupCountryCodeCMList.add(new GroupCountryCodeCM(countryGroup));
                    }

                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    e.printStackTrace();
                    task.notifyFail(e);
                }
            }
        };
    }

    /**
     * 根据组的名称查找到需要滚动的位置
     */
    public int getGroupIndexByLetter(String letter) {
        int letterIndex = groupNameList.indexOf(letter);
        if (letterIndex == -1) {        // 不存在的时候返回 -1
            return -1;
        } else {
            int groupIndex = 0;
            for (int i = 0; i < letterIndex; i++) {
                groupIndex += 1 + countryGroupList.get(i).countryCodeList.size();
            }
            return groupIndex;
        }
    }

    private List<String> getGroupNameList() {
        String[] groupNameArray = new String[] {
                "A", "B", "C", "D",
                "E", "F", "G", "H",
                "J", "K", "L", "M",
                "N", "P", "R", "S",
                "T", "W", "X", "Y", "Z"
        };
        return Arrays.asList(groupNameArray);
    }

    private List<CountryGroup> getCountryGroupList(List<String> groupNameList) throws Exception {
        // 循环解析内部的 26 个组，并添加到最外层的 26 组之中
        List<CountryGroup> countryGroupList = new ArrayList<>();

        // 解析最外层，分为 26 组，分别包含这 26 个英文字母的国家分组
        String countryCodeString = new String(ByteStreams.toByteArray(MainApplication.application.getResources().openRawResource(R.raw.countrycode)));
        JSONArray countryGroupJSONArray = new JSONArray(countryCodeString);
        for (int i = 0; i < countryGroupJSONArray.length(); i++) {

            CountryGroup countryGroup = new CountryGroup();

            // 循环解析每组中的国家，并添加到当前组中
            List<CountryCode> countryCodeList = new ArrayList<>();
            JSONArray countryJSONArray = countryGroupJSONArray.optJSONArray(i);
            for (int j = 0; j < countryJSONArray.length(); j++) {
                CountryCode countryCode = new CountryCode();
                countryCode.countryName = countryJSONArray.optJSONArray(j).optString(0);
                countryCode.countryCode = countryJSONArray.optJSONArray(j).optString(1);
                countryCodeList.add(countryCode);
            }

            // 对最外层组进行数据填充
            countryGroup.countryCodeList = countryCodeList;
            countryGroup.name = groupNameList.get(i);

            // 添加到 26 组之中
            countryGroupList.add(countryGroup);
        }

        return countryGroupList;
    }

    @Override public Class<? extends IView> getViewClass() {
        return CountryCodeView.class;
    }
}