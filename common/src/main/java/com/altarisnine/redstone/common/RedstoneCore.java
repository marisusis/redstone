package com.altarisnine.redstone.common;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.RedstoneAPI;
import com.altarisnine.redstone.api.command.GlobalHelpCommand;
import com.altarisnine.redstone.api.configuration.Configuration;
import com.altarisnine.redstone.api.configuration.ConfigurationHolder;
import com.altarisnine.redstone.api.configuration.ConfigurationManager;
import com.altarisnine.redstone.api.configuration.file.FileConfiguration;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.api.event.EventManager;
import com.altarisnine.redstone.api.plugin.Plugin;
import com.altarisnine.redstone.api.plugin.PluginManager;
import com.altarisnine.redstone.common.api.APIBase;
import com.altarisnine.redstone.common.bootstrap.RedstoneCoreBootstrap;
import com.altarisnine.redstone.common.command.CommandManager;
import com.altarisnine.redstone.common.commands.*;
import com.altarisnine.redstone.common.commands.guard.FreezeCommand;
import com.altarisnine.redstone.common.commands.guard.GuardBaseCommand;
import com.altarisnine.redstone.common.commands.guard.UnfreezeCommand;
import com.altarisnine.redstone.common.commands.guard.region.RegionCommand;
import com.altarisnine.redstone.common.configuration.CoreConfigurationManager;
import com.altarisnine.redstone.common.event.CoreEventManager;
import com.altarisnine.redstone.common.external.CoreMojangManager;
import com.altarisnine.redstone.common.external.ExternalManager;
import com.altarisnine.redstone.common.external.MojangManager;
import com.altarisnine.redstone.common.guard.CoreGuard;
import com.altarisnine.redstone.common.guard.SessionManager;
import com.altarisnine.redstone.common.guard.region.RegionManager;
import com.altarisnine.redstone.common.gui.GUIManager;
import com.altarisnine.redstone.common.logging.CoreLogger;
import com.altarisnine.redstone.common.messaging.PubSubManager;
import com.altarisnine.redstone.common.players.PlayerManager;
import com.altarisnine.redstone.common.plugin.CorePluginManager;
import com.altarisnine.redstone.common.scheduling.NetworkScheduler;
import com.altarisnine.redstone.common.server.CoreServer;
import com.altarisnine.redstone.common.storage.StorageManager;
import com.altarisnine.redstone.common.storage.database.DatabaseManager;
import com.altarisnine.redstone.common.storage.sync.SyncManager;
import com.altarisnine.redstone.common.user.User;
import com.altarisnine.redstone.common.util.ServerType;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import io.sentry.Sentry;
import lombok.Getter;

import java.io.InputStream;
import java.util.*;

// TODO add second redis client to listen to pubsub system?

// TODO rename to RedstoneCore
public abstract class RedstoneCore implements ConfigurationHolder {

    @Getter private static RedstoneCore instance;

    @Getter protected RedstoneAPI api;

    @Getter private RedstoneCoreBootstrap bootstrap;

    @Getter protected NetworkScheduler networkScheduler;
    @Getter protected DatabaseManager databaseManager;
    @Getter protected StorageManager storageManager;
    @Getter protected ConfigurationManager configurationManager;

    @Getter protected PlayerManager playerManager;

    @Getter protected SyncManager syncManager;
    @Getter protected PubSubManager pubSubManager;

    @Getter protected CommandManager commandManager;

    @Getter protected PluginManager pluginManager;

    @Getter protected CoreLogger logger;

    @Getter protected EventManager eventManager;

    @Getter protected GUIManager guiManager;

    @Getter protected CoreGuard guard;
    @Getter protected RegionManager regionManager;
    @Getter protected SessionManager sessionManager;

    @Getter protected Configuration config;

    @Getter protected CoreServer server;

    @Getter private boolean master;

    @Getter private MojangManager mojangManager;

    // Configuration files
    @Getter protected FileConfiguration playerCache;

    private Map<String, Plugin> loadedPlugins;
    private Map<Class<? extends ExternalManager>, ExternalManager> externalManagers;

    // PERFORMANCE is it a good idea to map online players to hashmap here, rather than querying bukkit/bungee each time?
    private BiMap<String, UUID> onlinePlayers;

    protected RedstoneCore(RedstoneCoreBootstrap instance) {
        this.bootstrap = instance;
        this.loadedPlugins = new HashMap<>();
        this.externalManagers = new HashMap<>();
        this.onlinePlayers = HashBiMap.create();
    }

    protected void preLoad() {
        // Initialize Sentry.io
        Sentry.init("https://c5541ef4899c4175b22e740aaf119022@sentry.io/1428560");

        if (!getDataFolder().exists()) getDataFolder().mkdir();

        // Load logger
        logger = new CoreLogger(this);

        // Load configuration manager
        configurationManager = new CoreConfigurationManager(this);

        // Load primary configuration
        this.config = configurationManager.getConfig("config.yml");

        // Load everything else
        storageManager = new StorageManager(this);
        databaseManager = new DatabaseManager(this);
        syncManager = new SyncManager(this);
        eventManager = new CoreEventManager(this);


//        pubSubManager = new PubSubManager(this); This should only be used on BungeeCord (proxy server)

        this.logger.info("preLoad done.");
    }

    protected void load() {
        // Initialize MongoDB
        databaseManager.init();

        // Initialize Redis
        syncManager.init();

        // Initialize core manager
        pluginManager = new CorePluginManager(this);

        this.logger.info("load done.");
    }

    @Override
    public boolean hasResource(String name) {
        return (getClass().getClassLoader().getResource(name) != null);
    }

    @Override
    public InputStream getResource(String name) {
        return (getClass().getClassLoader().getResourceAsStream(name));
    }

    protected void postLoad() {
        // FIXME Still being used?
        registerListeners();

        // TODO register certain commands only on non-bungee servers

        // Setup API
        api = getAPIImpl();
        Redstone.setApi(api);

        // PERFORMANCE load players on join
        storageManager.init(Player.class);

        loadDefaultCommands();

        this.logger.info("postLoad done.");
    }

    protected void registerExternalManager(ExternalManager manager) {
        if (!externalManagers.containsKey(manager.getClass())) {
            externalManagers.put(manager.getClass(), manager);
        }
    }

    public final <T extends ExternalManager> T getExternalManager(Class<T> clazz) {
        if (externalManagers.containsKey(clazz)) {
            return (T) externalManagers.get(clazz);
        } else {
            throw new IllegalArgumentException("That ExternalManager isn't registered!");
        }
    }

    public abstract User getPlayer(UUID uuid);
    public abstract User getPlayerByName(String name);
    public abstract List<User> getOnlineUsers();
    public abstract void broadcast(String message);
    public abstract void registerListeners();
    public abstract void log(String message);
    public abstract ServerType getType();
    public abstract APIBase getAPIImpl();

    private final void loadDefaultCommands() {
        switch (getType()) {

            case PROXY:
                break;
            case INSTANCE:
                // Load commands
                commandManager.registerCommand(new AnnounceCommand(), this);
                commandManager.registerCommand(new GlobalHelpCommand(), this);
                commandManager.registerCommand(new TellCommand(), this);
                commandManager.registerCommand(new PlayerInfoCommand(), this);
                commandManager.registerCommand(new GuardBaseCommand(this), this);
                commandManager.registerCommand(new TimeCommand(), this);
                commandManager.registerCommand(new RankCommand(), this);
                commandManager.registerCommand(new RegionCommand(), this);
                commandManager.registerCommand(new FreezeCommand(), this);
                commandManager.registerCommand(new UnfreezeCommand(), this);
                break;
            case GLASS:
                break;
        }
    }

    // Plugin methods
    public void enable() {
        instance = this;

        // Enabling phases
        preLoad();
        load();
        postLoad();

        // Load plugins
        loadPlugins();

        // Call onEnable for each registered core
        loadedPlugins.forEach((id, plugin) -> plugin.onEnable());

        // Bake commands
        commandManager.bake();

        this.logger.info("RedstoneCore enabled.");
    }

    private void loadPlugins() {
        this.logger.info("Loading plugins...");
        Plugin[] plugins = pluginManager.loadPlugins(getDataFolder());

        for (Plugin plugin : plugins) {
            loadedPlugins.put(plugin.getName(), plugin);
            this.logger.info("Finished loading core " + plugin.getName() + '.');
        }

        this.logger.info("Finished loading plugins.");
    }

    public void disable() {
        // TODO kick all players on disable to allow things to reinitialize
        //  OR automatically reinitialize everything on reload

        // TODO save data?

        // Disable plugins
        loadedPlugins.forEach((id, plugin) -> plugin.onDisable());

        // Shutdown network scheduler
        networkScheduler.shutdown();

        // Close database connections
        databaseManager.close();
        syncManager.close();


        this.logger.info("RedstoneCore disabled.");
    }

    public final void addOnlinePlayer(String name, UUID uuid) {
        onlinePlayers.put(name, uuid);
    }

    public final void removeOnlinePlayer(String name) {
        onlinePlayers.remove(name);
    }

    public final void removeOnlinePlayer(UUID uuid) {
        onlinePlayers.inverse().remove(uuid);
    }

    public final Set<String> getOnlinePlayerNames() {
        return onlinePlayers.keySet();
    }

    public final Set<UUID> getOnlinePlayerUniqueIds() {
        return onlinePlayers.values();
    }

    protected MojangManager loadMojangManager() {
        return new CoreMojangManager(this);
    }


}
