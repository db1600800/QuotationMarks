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
	  m+="NSString *formName;//交易号 接口号\n";
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
	  m+="    self.formName=formName;\n";
	  m+="    \n";

	  m+="     dispatch_async(dispatch_get_main_queue(), ^{\n";
	  m+="       \n";
	  m+="         if ([SVProgressHUD isVisible]==true) {\n";
	  m+="             \n";
	  m+="         }else\n";
	  m+="         {\n";
	  m+="             if (isNoHasLoading==true) {\n";
	  m+="                 \n";
	  m+="             }else\n";
	  m+="             {\n";
	  m+="             [SVProgressHUD showWithStatus:@\"努力加载中...\" maskType:SVProgressHUDMaskTypeClear];\n";
	  m+="             }\n";
	  m+="         }\n";
	  m+="     });\n";


	  m+="    \n";
	  m+="    NSMutableDictionary *sendDataDic=[[NSMutableDictionary alloc] init];\n";
	  m+="    NSMutableDictionary *headDic=[[NSMutableDictionary alloc] init];\n";
	  m+="    [sendDataDic setValue:headDic forKey:@\"Head\"];\n";
	  m+="    [sendDataDic setValue:businessparam forKey:@\"Body\"];\n";
	
	  m+="     NSLog(@\"hobby request %@ %@\",formName,sendDataDic);\n";
	  m+="    [serviceInvoker callWebservice:tranBodyDic formName:formName delegate:self];\n";

	  m+="    \n";
	  m+="    timmer  = [NSTimer scheduledTimerWithTimeInterval:35 target:self selector:@selector(timeFire) userInfo:nil repeats:NO];\n";

	  m+="}\n";
	  
	  
	  
	  
	    m+="/**\n";
		m+=" * 取服务器RSA公钥\n";
		m+=" */\n";
		m+="-(void) rsaPublicKey:(NSString*)appId appVersion:(NSString*)appVersion {\n";
		m+="    \n";
		m+="    self.formName=@\"rsaPublicKey\";\n";
		m+="    \n";

		m+="    NSString *appID=appId;\n";
		m+="    NSString *appVersion=appVersion;\n";
		m+="    \n";
		m+="    \n";
		m+="    // 获取服务器RSA公钥报文组装\n";
		m+="    NSMutableDictionary *rootParam = [[NSMutableDictionary alloc ] init];\n";
		m+="    NSMutableDictionary *jsonBusinessParam =  [[NSMutableDictionary alloc ] init];\n";
		m+="    [rootParam setValue:@\"rsaPublicKeyDeepProc\" forKey:@\"action\"];//rsaPublicKeyDeepProc   rsaPublicKey\n";
		m+="    [jsonBusinessParam setValue:appID forKey:@\"appId\"];// Appid\n";
		m+="    [jsonBusinessParam setValue:appVersion forKey:@\"appVer\"];// App版本\n";
		m+="    \n";
		m+="    [rootParam setValue:jsonBusinessParam forKey:@\"param\"];\n";
		m+="    \n";
		m+="    [[GSNetService sharedInstance] sendMsg:rootParam toServerOnFormName:@\"rsaPublicKey\" withDelegate:self];\n";
		m+="    \n";
		m+="}\n\n";
		
		
	  
		m+="//app注册 （ 告诉服务器appId appVersion  appRsa公钥 ）\n";
		m+="-(void) appRegist\n";
		m+="{\n";
		m+="    self.formName=@\"appRegist\";\n\n";
		
		m+="    RSAUtil *rsaUtil = [RSAUtil shareInstance];\n";
		m+="    \n";
		m+="    NSMutableDictionary *rootParam = [[NSMutableDictionary alloc ] init];\n";
		m+="    NSMutableDictionary *jsonBusinessParam =  [[NSMutableDictionary alloc ] init];\n";
		
		m+="    [rootParam setValue:@\"appSignIn\" forKey:@\"action\"];\n";
		m+="    [jsonBusinessParam setValue:appID forKey:@\"appId\"];// Appid\n";
		m+="    [jsonBusinessParam setValue:appVersion forKey:@\"appVer\"];// App版本\n";
		m+="    [jsonBusinessParam setValue:publicKeyStringX509ServerVer forKey:@\"keyVer\"];\n";
		m+="    \n\n";
		
		m+="    NSMutableDictionary *message =  [[NSMutableDictionary alloc ] init];\n";
		m+="    [message setValue:publicKeyStringX509 forKey:@\"appKey\"];\n";
		m+="    \n";
	

		m+="    \n";
		m+="    //Rsa只含公钥对象\n";
		m+="    RSA *publicRSA=[rsaUtil string2PublickeyFormartX509:publicKeyStringX509Server ];\n";
		m+="    //用服务器公钥加密app公钥字符串,再传输\n";
		m+="    NSData *encryptData = [rsaUtil encryptLongString:KeyTypePublic rsa:publicRSA paddingType:RSA_PADDING_TYPE_PKCS1 plainText:[self dic2jsonString:message ] usingEncoding:NSUTF8StringEncoding];\n";
		m+="    NSString *encryptString =[encryptData base64EncodedString];\n";
		m+="    \n";
		m+="    \n";
		m+="    [jsonBusinessParam setValue:encryptString forKey:@\"message\"];\n";
		m+="    \n";
		m+="    [rootParam setValue:jsonBusinessParam forKey:@\"param\"];\n";
		m+="    \n";
		m+="    [[GSNetService sharedInstance] sendMsg:rootParam toServerOnFormName:@\"appSignIn\" withDelegate:self];\n";
		m+="    \n";
		m+="}\n";
		
		
		m+="-(void) checkUpdates:(NSString*)appId appVersion:(NSString*)appVersion{\n";
		m+="    \n";
		m+="    Sql *sql=[[Sql alloc ]init];\n";
		m+="    \n";
		m+="    NSString *oldConfigFileVersion=[sql selectPM_SIGNSERVICE_CONFIGVERSION];\n";
		m+="    \n";
		m+="    if(oldConfigFileVersion==nil || [oldConfigFileVersion isEqualToString:@\"\"])\n";
		m+="    {\n";
		m+="        oldConfigFileVersion=@\"19000101.0.1\";\n";
		m+="    }\n";
		m+="    \n";
		m+="    self.formName=@\"checkUpdates\";\n";
		m+="    \n";
		m+="    NSUserDefaults *userDefault=[NSUserDefaults standardUserDefaults];\n";
		m+="    [userDefault setObject:appId forKey:@\"appId\"];\n";
		m+="    [userDefault setObject:appVersion forKey:@\"appVer\"];\n";
		m+="    \n";
		m+="    NSString *appID=appId;//[userDefault objectForKey:@\"appId\"];\n";
		m+="    NSString *appVer=appVersion;//[userDefault objectForKey:@\"appVer\"];\n";
		m+="    \n";
		m+="    // 获取接入平台RSA公钥报文组装\n";
		m+="    NSMutableDictionary *rootParam = [[NSMutableDictionary alloc ] init];\n";
		m+="    \n";
		m+="    [rootParam setValue:@\"startup\" forKey:@\"action\"];\n";
		m+="    [rootParam setValue:appID forKey:@\"appId\"];// Appid\n";
		m+="    [rootParam setValue:appVer forKey:@\"appVersion\"];// App版本\n";
		m+="    [rootParam setValue:oldConfigFileVersion forKey:@\"appConfigVersion\"];// App版本\n";
		m+="    [rootParam setValue:@\"\" forKey:@\"deviceId\"];// App版本\n";
		m+="    \n";
		m+="    NSLog(@\"checkupdates request :%@\",rootParam);\n";
		m+="    \n";
		m+="    [[GSNetService sharedInstance] sendMsg:rootParam toServerOnFormName:@\"checkUpdates\" withDelegate:self];\n";
		m+="    \n";
		m+="    \n";
		m+="    //http://202.105.44.4:8001/services/ChinaPostService?wsdl\n";
		m+="    \n";
		m+="    \n";
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
