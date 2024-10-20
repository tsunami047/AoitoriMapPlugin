package io.aoitori043.aoitorimapplugin.config;

import io.aoitori043.aoitorimapplugin.network.dto.SecretKeyDataDTO;
import org.bukkit.entity.Player;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-20  22:21
 * @Description: ?
 */
public class ImageEncryptor {

    public static SecretKey secretKey;
    public static byte[] byteKey;

    public static void sendEncryptor(Player player){
        SecretKeyDataDTO.builder()
                .key(byteKey)
                .build()
                .send(player);
    }

    public static SecretKey stringToKey(String keyString) {
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public static void load(){
        try {
            secretKey = stringToKey(MapConfigHandler.resourcesKey);
            byteKey = secretKey.getEncoded();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static SecretKey generateSecretKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        return keyGen.generateKey();
    }

    public static String keyToString(SecretKey secretKey) {
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

}
