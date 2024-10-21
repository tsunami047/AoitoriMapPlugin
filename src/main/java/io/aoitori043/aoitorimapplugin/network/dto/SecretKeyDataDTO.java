package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.config.ImageEncryptor;
import io.aoitori043.aoitorimapplugin.config.MapConfigHandler;
import io.aoitori043.aoitorimapplugin.database.MapDatabaseClient;
import io.aoitori043.aoitorimapplugin.database.MapPlayerProfile;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import static io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType.SECRET_KEY;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-20  22:58
 * @Description: ?
 */
public class SecretKeyDataDTO extends DataDTO {

    byte[] key;

    @Builder
    public SecretKeyDataDTO(byte[] key) {
        super(SECRET_KEY);
        this.key = key;
    }

    public boolean areEqual(byte[] array1, byte[] array2) {
        if (array1.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onServerReceived(Player player) {
        if (!MapConfigHandler.enableValidation) return;
        if (key ==null || !areEqual(key, ImageEncryptor.byteKey)){
            if (player.isOp()){
                return;
            }
            player.kickPlayer("连接中止");
        }else{
            MapPlayerProfile mapPlayerProfile = MapDatabaseClient.getMapPlayerProfile(player.getName());
            if (mapPlayerProfile == null) return;
            mapPlayerProfile.setVerified(true);
        }
    }
}
