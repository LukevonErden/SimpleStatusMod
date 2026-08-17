package simple.status;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import com.mojang.brigadier.arguments.StringArgumentType;

public class SSMCommand {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                Commands.literal("status")
                    .executes(context -> {
                        var player = context.getSource().getPlayerOrException();
                        var server = context.getSource().getServer();
                        var uuid = player.getUUID();
                        SimpleStatusMod.statusManager.setStatus(uuid, "");
                        SimpleStatusMod.statusManager.save();
                        StatusTeamManager.updatePlayer(server, player, "");
                        context.getSource().sendSuccess(
                                () -> Component.literal(
                                        "§f[§c§lSSM§f] §3Your status has been removed."
                                ),
                                false
                        );
                        return 1;
                    })
                    .then(
                        Commands.argument(
                                        "status",
                                        StringArgumentType.greedyString()
                            )
                            .executes(context -> {
                                String status = StringArgumentType
                                        .getString(context, "status")
                                        .replace("&", "§");
                                var player = context.getSource().getPlayerOrException();
                                var server = context.getSource().getServer();
                                var uuid = player.getUUID();
                                SimpleStatusMod.statusManager.setStatus(uuid, status);
                                SimpleStatusMod.statusManager.save();
                                StatusTeamManager.updatePlayer(server, player, status);
                                context.getSource().sendSuccess(
                                    () -> Component.literal(
                                        "§f[§c§lSSM§f] §3Your status has been set to: §f["
                                                + status
                                                + "§f]"
                                    ),
                                    false
                                );
                                return 1;
                            })
                    )
            );
        });
    }
}