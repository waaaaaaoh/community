package com.community.cache;

import com.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagCache {
    public static List get(){
        List tagDTOS = new ArrayList<>();
        tagDTOS.add("游园有感");
        tagDTOS.add("路线推荐");
        tagDTOS.add("改进建议");
        return tagDTOS;
    }

    public static String isValid(String tags){
        String[] split = StringUtils.split(tags,",");
        String flag = null;
        for(String tag : split ){
            if(tag.equals("游园有感")||tag.equals("路线推荐") ||tag.equals("改进建议")){
            }else{
                flag = tag;
                return flag;
            }
        }
        return flag;
    }
}
