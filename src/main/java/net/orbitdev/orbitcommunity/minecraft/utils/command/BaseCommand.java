package net.orbitdev.orbitcommunity.minecraft.utils.command;

import java.io.IOException;

public abstract class BaseCommand {

    public BaseCommand() {
        CommandManager.getInstance().registerCommands(this, null);
    }

    public abstract void onCommand(CommandArgs command) throws IOException;
}

