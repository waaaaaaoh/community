package com.community.controller;

import com.community.model.Garden;
import com.community.model.News;
import com.community.model.Show;
import com.community.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShowController {

    @Autowired
    ShowService showService;

    @GetMapping("/show/{id}")
    public String show(Model model,
//                       文章id
                       @PathVariable(name = "id") Long id,
//                      文章小分类
                       @RequestParam(value = "type",defaultValue = "1") Integer type,
//                       文章大分类
                       @RequestParam(value = "contentType",defaultValue = "1") Integer contentType){

//      页面小标题
        String title = null;
//      当前页分类
        String tag = null;
        String tag1 = null;
//      当前位置跳转页面参数
        String href = null;
//      跳转按钮常亮id
        String flag = null;

        switch (contentType){
            case 1:
                tag = "新闻动态/News information";
                tag1 = "新闻动态";
                href = "news";
                switch (type) {
                    case 1:
                        title = "最新公告";
                        flag = "zxgg";
                        break;
                    case 2:
                        title = "园内动态";
                        flag = "yndt";
                        break;
                    case 3:
                        title = "党政专区";
                        flag = "dzzq";
                        break;
                    default:
                }
                break;
            case 2:
                tag = "科学研究/Scientific research";
                tag1 = "科学研究";
                href = "science";
                switch (type) {
                    case 1:
                        title = "芙蓉研究";
                        flag = "fryj";
                        break;
                    case 2:
                        title = "种质资源";
                        flag = "zzzy";
                        break;
                    case 3:
                        title = "合作交流";
                        flag = "gzjl";
                        break;
                    case 4:
                        title = "园林有害生物预测报告";
                        flag = "ycbg";
                        break;
                    default:
                }
                break;
            case 3:
                tag = "科普教育/Popular Science Education";
                tag1 = "科普教育";
                href = "education";
                switch (type) {
                    case 1:
                        title = "教育项目";
                        flag = "jyxm";
                        break;
                    case 2:
                        title = "活动纪实";
                        flag = "hdjs";
                        break;
                    case 3:
                        title = "志愿者";
                        flag = "zyz";
                        break;
                    case 4:
                        title = "活动招募";
                        flag = "hdzm";
                        break;
                    case 5:
                        title = "大话植物";
                        flag = "dhzw";
                        break;
                    case 6:
                        title = "科普馆";
                        flag = "kpg";
                        break;
                    default:
                }
                break;
            case 4:
                tag = "园林园艺/Landscape gardening";
                tag1 = "园林园艺";
                href = "garden";
                switch (type) {
                    case 1:
                        title = "四季览园";
                        flag = "sjly";
                        break;
                    case 2:
                        title = "专类园";
                        flag = "zly";
                        break;
                    case 3:
                        title = "主题花展";
                        flag = "zthz";
                        break;
                    case 4:
                        title = "观花指南";
                        flag = "ghzn";
                        break;
                    default:
                }
                break;
            default:
        }

//        获取内容
        Show show = showService.findByContentTypeAndId(id,contentType);
        Show pre = showService.findpre(id,contentType,show.getType());
        Show next = showService.findnext(id,contentType,show.getType());


        model.addAttribute("show", show);
        model.addAttribute("showpre", pre);
        model.addAttribute("shownext", next);
        model.addAttribute("type", type);
        model.addAttribute("contentType",contentType);
        model.addAttribute("flag",flag);
        model.addAttribute("title", title);
        model.addAttribute("tag",tag);
        model.addAttribute("tag1",tag1);
        model.addAttribute("href",href);
        model.addAttribute("id",id);

        return "show";
    }
}
