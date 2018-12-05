package com.demo.holder.common.business;

public class AppInterfaceContant {

    /**
     * 用户登录
     */
    public final static String LOGIN = "user/login";

    /**
     * 自动登录
     */
    public final static String AUTOLOGIN = "user/autologin";

    /**
     * 获取微信登录openid
     */
    public final static String OPENID = "user/oauthgetopenid";

    /**
     * 获取微信登录openid
     */
    public final static String LOGINBYOPENID = "user/loginbyopenid";

    public final static String LOGINBYUNIONID = "user/loginbyunionid";

    /**
     *获取短信验证码
     */
    public final static String GETCODE = "user/getcode";

    /**
     *退出登录
     */
    public final static String LOGINOUT = "user/logout";

    /**
     *获取达人信息
     */
    public final static String GETDINFO = "user/getdinfo";
    /**
     * 获取达人关注状态
     */
    public final static String FOCUSSTATUS = "user/focusstatus";
    /**
     * 关注
     */
    public final static String FOUCUSON = "user/focuson";
    /**
     * 取消关注
     */
    public final static String FOUCUSUN = "user/unfocus";

    /**
     * 礼物列表
     */
    public final static String GIFTLIST = "gifts/lists";
    /**
     * 通过环信获取用户
     */
    public final static String GETUIDBYHX = "user/getBy";

    /**
     * 申请达人
     */
    public final static String APPLYTEGLE = "talent/apply";
    /**
     * 修改达人信息
     */
    public final static String EDITTEGLE = "talent/edit";

    /**
     * 订单详情
     */
    public final static String ORDERDETAIL = "order/get";

    /**
     * 取消订单
     */
    public final static String ORDERCANCEL = "order/cancel";

    /**
     * 保存用户信息
     */
    public final static String SAVEUSER = "user/save";
    /**
     * 上传图片，视频
     */
    public final static String PICVIDEOUPLOAD = "album/upload";
    /**
     * 民族
     */
    public final static String NATIONS = "setting/nations";

    /**
     * 节目类型
     */
    public final static String PGTYPES = "setting/programmeTypes";

    /**
     * 节目时长
     */
    public final static String PROGRAMMEMINS = "setting/programmemins";


    /**
     * 上传身份证
     */
    public final static String UPLOAD_IDCARD_PHOTO = "uploads/idcardphoto";

    /**
     * 上传图片
     */
    public final static String UPLOAD_PHOTO = "uploads/photos";

    /**
     * 上传视频
     */
    public final static String UPLOAD_VIDEOS = "uploads/videos";

    /**
     * 我的关注列表
     */
    public final static String MYFOCUSLIST = "user/followed";

    /**
     * 评价发表
     */
    public final static String ORDER_COMMMENT = "order/comment";
    /**
     * 用户/订单 评价列表
     */
    public final static String ORDER_COMMENTLIST = "order/getcommentlist";

    /**
     * 查询申请达人信息
     */
    public final static String QUERY_APPLYINFO = "talent/checkhasapply";
    /**
     * 查询申请达人图片
     */
    public final static String GETAPPLYPHOTOS = "album/getApplyPhotos";

    /**
     * 查询申请达人视频
     */
    public final static String GETAPPLYVIDEOS = "album/getApplyVideos";
    /**
     * 设置时间
     */
    public final static String SETDATE= "user/setdate";

    /**
     * 我的资产
     */
    public final static String PROPERTY= "my/property";

    /**
     * 收益明细
     */
    public final static String INCOME= "my/income";

    /**
     * 申请提现
     */
    public final static String WITHDRAWAPPLY= "withdraw/apply";

    /**
     * 提现明细
     */
    public final static String WITHDRAWDETAIL= "my/withdraw";

    /**
     * 消息通知
     */
    public final static String MESSAGELIST= "message/lists";
    /**
     * 虚拟电话
     */
    public final static String PRIVATENUMBER= "privatenumber/get";
    /**
     * 我的团队
     */
    public final static String PARTNERINDEX = "partner/index";
    /**
     * 团队排行
     */
    public final static String PARTNERRANKING = "partner/ranking";
}
