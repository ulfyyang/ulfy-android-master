package com.ulfy.master.application.vm;

import com.github.promeg.pinyinhelper.Pinyin;
import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.GroupContactBookCM;
import com.ulfy.master.domain.entity.PeopleGroup;
import com.ulfy.master.ui.view.ContactBookView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactBookVM extends BaseVM {
    public List<String> groupNameList;
    public List<PeopleGroup> peopleGroupList;
    public List<GroupContactBookCM> groupContactBookCMList = new ArrayList<>();

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");

                    List<String> contactPeopleList = getContactPeopleList();
                    peopleGroupList = getPeopleGroupList(contactPeopleList);

                    for (PeopleGroup peopleGroup : peopleGroupList) {
                        groupContactBookCMList.add(new GroupContactBookCM(peopleGroup));
                    }

                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
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
                groupIndex += 1 + peopleGroupList.get(i).peopleList.size();
            }
            return groupIndex;
        }
    }

    private List<String> getContactPeopleList() {
        String[] contactPeopleArray = new String[] {
                "穆罕默德", "艾萨克·牛顿", "耶稣", "释迦牟尼", "詹姆斯·克拉克·麦克斯韦",
                "路易·巴斯德", "孔子", "保罗", "蔡伦", "马丁·路德", "乔治·华盛顿",
                "伽利略·伽利莱", "约翰内斯·古腾堡", "克里斯托弗·哥伦布", "阿尔伯特·爱因斯坦",
                "亚里士多德", "路易·巴斯德", "欧几里得", "摩西", "卡尔·马克思", "莱特兄弟",
                "查尔斯·达尔文", "嬴政", "盖乌斯·图里努斯·屋大维", "尼古拉·哥白尼",
                "安托万-洛朗·德·拉瓦锡", "君士坦丁大帝", "詹姆斯·瓦特", "迈克尔·法拉第",
                "成吉思汗", "亚当·斯密", "威廉·莎士比亚", "约翰·道尔顿", "亚历山大大帝",
                "拿破仑一世", "托马斯·爱迪生", "安东尼·范·列文虎克", "威廉·莫顿", "古列尔莫·马可尼"
        };
        return Arrays.asList(contactPeopleArray);
    }

    private List<PeopleGroup> getPeopleGroupList(List<String> contactPeopleList) {
        // 将联系人按照首字母分组
        Map<String, List<String>> keyPeopleMap = new HashMap<>();
        for (String people : contactPeopleList) {
            String key = String.valueOf(Pinyin.toPinyin(people.trim().charAt(0)).charAt(0));
            List<String> peopleList = keyPeopleMap.get(key);
            if (peopleList == null) {
                peopleList = new ArrayList<>();
                keyPeopleMap.put(key, peopleList);
            }
            peopleList.add(people);
        }

        // 生成联系人数组并按字母排序
        groupNameList = new ArrayList<>(keyPeopleMap.keySet());
        Collections.sort(groupNameList);

        // 按顺序排放生成分组结构列表
        List<PeopleGroup> peopleGroupList = new ArrayList<>();
        for (String key : groupNameList) {
            PeopleGroup peopleGroup = new PeopleGroup();
            peopleGroup.groupName = key;
            peopleGroup.peopleList = keyPeopleMap.get(key);
            peopleGroupList.add(peopleGroup);
        }
        return peopleGroupList;
    }


    @Override public Class<? extends IView> getViewClass() {
        return ContactBookView.class;
    }
}