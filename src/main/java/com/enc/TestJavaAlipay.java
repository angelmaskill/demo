package com.enc;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

/**
 * @Author: AngelMa
 * @Description:
 * @Date: Created on 2021/6/6 4:44 下午
 * @Modified By:
 */
public class TestJavaAlipay {
    public static void main(String[] args) throws AlipayApiException {
        String publicKey = AlipaySignature.getAlipayPublicKey(
            "/Users/xxx/appCertPublicKey_2021002148622857.crt");
        System.out.println("应用公钥数据：" + publicKey);
    }
}
