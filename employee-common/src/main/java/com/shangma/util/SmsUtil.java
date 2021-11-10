package com.shangma.util;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.*;
public class SmsUtil {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static void sendSms(String phone,String code){
        SendSmsResponse sendSmsResponse = null;
        try {
            com.aliyun.dysmsapi20170525.Client client = SmsUtil.createClient("LTAI5tMx133a5mk3UuChTgvX", "WOKfBJgsuc7MHFbAXzvy1eBq3k3eY8");
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(phone)
                    .setSignName("禾十六美肌整骨社")
                    .setTemplateCode("SMS_172738690")
                    .setTemplateParam("{\"code\":"+code+"}");
            // 复制代码运行请自行打印 API 的返回值
            sendSmsResponse = client.sendSms(sendSmsRequest);
            System.out.println(sendSmsResponse.getBody().getCode());
            System.out.println(sendSmsResponse.getBody().getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
