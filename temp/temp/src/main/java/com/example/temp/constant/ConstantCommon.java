package com.example.temp.constant;

/**
 * @author monkey king
 * @date 2019-12-17 18:08:41
 */
public class

ConstantCommon {

    /**
     * 系统自动初始化时的备注
     */
    public static final String AUTO_REMARK = "系统自动初始化";

    public static final String PAY_CHECK_TIME_KEY = "sys_config:pay:checkTime";

    public static final String ALL = "all";

    public static final String MINUS_NINE = "-9";

    public static final String NEGATIVE_TEN = "-10";

    public static final String MINUS_NINE_NINE = "-99";

    public static final String TWENTY = "20";

    public static final String MINUS_TWO = "-2";

    public static final String MINUS_ONE = "-1";

    public static final Integer MINUS_ONE_INT = -1;

    public static final String ZERO = "0";

    public static final Integer ZERO_INT = 0;

    public static final String ONE = "1";

    public static final Integer ONE_INT = 1;

    public static final String TWO = "2";

    public static final Integer TWO_INT = 2;

    public static final String THREE = "3";

    public static final String YES = "yes";

    public static final String NO = "no";

    public static final String OK = "ok";

    public static final String DEV = "dev";

    public static final String PRO = "pro";

    public static final String PRO_PUB = "pro_pub";


    public static final String TEST = "test";

    public static final String NO_PERM = "noPerm";

    public static final int SEVEN = 7;

    public static final int THIRTY = 30;

    public static final String THIRTY_STR = "30";

    public static final int FORTY = 40;

    public static final int TWELVE = 12;

    public static final int FOUR = 4;

    public static final String FOURSTR = "4";

    public static final String FIVE = "5";

    public static final String SIX = "6";

    public static final Integer SIX_INT = 6;

    public static final Integer MINUS_SIX_INT = -6;

    public static final String TEN_THOUSAND = "10000";

    /**
     * 线上展会的页面跳转code值
     */
    public static final String UNION_SHOP = "unionShop";
    /**
     * 闲鱼的页面跳转code值
     */
    public static final String XY_CODE = "syncXianyu";

    /**
     * 抖店的页面跳转code值
     */
    public static final String DD_CODE = "syncDoudian";

    /**
     * 快手的页面跳转code值
     */
    public static final String KS_CODE = "syncKuaishou";

    /**
     * 鉴定/兜底
     */
    public static final String TRADE_AUTHENTICATE = "tradeAuthenticate";

    /**
     * 维修保养洗护
     */
    public static final String TRADE_MAINTENANCE = "tradeMaintenance";

    /**
     * 中检出证
     */
    public static final String TRADE_EXAMINE = "tradeExamine";

    /**
     * 行业培训
     */
    public static final String TRADE_TRAIN = "tradeTrain";

    /**
     * 线下展会报名
     */
    public static final String TRADE_UNION_APPLY = "tradeUnionApply";

    /**
     * 名表典当
     */
    public static final String TRADE_PAWN = "tradePawn";

    /**
     * 全小写的iOS
     */
    public static final String IOS = "ios";

    /**
     * 全小写的android
     */
    public static final String ANDROID = "android";

    /**
     * 店铺默认封面
     */
    public static final String DEFAULT_SHOP_COVER_IMG = "/default/shopCoverImg.png";

    /**
     * 店铺默认头像
     */
    public static final String DEFAULT_SHOP_HEAD_IMG = "/default/shopHeadImg.png";

    /**
     * 用户默认头像
     */
    public static final String DEFAULT_USER_HEAD_IMG = "/default/headImg.png";

    /**
     * 短期登录有效时间;(天)
     */
    public static final int SHORT_LOGIN_TIME = 3;

    /**
     * 长期登录有效时间;(天)
     */
    public static final int LONG_LOGIN_TIME = 15;

    /**
     * 锁单时间(分钟)
     */
    public static final int LOCK_PRODUCT_MINUTE = 15;

    /**
     * 十
     */
    public static final String TEN = "10";

    public static final Integer TEN_INT = 10;

    /**
     * 二十二
     */
    public static final String TWENTY_TWO = "22";

    /**
     * 阿里云oss域名,容器启动时会为此赋值
     */
    public static String ossDomain;
    /**
     * 运行环境,容器启动时会为此赋值
     */
    public static String springProfilesActive;

    /**
     * 删除状态 - 非删除
     */
    public static final String DEL_OFF = "0";

    /**
     * 删除状态 - 删除
     */
    public static final String DEL_ON = "1";

    /**
     * 是否有效状态 - 无效
     */
    public static final String STATE_OFF = "0";

    /**
     * 是否有效状态 - 有效
     */
    public static final String STATE_ON = "1";

    /**
     * 商品上架 参数
     */
    public static final String PRO_STATE_CODE_UP = "1";
    /**
     * 商品下架 参数
     */
    public static final String PRO_STATE_CODE_DOWN = "0";
    /**
     * 未上架 首次入库
     */
    public static final Integer PRO_STATE_FIRST_PUT = 10;
    /**
     * 上架 首次上架
     */
    public static final Integer PRO_STATE_FIRST_UP = 20;
    /**
     * 未上架 再次入库-上架后人工下架
     */
    public static final Integer PRO_STATE_AGAIN_PUT = 11;
    /**
     * 上架 再次上架
     */
    public static final Integer PRO_STATE_AGAIN_UP = 21;
    /**
     * 2号店铺名称
     */
    public static final String ORD_TWO_SHOP = "10684";

    /**
     * 默认decimal 保留小数点位数
     */
    public static final Integer DEFAULT_DECIMAL_POINT = 2;

    /**
     * 默认decimal 百分数
     */
    public static final Integer HUNDRED_PERCENT = 100;

    /**
     * 默认decimal 0%
     */
    public static final String ZERO_PERCENT = "0.00";

    /**
     * 产品贴士最大长度
     */
    public static final Integer MAN_TIP_LENGTH = 50;


    /**
     * 2号店主键ID
     */
    public static final Integer TOW_SHOP_ID = 10684;


    /**
     * 点击数量
     */
    public static final String SERV_INFO_CLICK_NUM = "serv:info:clickNum:infoId:{INFOID}:servType:(SERVTYPE)";

    /**
     * 访问数量key
     */
    public static final String SERV_INFO_VISIT_KEY = "serv:info:visit:infoId:{INFOID}:servType:(SERVTYPE):userId:[USERID]";

    /**
     * 访问数量
     */
    public static final String SERV_INFO_VISIT_NUM = "serv:info:visitNum:infoId:{INFOID}:servType:(SERVTYPE)";

    /**
     * 点击联系方式数量
     */
    public static final String SERV_INFO_GAIN_PHONE_NUM = "serv:info:gain_phone_num:infoId:{INFOID}:servType:(SERVTYPE)";


    /**
     * key
     */
    public static final String SERV_INFO_DATA_COUNT = "serv:info:data:count:{INFOID}:servType:(SERVTYPE)";


    public static final String SERV_INFO = "serv:info:data:count";

    /**
     * 背景相似度
     */
    public final static float BG_SCORE = 0.5f;

    /**
     * 销售价
     */
    public static final String sale = "sale";

    /**
     * 同行价
     */
    public static final String trade = "trade";


    /**
     * es中传入参数是save
     */
    public static final String ES_SAVE = "save";

    /**
     * es中传入参数是update
     */
    public static final String ES_UPDATE = "update";

}
