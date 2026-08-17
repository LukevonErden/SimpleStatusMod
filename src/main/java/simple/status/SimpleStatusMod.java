package simple.status;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import net.minecraft.network.chat.Component;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.server.MinecraftServer;

public class SimpleStatusMod implements ModInitializer {
	public static final String MOD_ID = "simplestatusmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static StatusManager statusManager;

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		File file = new File("config/simplestatusmod/statuses.json");
		statusManager = new StatusManager(file);
		statusManager.load();
		SSMCommand.register();
		StatusJoinListener.register();
		ServerMessageEvents.ALLOW_CHAT_MESSAGE.register(
			(message, sender, boundChatType) -> {
				String status = statusManager.getStatus(sender.getUUID());
				Component chatMessage;
				if (status != null && !status.isEmpty()) {
					chatMessage = Component.literal("[")
							.append(Component.literal(status))
							.append(Component.literal("] "))
							.append(Component.literal(sender.getGameProfile().name()))
							.append(Component.literal(": "))
							.append(message.decoratedContent());
				} else {
					chatMessage = sender.getDisplayName()
							.copy()
							.append(Component.literal(": "))
							.append(message.decoratedContent());
				}
				MinecraftServer server = sender.level().getServer();

				server.getPlayerList().broadcastSystemMessage(
						chatMessage,
						false
				);
				return false;
			}
		);
	}
	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
