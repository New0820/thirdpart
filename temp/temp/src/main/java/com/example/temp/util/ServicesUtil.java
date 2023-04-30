package com.example.temp.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author monkey king
 * @Date 2019/12/14 19:45
 */
@Component
@Slf4j
public class ServicesUtil {


    public String getAttributeCn(String attributeUs, boolean showLongName) {
        //属性名称：10:自有商品; 20:寄卖商品; 30:质质押商品 40:其它
        String attributeCn = "未知属性";
        if (!LocalUtils.isEmptyAndNull(attributeUs)) {
            switch (attributeUs) {
                case "10":
                    attributeCn = showLongName ? "自有商品" : "自";
                    break;
                case "20":
                    attributeCn = showLongName ? "寄卖商品" : "寄";
                    break;
                case "30":
                    attributeCn = showLongName ? "质押商品" : "押";
                    break;
                default:
                    attributeCn = showLongName ? "其它商品" : "其";
                    break;
            }
        }
        return attributeCn;
    }

    public String getClassifyCn(String classifyUs) {
        //属性名称：10:自有商品; 20:寄卖商品; 30:质质押商品 40:其它
        String attributeCn = "未知分类";
        if (!LocalUtils.isEmptyAndNull(classifyUs)) {
            switch (classifyUs) {
                case "WB":
                    attributeCn = "腕表";
                    break;
                case "XB":
                    attributeCn = "箱包";
                    break;
                case "ZB":
                    attributeCn = "珠宝";
                    break;
                case "XX":
                    attributeCn = "鞋靴";
                    break;
                case "PS":
                    attributeCn = "配饰";
                    break;
                case "FS":
                    attributeCn = "服饰";
                    break;
                case "QT":
                    attributeCn = "其它";
                    break;
                case "SS":
                    attributeCn = "首饰";
                    break;
                default:
                    break;
            }
        }
        return attributeCn;
    }

    public String getStateCn(String stateUs) {
        String stateCn = null;
        if (!LocalUtils.isEmptyAndNull(stateUs)) {
            int state = Integer.parseInt(stateUs);
            if (LocalUtils.isBetween(state, 10, 19)) {
                stateCn = "未上架";
            } else if (LocalUtils.isBetween(state, 20, 39)) {
                stateCn = "已上架";
            } else if (LocalUtils.isBetween(state, 40, 49)) {
                stateCn = "已售罄";
            } else if (state == -90) {
                stateCn = "已删除";
            } else {
                stateCn = "未知状态";
            }
        }
        return stateCn;
    }


}
