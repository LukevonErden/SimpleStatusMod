package simple.status;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class StatusJoinListener {
    public static void register() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            var joiningPlayer = handler.getPlayer();
            String status = SimpleStatusMod.statusManager.getStatus(
                    joiningPlayer.getUUID()
            );
            if (status != null && !status.isEmpty()) {
                StatusTeamManager.updatePlayer(
                        server,
                        joiningPlayer,
                        status
                );
            }
        });
    }
}