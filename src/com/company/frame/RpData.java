package com.company.frame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 柏欢欢 on 2017/12/24.
 * 新增奖惩二级联动数据
 */
public class RpData {
    public static Map<String, List<String>> init() {
        Map<String,List<String>> fmap = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        list1.add("明星员工");
        list1.add("先进个人");
        list1.add("先进团队");
        List<String> list2 = new ArrayList<>();
        list2.add("多次迟到");
        list2.add("违纪违规");
        fmap.put("奖",list1);
        fmap.put("惩",list2);
         return fmap;
    }
}
