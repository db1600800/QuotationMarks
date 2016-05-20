package com.compoment.addfunction.iphone;

public class HttpAndError {

	
  public void httpH()
  {
	  String m="";
	  


	  m+="#import <UIKit/UIKit.h>\n";

	  m+="@protocol ServiceInvokerDelegate;\n";

	  m+="@interface ServiceInvoker : NSObject\n";
	  m+="{\n";
	  m+="bool isContinueOpenLoading;//不关闭Loading\n";
	  m+="bool isNoHasLoading;//没有Loading\n";
	  m+="}\n";
	  
	  m+="@property (strong, nonatomic) id<ServiceInvokerDelegate>  delegate;\n";
	  m+="@property (strong, nonatomic) UIViewController *viewController;\n";
	  m+="-(void) callWebservice:(NSDictionary*)businessparam otherParam:(NSDictionary*)otherParam  formName:(NSString*)formName delegate:(id<ServiceInvokerDelegate>)delegate  viewController:(UIViewController*)viewController ; \n";
	  m+="@end\n";



	  m+="@protocol ServiceInvokerDelegate<NSObject>\n";
	  m+="@required\n";
	  m+="//业务请求返回数据\n";
	  m+="-(void) ReturnData:(MsgReturn*)msgReturn;\n";
	  m+="-(void) ReturnError:(MsgReturn*)msgReturn;\n";
	  m+="@end\n";

  }
  
  
  public void htmlM()
  {
	  
	  String m="";

	  m+="#import \"ServiceInvoker.h\"\n";

	  m+="#import \"SVProgressHUD.h\"\n";
	  m+="#import \"PromptError.h\"\n";
	  m+="#import \"JSONKit.h\"\n";

	  m+="#include <arpa/inet.h>\n";
	  m+="#include <net/if.h>\n";
	  m+="#include <ifaddrs.h>\n";
	 
	 

	  m+="@implementation ServiceInvoker\n";

	  m+="NSTimer *timmer;\n";

	  m+="-(void) callWebservice:(NSDictionary*)businessparam otherParam:(NSDictionary*)otherParam  formName:(NSString*)formName delegate:(id<ServiceInvokerDelegate>)delegate  viewController:(UIViewController*)viewController \n";
	  m+="{\n";
	  m+="    isContinueOpenLoading=[otherParam objectForKey:@\"isContinueOpenLoading\"];\n";
	  m+="    isNoHasLoading=[otherParam objectForKey:@\"isNoHasLoading\"] ;\n";
	  m+="    self.viewController=viewController;\n";
	  m+="    self.delegate=delegate;\n";
	  m+="    \n";

	  m+="     dispatch_async(dispatch_get_main_queue(), ^{\n";
	  m+="       \n";
	  m+="         if ([SVProgressHUD isVisible]==true) {\n";
	  m+="             \n";
	  m+="         }else\n";
	  m+="         {\n";
	  m+="             if (sysBaseInfo.isNoHasLoading==true) {\n";
	  m+="                 \n";
	  m+="             }else\n";
	  m+="             {\n";
	  m+="             [SVProgressHUD showWithStatus:@\"努力加载中...\" maskType:SVProgressHUDMaskTypeClear];\n";
	  m+="             }\n";
	  m+="         }\n";
	  m+="     });\n";


	  m+="    \n";
	  m+="    NSMutableDictionary *tranBodyDic=[[NSMutableDictionary alloc] init];\n";
	  m+="    tranBodyDic=business;\n";

	  m+="   \n";
	  m+="//    headMap.put(SendMsgHead.H_BRCH_NO, \"000000\");\n";
	  m+="//    headMap.put(SendMsgHead.H_SFILE_NUM, \"0000\");\n";
	  m+="//    headMap.put(SendMsgHead.H_CHANNEL_TRACE, \"\");\n";
	  m+="//    headMap.put(SendMsgHead.H_CHANNEL_NO, DeviceInfo.channelNo);\n";
	  m+="//    headMap.put(SendMsgHead.H_IP_ADDR, DeviceInfo.deviceIP);\n";
	  m+="//    headMap.put(SendMsgHead.H_TTY, CarServiceAgentApplication.appServiceInvoker.mLogicID);\n";
	  m+="//    headMap.put(SendMsgHead.H_SEQ_NO, \"\");\n";
	  m+="//    headMap.put(SendMsgHead.H_AUTH_OPER_NO, \"\");\n";
	  m+="//    headMap.put(SendMsgHead.H_OPER_NO, \"00\");\n";
	  m+="//    headMap.put(SendMsgHead.H_OPER_NO_NEW, OperatorMsg.oprID);\n";
	  m+="//    headMap.put(SendMsgHead.H_BRCH_NO_NEW, OperatorMsg.organID);\n";
	  m+="    \n";
	  m+="    DeviceInfo *deviceInfo=[DeviceInfo sharedInstance];\n";
	  m+="    \n";
	  m+="    NSMutableDictionary *tranheadDic=[[NSMutableDictionary alloc] init];\n";
	  m+="    // 1	局号(包头使用)	H_BRCH_NO	字符	7\n";
	  m+="    [tranheadDic setValue:@\"0000000\" forKey:@\"H_BRCH_NO\"];\n";
	  m+="    // 2	柜员号(包头使用)	H_OPER_NO	字符	2\n";
	  m+="    [tranheadDic setValue:@\"00\" forKey:@\"H_OPER_NO\"];\n";
	  m+="    // 3	交易流水号	H_SEQ_NO	数字\n";
	  m+="    [tranheadDic setValue:@\"\" forKey:@\"H_SEQ_NO\"];\n";
	  m+="    //  4	IP地址	H_IP_ADDR	字符	15\n";
	  m+="    [tranheadDic setValue:deviceInfo.H_IP_ADDR forKey:@\"H_IP_ADDR\"];\n";
	  m+="    //5	终端号(不带/dev/tty)	H_TTY	字符	100\n";
	  m+="    [tranheadDic setValue:deviceInfo.LogicDeviceNo forKey:@\"H_TTY\"];\n";
	  m+="    // 6	授权柜员号	H_AUTH_OPER_NO	字符	4\n";
	  m+="    [tranheadDic setValue:@\"\" forKey:@\"H_AUTH_OPER_NO\"];\n";
	  m+="    //7	渠道流水号	H_CHANNEL_TRACE	字符	12\n";
	  m+="    [tranheadDic setValue:@\"\" forKey:@\"H_CHANNEL_TRACE\"];\n";
	  m+="    //  8	渠道标识	H_CHANNEL_NO	字符	2\n";
	  m+="    [tranheadDic setValue:@\"06\" forKey:@\"H_CHANNEL_NO\"];\n";
	  m+="    //  9	综合服务平台支局号	H_BRCH_NO_NEW	字符	8\n";
	  m+="    [tranheadDic setValue:deviceInfo.H_BRCH_NO_NEW forKey:@\"H_BRCH_NO_NEW\"];\n";
	  m+="    // 10	综合服务平台柜员号码	H_OPER_NO_NEW	字符	4\n";
	  m+="    [tranheadDic setValue:deviceInfo.H_OPER_NO_NEW forKey:@\"H_OPER_NO_NEW\"];\n";
	  m+="    // 11	上送文件数量循环域开始	H_SFILE_NUM	数字\n";
	  m+="    [tranheadDic setValue:@\"0000\" forKey:@\"H_SFILE_NUM\"];\n";
	  m+="    // 12	不带路径上送文件名	H_SEND_FILE	字符	60\n";
	  m+="   // [tranheadDic setValue:@\"440000004509\" forKey:@\"H_SEND_FILE\"];\n";
	  m+="    \n";
	  m+="    \n";
	  m+="    \n";
	  m+="   // NSMutableDictionary *sendDataDic=[[NSMutableDictionary alloc] init];\n";
	  m+="    [tranBodyDic setValue:tranheadDic forKey:@\"SNDMSG_HEAD\"];\n";
	  m+="//      [sendDataDic setValue:tranBodyDic forKey:@\"tranBody\"];\n";
	  m+="//    \n";
	  m+="//    NSMutableDictionary *fullDic=[[NSMutableDictionary alloc] init];\n";
	  m+="//    [fullDic setValue:sendDataDic forKey:@\"sendData\"];\n";
	  m+="    \n";
	  m+="    \n";
	  m+="    \n";
	  m+="   \n";
	  m+="  \n";
	  m+="          serviceInvoker=[[ServiceInvoker alloc]init];\n";

	  m+="  \n";
	  m+="    [serviceInvoker  setDelegate:self];\n";

	  m+="     NSLog(@\"hobby request %@ %@\",formName,tranBodyDic);\n";
	  m+="    [serviceInvoker callWebservice:tranBodyDic formName:formName delegate:self];\n";

	  m+="    \n";
	  m+="    timmer  = [NSTimer scheduledTimerWithTimeInterval:35 target:self selector:@selector(timeFire) userInfo:nil repeats:NO];\n";

	  m+="}\n";

	  m+="-(void)timeFire\n";
	  m+="{\n";
	  m+="       [SVProgressHUD dismiss];\n";
	  m+="    \n";
	  m+="//    MsgReturn *msgReturn= [[MsgReturn alloc]init ];\n";
	  m+="//    msgReturn.errorCode=ERROR_TIMEOUT_ERROR ;\n";
	  m+="//    msgReturn.errorDesc=ERROR_TIMEOUT;\n";
	  m+="//    msgReturn.errorType=@\"01\";\n";
	  m+="//    [PromptError changeShowErrorMsg:msgReturn title:@\"\"  viewController:self block:^(BOOL OKCancel)\n";
	  m+="//     {\n";
	  m+="//         if (OKCancel) {\n";
	  m+="//             \n";
	  m+="//         }else\n";
	  m+="//         {\n";
	  m+="//             \n";
	  m+="//         }\n";
	  m+="//         return ;\n";
	  m+="//     }\n";
	  m+="//     ];\n";
	  m+="    \n";
	  m+=" \n";

	  m+="}\n";


	  m+="//业务请求返回错误\n";
	  m+="-(void)serviceInvokerError:(MsgReturn*)msgReturn\n";
	  m+="{\n";
	  m+="    if (msgReturn==nil ) {\n";
	  m+="        return;\n";
	  m+="    }\n";
	  m+="    \n";
	  m+="       [timmer invalidate];\n";
	  m+="     timmer=nil;\n";
	  m+="      dispatch_async(dispatch_get_main_queue(), ^{\n";
	  m+="        [SVProgressHUD dismiss];\n";
	  m+="      });\n";
	  m+="  \n";
	  m+="  \n";
	  m+="    \n";
	  m+="   if(msgReturn.formName!=nil && [msgReturn.errorCode isEqualToString:ERROR_FAILED])\n";
	  m+="    {//交易失败\n";
	  m+="        \n";
	  m+="        \n";
	  m+="    }\n";
	  m+="    \n";
	  m+="    else\n";
	  m+="    {\n";
	  m+="        //网络错误 服务器错误  传输格式错误\n";
	  m+="        if([msgReturn.errorCode isEqualToString:ERROR_DATA_FORMAT_ERROR] || [msgReturn.errorCode isEqualToString:ERROR_SERVICE_IN_ERROR] || [msgReturn.errorCode isEqualToString:ERROR_NOT_NET])\n";
	  m+="            \n";
	  m+="        {\n";
	  m+="           \n";
	  m+="            \n";
	  m+="          \n";
	  m+="            [PromptError changeShowErrorMsg:msgReturn title:@\"\"  viewController:self block:^(BOOL OKCancel)\n";
	  m+="             {\n";
	  m+="                 if (OKCancel) {\n";
	  m+="                     \n";
	  m+="                 }else\n";
	  m+="                 {\n";
	  m+="                     \n";
	  m+="                 }\n";
	  m+="                 return ;\n";
	  m+="             }\n";
	  m+="             ];\n";

	  m+="        }\n";
	  m+="    }\n";
	  m+="       [self.delegate ReturnError:msgReturn];\n";
	  m+="    \n";
	  m+="    NSLog(@\"%@ %@\",msgReturn.formName,msgReturn.errorDesc);\n";
	  m+="    \n";
	  m+="}\n";

	  m+="//业务请求返回数据\n";
	  m+="-(void)serviceInvokerReturnData:(MsgReturn*)msgReturn\n";
	  m+="{\n";
	  m+="    [timmer invalidate];\n";
	  m+="    timmer=nil;\n";
	  m+="    \n";
	  m+="    if([msgReturn.errorCode isEqualToString:ERROR_SUCCESS])\n";
	  m+="    {//callWebservice成功\n";
	  m+="        \n";
	  m+="        if ([msgReturn.formName isEqual:@\"JY0052\"]||[msgReturn.formName isEqual:@\"JY0049\"]) {\n";
	  m+="            \n";
	  m+="        }\n";
	  m+="        else\n";
	  m+="        {\n";
	  m+="              dispatch_async(dispatch_get_main_queue(), ^{\n";
	  m+="                  if (isContinueOpenLoading) {//true开着\n";
	  m+="                      \n";
	  m+="                  }else\n";
	  m+="                  {//false 关闭\n";
	  m+="                  [SVProgressHUD dismiss];\n";
	  m+="                  }\n";
	  m+="               \n";
	  m+="              });\n";
	  m+="      \n";
	  m+="        }\n";
	  m+="        \n";
	  m+="        \n";
	  m+="        \n";
	  m+="        NSMutableDictionary* map=msgReturn.map;\n";
	  m+="        NSString *businessParamString=[map objectForKey:@\"businessParam\"];\n";
	  m+="        NSDictionary *businessParamDic=[businessParamString objectFromJSONString];\n";
	  m+="        NSString *tempdata=[businessParamDic objectForKey:@\"data\"];\n";
	  m+="        NSDictionary *kk=[tempdata objectFromJSONString];\n";
	  m+="        NSDictionary  *data=[kk objectForKey:@\"kk\"];\n";
	  m+="        \n";
	  m+="       \n";
	  m+="      \n";
	  m+="        \n";
	  m+="       \n";
	  m+="        NSMutableDictionary *returnDataDic=[data  objectForKey:@\"returnData\"];\n";
	  m+="        \n";
	  m+="         NSMutableDictionary *tempData=[NSMutableDictionary dictionaryWithDictionary:returnDataDic];\n";
	  m+="        \n";
	  m+="        [tempData setObject:returnDataDic forKey:@\"returnBody\"];\n";
	  m+="        \n";
	  m+="         NSMutableDictionary *tempData2=[[NSMutableDictionary alloc] init];\n";
	  m+="        \n";
	  m+="        [tempData2 setObject:tempData forKey:@\"returnData\"];\n";
	  m+="         msgReturn.map=tempData2 ;\n";
	  m+="        \n";
	  m+="        NSMutableDictionary *returnHeadDic=[returnDataDic objectForKey:@\"RCVMSG_HEAD\"];\n";
	  m+="        NSString *respCode=[returnHeadDic objectForKey:@\"HOST_RET_ERR\"];\n";
	  m+="        NSString *respDesc=[returnHeadDic objectForKey:@\"HOST_RET_MSG\"];\n";
	  m+="        \n";
	  m+="        NSMutableDictionary *returnBodyDic=[returnDataDic objectForKey:@\"returnBody\"];\n";
	  m+="    \n";
	  m+="        \n";
	  m+="        if (respCode!=nil && ![respCode isEqualToString:@\"\"] &&![respCode isEqualToString:@\"000000\"]) {\n";
	  m+="            \n";
	  m+="        \n";
	  m+="        dispatch_async(dispatch_get_main_queue(), ^{\n";
	  m+="            [SVProgressHUD dismiss];\n";
	  m+="            \n";
	  m+="        });\n";
	  m+="        \n";
	  m+="            \n";
	  m+="            MsgReturn *msgReturn=[[MsgReturn alloc] init];\n";
	  m+="            msgReturn.errorCode=respCode;\n";
	  m+="            msgReturn.errorDesc=respDesc;\n";
	  m+="            msgReturn.errorType=@\"02\";\n";
	  m+="        //NSLog(@\"%@ %@\",msgReturn.errorCode,msgReturn.errorDesc);\n";
	  m+="        \n";
	  m+="        \n";
	  m+="        \n";
	  m+="        [PromptError changeShowErrorMsg:msgReturn title:nil viewController:self.viewController block:^(BOOL OKCancel){\n";
	  m+="            if (OKCancel) {\n";
	  m+="                //[self.delegate ReturnError:msgReturn];\n";
	  m+="            }\n";
	  m+="            \n";
	  m+="        } ];\n";
	  m+="            \n";
	  m+="            [SVProgressHUD dismiss];\n";
	  m+="            \n";
	  m+="            \n";
	  m+="            return;\n";
	  m+="        }\n";
	  m+="     \n";
	  m+="        [self.delegate ReturnData:msgReturn];\n";
	  m+="        \n";
	  m+="        \n";
	  m+="    }else{//错误码 非0000\n";
	  m+="    \n";
	  m+="           dispatch_async(dispatch_get_main_queue(), ^{\n";
	  m+="                              [SVProgressHUD dismiss];\n";
	  m+="               \n";
	  m+="           });\n";
	  m+="        \n";
	  m+="        NSLog(@\"%@ %@\",msgReturn.errorCode,msgReturn.errorDesc);\n";
	  m+="        \n";
	  m+="  \n";
	  m+="  \n";
	  m+="        [PromptError changeShowErrorMsg:msgReturn title:nil viewController:self.viewController block:^(BOOL OKCancel){\n";
	  m+="            if (OKCancel) {\n";
	  m+="                      [self.delegate ReturnError:msgReturn];\n";
	  m+="            }\n";
	  m+="        \n";
	  m+="        } ];\n";
	  m+="    }\n";
	  m+="    \n";
	  m+="}\n";



	  m+="//实现一个创建单例对象的类方法\n";




	  m+="//这是单例对象遵循<NSCopying>协议时需要实现的方法\n";
	  m+="-(id) copyWithZone: (struct _NSZone *)zone{\n";
	  m+="    return self;\n";
	  m+="}\n";


	  m+="//- (NSString *)localIPAddress\n";
	  m+="//{\n";
	  m+="//    NSString *localIP = nil;\n";
	  m+="//    struct ifaddrs *addrs;\n";
	  m+="//    if (getifaddrs(&addrs)==0) {\n";
	  m+="//        const struct ifaddrs *cursor = addrs;\n";
	  m+="//        while (cursor != NULL) {\n";
	  m+="//            if (cursor->ifa_addr->sa_family == AF_INET && (cursor->ifa_flags & IFF_LOOPBACK) == 0)\n";
	  m+="//            {\n";
	  m+="//                //NSString *name = [NSString stringWithUTF8String:cursor->ifa_name];\n";
	  m+="//                //if ([name isEqualToString:@\"en0\"]) // Wi-Fi adapter\n";
	  m+="//                {\n";
	  m+="//                    localIP = [NSString stringWithUTF8String:inet_ntoa(((struct sockaddr_in *)cursor->ifa_addr)->sin_addr)];\n";
	  m+="//                    break;\n";
	  m+="//                }\n";
	  m+="//            }\n";
	  m+="//            cursor = cursor->ifa_next;\n";
	  m+="//        }\n";
	  m+="//        freeifaddrs(addrs);\n";
	  m+="//    }\n";
	  m+="//    return localIP;\n";
	  m+="//}\n";


	  m+="@end\n";

  }
}
