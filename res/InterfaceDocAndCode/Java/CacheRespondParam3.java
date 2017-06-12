


/**买家或者卖家关联第三方信息表3*/
public class CacheRespondParam3{
/** 系统用户号 备注:key*/
public int systemNo;
/** 系统类别0:买家1：卖家2：批发商 备注:*/
public select systemTyoe;
/** 第三方代号类型0：微信OPENID1：安卓设备号2：IOS设备号 备注:*/
public select linkType;
/** 第三方代号 如微信公众号对应的OPENID或者系统设备号 备注:*/
public String linkNo;
/** 关联时间格式：yyyymmdd hh24miss 备注:*/
public String linkTime;
}


