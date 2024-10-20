package io.aoitori043.aoitorimapplugin.network;

import org.junit.jupiter.api.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-20  22:50
 * @Description: ?
 */
public class SecretKeyTest {

    @Test
    public void keyToString() throws Exception {
        // 获取密钥的字节数组
        SecretKey secretKey = generateSecretKey();
        byte[] keyBytes = secretKey.getEncoded();

        // 将字节数组进行 Base64 编码
        String s = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println(s);
    }

    // 生成 AES 密钥
    public SecretKey generateSecretKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);  // 使用 128 位密钥
        return keyGen.generateKey();
    }

}
