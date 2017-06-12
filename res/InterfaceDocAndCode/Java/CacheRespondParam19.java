


/**消息表19*/
public class CacheRespondParam19{
/** 主键 备注:key*/
public int messageId;
/** 内容 备注:*/
public String messageDetail;
/** 01：业务通知02：系统变更通知03：业务进展通知04：其它通知 备注:*/
public select messageType;
/** 0：未读 1：已读 备注:*/
public int readMessageFlag;
/** 系统用户 备注:*/
public int systemNo;
/** 消息渠道01：微信02：安卓03：IOS 备注:*/
public String messageChannel;
/** 消息链接类型 备注:*/
public String messageLinkType;
/** 消息链接业务参数 备注:*/
public String messageLinkPara;
}


