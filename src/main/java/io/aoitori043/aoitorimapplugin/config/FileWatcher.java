package io.aoitori043.aoitorimapplugin.config;

import io.aoitori043.aoitorimapplugin.AoitoriMapPlugin;
import io.aoitori043.aoitorimapplugin.business.OverlayManager;
import io.aoitori043.aoitorimapplugin.config.mapper.OverlayMapper;
import io.aoitori043.aoitorimapplugin.database.MapDatabaseClient;
import io.aoitori043.aoitorimapplugin.database.MapPlayerProfile;
import io.aoitori043.aoitorimapplugin.network.NetworkImpl;
import io.aoitori043.aoitorimapplugin.network.dto.OperateMapDataDTO;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class FileWatcher {

    private WatchService watchService;
    private Path rootDirectory;
    private Map<WatchKey, Path> keys;

    public FileWatcher(String directoryPath) throws IOException {
        this.rootDirectory = Paths.get(directoryPath);
        this.watchService = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<>();
        registerAll(rootDirectory);
    }

    private void registerAll(final Path startPath) throws IOException {
        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDir(dir);
                return FileVisitResult.CONTINUE;
            }

            private void registerDir(Path dir) throws IOException {
                WatchKey key = dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
                keys.put(key, dir);
            }
        });
    }

    public void startWatching() {
        while (true) {
            WatchKey key;
            try {
                key = watchService.take();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            Path dir = keys.get(key);
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                Path filePath = (Path) event.context();
                Path fullPath = dir.resolve(filePath);

                AoitoriMapPlugin.plugin.getLogger().info("Event type: " + kind + ". File affected: " + fullPath);
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    if (Files.isDirectory(fullPath)) {
                        try {
                            registerAll(fullPath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        AoitoriMapPlugin.plugin.getLogger().info("File created: " + fullPath);
                    }
                } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    AoitoriMapPlugin.plugin.getLogger().info("File modified: " + fullPath);
                } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    if (Files.isDirectory(fullPath)) {
                        unregisterDir(fullPath);
                    } else {
                        AoitoriMapPlugin.plugin.getLogger().info("File deleted: " + fullPath);
                    }
                }

                MapConfigHandler.load();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    MapPlayerProfile mapPlayerProfile = MapDatabaseClient.getMapPlayerProfile(player.getName());
                    OverlayManager overlayManager = mapPlayerProfile.getOverlayManager();
                    for (OverlayMapper value : MapConfigHandler.overlay.values()) {
                        overlayManager.addOverlayImpl(value);
                    }
                     OperateMapDataDTO.builder().type(OperateMapDataDTO.MapOperateType.REFRESH).build().send(player);
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);
            }
        }
    }

    private void unregisterDir(Path dir) {
        WatchKey key = keys.entrySet().stream()
                .filter(entry -> entry.getValue().equals(dir))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);

        if (key != null) {
            keys.remove(key);
        }
    }
}
