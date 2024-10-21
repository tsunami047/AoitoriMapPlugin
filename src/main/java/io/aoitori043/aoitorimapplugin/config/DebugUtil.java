package io.aoitori043.aoitorimapplugin.config;

import io.aoitori043.aoitoriproject.impl.ConfigHandler;
import lombok.Getter;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-21  16:02
 * @Description: ?
 */
public class DebugUtil {

    public enum DebugLevel{
        VERBOSE(0),
        DEVELOP(1),
        PRODUCTION(2);

        @Getter
        final int point;

        DebugLevel(int point) {
            this.point = point;
        }

        public static DebugLevel fromPoint(int point) {
            for (DebugLevel level : values()) {
                if (level.getPoint() == point) {
                    return level;
                }
            }
            throw new IllegalArgumentException("No DebugLevel with point " + point);
        }

    }


    public static void debug(String msg,DebugLevel debugLevel){
        if (debugLevel.point >= MapConfigHandler.debugLevel) {
            System.out.println(msg);
        }
    }
}
