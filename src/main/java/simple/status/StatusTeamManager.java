package simple.status;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;

public class StatusTeamManager {
    public static void updatePlayer(
            MinecraftServer server,
            ServerPlayer player,
            String status
    ) {
        Scoreboard scoreboard = server.getScoreboard();
        String teamName = "ssm_" + player.getUUID().toString().replace("-", "");
        PlayerTeam team = scoreboard.getPlayerTeam(teamName);

        if (team == null) {
            team = scoreboard.addPlayerTeam(teamName);
        }
        scoreboard.removePlayerFromTeam(player.getScoreboardName());
        if (status == null || status.isEmpty()) {
            scoreboard.removePlayerTeam(team);
            return;
        }
        team.setPlayerPrefix(
                Component.literal("[" + status + "§r] ")
        );
        scoreboard.addPlayerToTeam(
                player.getScoreboardName(),
                team
        );
    }
}