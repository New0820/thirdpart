package com.example.temp.param;

import lombok.Data;
import org.springframework.data.annotation.Id;


/**
 * create by: CM
 * description:  商品存储实体
 * create time: 2022/7/19 10:55
 *
 * @author cm
 */
@Data
public class ParamProductEs {
    /**
     * 商品id （pro_product表id）
     */
    @Id
    private Integer proId;
    /**
     * 商品编码（pro_product表biz_id）
     */

    private String bizId;


    private Integer bsuId;
    /**
     * 店铺id （pro_product表fk_shp_shop_id）
     */
    private Integer shopId;
    /**
     * 分类（pro_product表fk_pro_classify_code）
     */
    private String proClassifyCode;
    /**
     * 商品名称（pro_product表name）
     */
    private String proName;
    /**
     * 描述（pro_product表description）
     */
    private String description;
    /**
     * 备注（pro_product表remark）
     */
    private String remark;
    /**
     * 独立编码（pro_product表unique_code）
     */
    private String uniqueCode;
    /**
     * 图片下载时间 （pro_download_img表update_time）
     */

    private String downloadImgUpdateTime;
    /**
     * 更新时间（pro_product表update_time）
     */
    private String proUpdateTime;
    /**
     * 插入时间（pro_product表insert_time）
     */
    private String proInsertTime;
    /**
     * 销售价（pro_product表sale_price）
     */

    private String proSalePrice;
    /**
     * 同行价（pro_product表trade_price）
     */

    private Long proTradePrice;
    private String smallImg;
    /**
     * 成本价（pro_product表init_price）
     */
    private Long proInitPrice;
    /**
     * 品牌（pro_product表fk_pro_classify_sub_name）
     */

    private String proClassifySubName;

    /**
     * 系列
     */
    private String proSubSeriesName;

    /**
     * 型号
     */
    private String proSeriesModelName;
    /**
     * 回收人员（pro_product表recycle_admin）
     */
    private Integer proRecycleAdmin;
    /**
     * 回收类型id（pro_product表fk_pro_recycle_id）
     */
    private Integer proRecycleId;
    /**
     * 回收时间（pro_product表recycle_time）
     */
    private String proRecycleTime;
    /**
     * 商品位置id（pro_dynamic表id）
     */
    private String proDynamicId;
    /**
     * 标签id（pro_tag表id）
     */
    private String proTagId;
    /**
     * 适用人群（pro_product表target_user）
     */
    private String proTargetUser;
    /**
     * 是否打开闲鱼按钮（pro_product表xianyu_sync_state）
     */

    private Integer proXianyuSyncState;
    /**
     * 是否打开快手按钮（pro_product表kuaishou_sync_state）
     */
    private Integer proKuaishouSyncState;
    /**
     * 是否打开抖店按钮（pro_product表doudian_sync_state）
     */
    private Integer proDoudianSyncState;
    /**
     * 临时仓id（pro_temp_product表id）
     */
    private String proTempId;
    /**
     * 状态（pro_product表fk_pro_state_code）
     */
    private Integer proStateCode;
    /**
     * 擦亮时间（pro_product表refresh_time）
     */
    private String proRefreshTime;

    /**
     * 修改时间
     */
    private String updateTime;
    /**
     * 店铺城市（shp_detail表city）
     */
    private String shopCity;
    /**
     * 索引类型（shp_detail表city）
     */
    private String shopProvince;

    /**
     * 锁单（0：锁单，1：解锁）
     */
    private String proLockState;

    /**
     * 是否逻辑删除;0:false:不删除;1:true
     */
    private Integer proDel;

    /**
     * 商品委托方信息
     */
    private String customerInfo;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店铺编号
     */
    private String shopNumber;
    /**
     * 产品属性表的code
     */
    private Integer proAttributeCode;
    /**
     * 用户id
     */
    private String proUserId;
    /**
     * 入库时间
     */
    private String insertTime;
    private String autoNumber;

    private String share;
    /**
     *商家联盟状态
     */
    private String unionState;

    private Integer proShowStatus;
    /**
     * 寄卖传送类型
     */
    private String conveyState;
    private String contact;

    /**
     * 公价id
     */
    private Integer publicId;

    /**
     * 搜索条件字段
     */
    private String searchCondition;

    /**
     * 商品总数量
     */
    private Integer totalNum;

    /**
     * 卖出库存
     */
    private Integer saleNum;

    /**
     * 商品所属店铺过期时间
     */
    private String shopPayEndTime;


    private String showTime;
}
