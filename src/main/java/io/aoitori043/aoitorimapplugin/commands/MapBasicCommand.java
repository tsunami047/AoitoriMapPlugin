package io.aoitori043.aoitorimapplugin.commands;

import io.aoitori043.aoitorimapplugin.AoitoriMapPlugin;
import io.aoitori043.aoitorimapplugin.business.OverlayManager;
import io.aoitori043.aoitorimapplugin.config.ImageEncryptor;
import io.aoitori043.aoitorimapplugin.config.MapConfigHandler;
import io.aoitori043.aoitorimapplugin.config.mapper.OverlayMapper;
import io.aoitori043.aoitorimapplugin.database.MapDatabaseClient;
import io.aoitori043.aoitorimapplugin.database.MapPlayerProfile;
import io.aoitori043.aoitorimapplugin.network.NetworkImpl;
import io.aoitori043.aoitorimapplugin.network.dto.OperateMapDataDTO;
import io.aoitori043.aoitorimapplugin.network.dto.RenderWorldDataDTO;
import io.aoitori043.aoitoriproject.command.*;
import io.aoitori043.syncdistribute.rmi.data.PersistentDataAccess;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.List;


/**
 * @Author: natsumi
 * @CreateTime: 2024-03-23  13:29
 * @Description: ?
 */
@BasicCommandParameter
public class MapBasicCommand extends BasicCommand {

    public MapBasicCommand(JavaPlugin plugin) {
        super(plugin.getName(), plugin);
    }


    public String getPrefix() {
        return MapConfigHandler.instance.pluginPrefix;
    }

    @Override
    public Class<? extends SubCommand>[] getSubCommands() {
        return new Class[]{

        };
    }

    @Override
    public List<String> getAllAlias() {
        return Arrays.asList("map","am");
    }

    @Override
    public void sendMessage(CommandSender sender, String msg) {
        if (sender instanceof Player) {
            sender.sendMessage(msg.replaceAll("&", "§"));
        } else {
            plugin.getLogger().info(msg.replaceAll("§.", ""));
        }
    }

    @Parameter(argument = "open", help = "打开地图")
    @ParameterSpecification(index = 0, tip = "player", type = ParameterSpecification.Type.Player,nullable = true)
    public void open(CommandSender sender, List<SubCommand.ArgumentHelper> arguments) {
        OperateMapDataDTO.builder()
                .type(OperateMapDataDTO.MapOperateType.OPEN)
                .build()
                .send(!arguments.isEmpty() ? arguments.get(0).getAsPlayer() : (Player) sender);
    }

    @Parameter(argument = "close", help = "关闭地图")
    @ParameterSpecification(index = 0, tip = "player", type = ParameterSpecification.Type.Player,nullable = true)
    public void close(CommandSender sender, List<SubCommand.ArgumentHelper> arguments) {
        OperateMapDataDTO.builder()
                .type(OperateMapDataDTO.MapOperateType.CLOSE)
                .build()
                .send(!arguments.isEmpty() ? arguments.get(0).getAsPlayer() : (Player) sender);
    }

    @Parameter(argument = "reload", help = "重载插件配置")
    @ExecutionStartMessage(message = "开始重载...")
    @ExecutionEndMessage(message = "重载完成，耗时 %time%s")
    public void execute(CommandSender sender, List<SubCommand.ArgumentHelper> arguments) {
        MapConfigHandler.load();
    }

    @Parameter(argument = "render", help = "客户端渲染，1->启动，0->关闭")
    @ParameterSpecification(index = 0, tip = "player", type = ParameterSpecification.Type.Player)
    @ParameterSpecification(index = 1, tip = "enable", type = ParameterSpecification.Type.Int)
    public void render(CommandSender sender, List<SubCommand.ArgumentHelper> arguments) {
        OperateMapDataDTO.builder()
                .type(arguments.get(1).getAsInt() == 1 ? OperateMapDataDTO.MapOperateType.START_MAPPING : OperateMapDataDTO.MapOperateType.STOP_MAPPING)
                .build()
                .send(arguments.get(0).getAsPlayer());
    }

    @Parameter(argument = "display", help = "渲染指定地图")
    @ParameterSpecification(index = 0, tip = "player", type = ParameterSpecification.Type.Player)
    @ParameterSpecification(index = 1, tip = "world", type = ParameterSpecification.Type.Text)
    public void display(CommandSender sender, List<SubCommand.ArgumentHelper> arguments) {
        RenderWorldDataDTO.builder()
                .worldName(arguments.get(1).getOriginalArg())
                .build()
                .send(arguments.get(0).getAsPlayer());
    }

    @Parameter(argument = "refresh", help = "刷新显示")
    @ParameterSpecification(index = 0, tip = "player", type = ParameterSpecification.Type.Player,nullable = true)
    public void refresh(CommandSender sender, List<SubCommand.ArgumentHelper> arguments) {
        OperateMapDataDTO.builder()
                .type(OperateMapDataDTO.MapOperateType.REFRESH)
                .build()
                .send(arguments.get(0)!=null ? arguments.get(0).getAsPlayer() : Bukkit.getPlayer(sender.getName()));
    }

    @Parameter(argument = "generateKey", help = "生成密钥")
    @ParameterSpecification(index = 0, tip = "player", type = ParameterSpecification.Type.Player,nullable = true)
    public void generateKey(CommandSender sender, List<SubCommand.ArgumentHelper> arguments) {
        try {
            SecretKey secretKey = ImageEncryptor.generateSecretKey();
            String key = ImageEncryptor.keyToString(secretKey);
            AoitoriMapPlugin.plugin.getLogger().info("复制替换到config.yml的resourcesKey中，新的密钥："+key);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
