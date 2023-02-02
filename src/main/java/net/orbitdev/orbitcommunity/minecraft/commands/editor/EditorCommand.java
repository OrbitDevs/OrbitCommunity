package net.orbitdev.orbitcommunity.minecraft.commands.editor;

import net.orbitdev.orbitcommunity.minecraft.menus.editor.EditorMenu;
import net.orbitdev.orbitcommunity.minecraft.utils.command.BaseCommand;
import net.orbitdev.orbitcommunity.minecraft.utils.command.Command;
import net.orbitdev.orbitcommunity.minecraft.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class EditorCommand extends BaseCommand {

    @Command(name = "editor", permission = "orbit.command.editor", inGameOnly = true)
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        new EditorMenu().openMenu(player);
    }
}