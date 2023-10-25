package com.technology.share.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.technology.share.domain.Icon;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.IconService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 图标Controller
 */
@RestController("AdminIconController")
@RequestMapping("/admin/icon")
@Api(tags = "图标管理")
public class IconController extends BaseController<Icon,IconService> {

    @Override
    protected QueryWrapper<Icon> getQueryWrapper(JSONObject queryParams) {
        Icon icon = queryParams.toJavaObject(Icon.class);
        QueryWrapper<Icon> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(icon.getIconClass())){
            queryWrapper.like("icon_class",icon.getIconClass());
        }
        return queryWrapper;
    }

//    @RequestMapping(value = "initIcon", method = RequestMethod.GET)
    public ResponseResult initIcon(){

        List<Icon> iconList = new ArrayList<>();
        for(String icon : icons.split(",")){
            Icon ic = new Icon();
            ic.setIconClass(icon);
            ic.setCreateBy("朱俊亮");
            ic.setCreateTime(new Date());
            ic.setUpdateBy("朱俊亮");
            ic.setUpdateTime(new Date());
            iconList.add(ic);
            service.save(ic);
        }
        return ResponseResult.ok();
    }

    private static String icons = "el-icon-platform-eleme\n" +
            ",el-icon-eleme\n" +
            ",el-icon-delete-solid\n" +
            ",el-icon-delete\n" +
            ",el-icon-s-tools\n" +
            ",el-icon-setting\n" +
            ",el-icon-user-solid\n" +
            ",el-icon-user\n" +
            ",el-icon-phone\n" +
            ",el-icon-phone-outline\n" +
            ",el-icon-more\n" +
            ",el-icon-more-outline\n" +
            ",el-icon-star-on\n" +
            ",el-icon-star-off\n" +
            ",el-icon-s-goods\n" +
            ",el-icon-goods\n" +
            ",el-icon-warning\n" +
            ",el-icon-warning-outline\n" +
            ",el-icon-question\n" +
            ",el-icon-info\n" +
            ",el-icon-remove\n" +
            ",el-icon-circle-plus\n" +
            ",el-icon-success\n" +
            ",el-icon-error\n" +
            ",el-icon-zoom-in\n" +
            ",el-icon-zoom-out\n" +
            ",el-icon-remove-outline\n" +
            ",el-icon-circle-plus-outline\n" +
            ",el-icon-circle-check\n" +
            ",el-icon-circle-close\n" +
            ",el-icon-s-help\n" +
            ",el-icon-help\n" +
            ",el-icon-minus\n" +
            ",el-icon-plus\n" +
            ",el-icon-check\n" +
            ",el-icon-close\n" +
            ",el-icon-picture\n" +
            ",el-icon-picture-outline\n" +
            ",el-icon-picture-outline-round\n" +
            ",el-icon-upload\n" +
            ",el-icon-upload2\n" +
            ",el-icon-download\n" +
            ",el-icon-camera-solid\n" +
            ",el-icon-camera\n" +
            ",el-icon-video-camera-solid\n" +
            ",el-icon-video-camera\n" +
            ",el-icon-message-solid\n" +
            ",el-icon-bell\n" +
            ",el-icon-s-cooperation\n" +
            ",el-icon-s-order\n" +
            ",el-icon-s-platform\n" +
            ",el-icon-s-fold\n" +
            ",el-icon-s-unfold\n" +
            ",el-icon-s-operation\n" +
            ",el-icon-s-promotion\n" +
            ",el-icon-s-home\n" +
            ",el-icon-s-release\n" +
            ",el-icon-s-ticket\n" +
            ",el-icon-s-management\n" +
            ",el-icon-s-open\n" +
            ",el-icon-s-shop\n" +
            ",el-icon-s-marketing\n" +
            ",el-icon-s-flag\n" +
            ",el-icon-s-comment\n" +
            ",el-icon-s-finance\n" +
            ",el-icon-s-claim\n" +
            ",el-icon-s-custom\n" +
            ",el-icon-s-opportunity\n" +
            ",el-icon-s-data\n" +
            ",el-icon-s-check\n" +
            ",el-icon-s-grid\n" +
            ",el-icon-menu\n" +
            ",el-icon-share\n" +
            ",el-icon-d-caret\n" +
            ",el-icon-caret-left\n" +
            ",el-icon-caret-right\n" +
            ",el-icon-caret-bottom\n" +
            ",el-icon-caret-top\n" +
            ",el-icon-bottom-left\n" +
            ",el-icon-bottom-right\n" +
            ",el-icon-back\n" +
            ",el-icon-right\n" +
            ",el-icon-bottom\n" +
            ",el-icon-top\n" +
            ",el-icon-top-left\n" +
            ",el-icon-top-right\n" +
            ",el-icon-arrow-left\n" +
            ",el-icon-arrow-right\n" +
            ",el-icon-arrow-down\n" +
            ",el-icon-arrow-up\n" +
            ",el-icon-d-arrow-left\n" +
            ",el-icon-d-arrow-right\n" +
            ",el-icon-video-pause\n" +
            ",el-icon-video-play\n" +
            ",el-icon-refresh\n" +
            ",el-icon-refresh-right\n" +
            ",el-icon-refresh-left\n" +
            ",el-icon-finished\n" +
            ",el-icon-sort\n" +
            ",el-icon-sort-up\n" +
            ",el-icon-sort-down\n" +
            ",el-icon-rank\n" +
            ",el-icon-loading\n" +
            ",el-icon-view\n" +
            ",el-icon-c-scale-to-original\n" +
            ",el-icon-date\n" +
            ",el-icon-edit\n" +
            ",el-icon-edit-outline\n" +
            ",el-icon-folder\n" +
            ",el-icon-folder-opened\n" +
            ",el-icon-folder-add\n" +
            ",el-icon-folder-remove\n" +
            ",el-icon-folder-delete\n" +
            ",el-icon-folder-checked\n" +
            ",el-icon-tickets\n" +
            ",el-icon-document-remove\n" +
            ",el-icon-document-delete\n" +
            ",el-icon-document-copy\n" +
            ",el-icon-document-checked\n" +
            ",el-icon-document\n" +
            ",el-icon-document-add\n" +
            ",el-icon-printer\n" +
            ",el-icon-paperclip\n" +
            ",el-icon-takeaway-box\n" +
            ",el-icon-search\n" +
            ",el-icon-monitor\n" +
            ",el-icon-attract\n" +
            ",el-icon-mobile\n" +
            ",el-icon-scissors\n" +
            ",el-icon-umbrella\n" +
            ",el-icon-headset\n" +
            ",el-icon-brush\n" +
            ",el-icon-mouse\n" +
            ",el-icon-coordinate\n" +
            ",el-icon-magic-stick\n" +
            ",el-icon-reading\n" +
            ",el-icon-data-line\n" +
            ",el-icon-data-board\n" +
            ",el-icon-pie-chart\n" +
            ",el-icon-data-analysis\n" +
            ",el-icon-collection-tag\n" +
            ",el-icon-film\n" +
            ",el-icon-suitcase\n" +
            ",el-icon-suitcase-1\n" +
            ",el-icon-receiving\n" +
            ",el-icon-collection\n" +
            ",el-icon-files\n" +
            ",el-icon-notebook-1\n" +
            ",el-icon-notebook-2\n" +
            ",el-icon-toilet-paper\n" +
            ",el-icon-office-building\n" +
            ",el-icon-school\n" +
            ",el-icon-table-lamp\n" +
            ",el-icon-house\n" +
            ",el-icon-no-smoking\n" +
            ",el-icon-smoking\n" +
            ",el-icon-shopping-cart-full\n" +
            ",el-icon-shopping-cart-1\n" +
            ",el-icon-shopping-cart-2\n" +
            ",el-icon-shopping-bag-1\n" +
            ",el-icon-shopping-bag-2\n" +
            ",el-icon-sold-out\n" +
            ",el-icon-sell\n" +
            ",el-icon-present\n" +
            ",el-icon-box\n" +
            ",el-icon-bank-card\n" +
            ",el-icon-money\n" +
            ",el-icon-coin\n" +
            ",el-icon-wallet\n" +
            ",el-icon-discount\n" +
            ",el-icon-price-tag\n" +
            ",el-icon-news\n" +
            ",el-icon-guide\n" +
            ",el-icon-male\n" +
            ",el-icon-female\n" +
            ",el-icon-thumb\n" +
            ",el-icon-cpu\n" +
            ",el-icon-link\n" +
            ",el-icon-connection\n" +
            ",el-icon-open\n" +
            ",el-icon-turn-off\n" +
            ",el-icon-set-up\n" +
            ",el-icon-chat-round\n" +
            ",el-icon-chat-line-round\n" +
            ",el-icon-chat-square\n" +
            ",el-icon-chat-dot-round\n" +
            ",el-icon-chat-dot-square\n" +
            ",el-icon-chat-line-square\n" +
            ",el-icon-message\n" +
            ",el-icon-postcard\n" +
            ",el-icon-position\n" +
            ",el-icon-turn-off-microphone\n" +
            ",el-icon-microphone\n" +
            ",el-icon-close-notification\n" +
            ",el-icon-bangzhu\n" +
            ",el-icon-time\n" +
            ",el-icon-odometer\n" +
            ",el-icon-crop\n" +
            ",el-icon-aim\n" +
            ",el-icon-switch-button\n" +
            ",el-icon-full-screen\n" +
            ",el-icon-copy-document\n" +
            ",el-icon-mic\n" +
            ",el-icon-stopwatch\n" +
            ",el-icon-medal-1\n" +
            ",el-icon-medal\n" +
            ",el-icon-trophy\n" +
            ",el-icon-trophy-1\n" +
            ",el-icon-first-aid-kit\n" +
            ",el-icon-discover\n" +
            ",el-icon-place\n" +
            ",el-icon-location\n" +
            ",el-icon-location-outline\n" +
            ",el-icon-location-information\n" +
            ",el-icon-add-location\n" +
            ",el-icon-delete-location\n" +
            ",el-icon-map-location\n" +
            ",el-icon-alarm-clock\n" +
            ",el-icon-timer\n" +
            ",el-icon-watch-1\n" +
            ",el-icon-watch\n" +
            ",el-icon-lock\n" +
            ",el-icon-unlock\n" +
            ",el-icon-key\n" +
            ",el-icon-service\n" +
            ",el-icon-mobile-phone\n" +
            ",el-icon-bicycle\n" +
            ",el-icon-truck\n" +
            ",el-icon-ship\n" +
            ",el-icon-basketball\n" +
            ",el-icon-football\n" +
            ",el-icon-soccer\n" +
            ",el-icon-baseball\n" +
            ",el-icon-wind-power\n" +
            ",el-icon-light-rain\n" +
            ",el-icon-lightning\n" +
            ",el-icon-heavy-rain\n" +
            ",el-icon-sunrise\n" +
            ",el-icon-sunrise-1\n" +
            ",el-icon-sunset\n" +
            ",el-icon-sunny\n" +
            ",el-icon-cloudy\n" +
            ",el-icon-partly-cloudy\n" +
            ",el-icon-cloudy-and-sunny\n" +
            ",el-icon-moon\n" +
            ",el-icon-moon-night\n" +
            ",el-icon-dish\n" +
            ",el-icon-dish-1\n" +
            ",el-icon-food\n" +
            ",el-icon-chicken\n" +
            ",el-icon-fork-spoon\n" +
            ",el-icon-knife-fork\n" +
            ",el-icon-burger\n" +
            ",el-icon-tableware\n" +
            ",el-icon-sugar\n" +
            ",el-icon-dessert\n" +
            ",el-icon-ice-cream\n" +
            ",el-icon-hot-water\n" +
            ",el-icon-water-cup\n" +
            ",el-icon-coffee-cup\n" +
            ",el-icon-cold-drink\n" +
            ",el-icon-goblet\n" +
            ",el-icon-goblet-full\n" +
            ",el-icon-goblet-square\n" +
            ",el-icon-goblet-square-full\n" +
            ",el-icon-refrigerator\n" +
            ",el-icon-grape\n" +
            ",el-icon-watermelon\n" +
            ",el-icon-cherry\n" +
            ",el-icon-apple\n" +
            ",el-icon-pear\n" +
            ",el-icon-orange\n" +
            ",el-icon-coffee\n" +
            ",el-icon-ice-tea\n" +
            ",el-icon-ice-drink\n" +
            ",el-icon-milk-tea\n" +
            ",el-icon-potato-strips\n" +
            ",el-icon-lollipop\n" +
            ",el-icon-ice-cream-square\n" +
            ",el-icon-ice-cream-round";
}
