package net.orbitdev.orbitcommunity.minecraft.commands.bug;

import net.orbitdev.orbitcommunity.OrbitCommunity;
import net.orbitdev.orbitcommunity.minecraft.utils.CC;
import net.orbitdev.orbitcommunity.minecraft.utils.command.BaseCommand;
import net.orbitdev.orbitcommunity.minecraft.utils.command.Command;
import net.orbitdev.orbitcommunity.minecraft.utils.command.CommandArgs;
import net.orbitdev.orbitcommunity.shared.bug.BugManager;
import net.orbitdev.orbitcommunity.shared.category.Category;
import net.orbitdev.orbitcommunity.shared.suggestion.SuggestionManager;
import org.bukkit.command.CommandSender;

import java.io.IOException;
import java.util.Arrays;

public class BugCommand extends BaseCommand {

    private final BugManager bugManager;

    @Command(name = "bug", permission = "orbit.command.bug", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        CommandSender sender = cmdArgs.getSender();
        String[] args = cmdArgs.getArgs();
        if (args.length < 2) {
            sender.sendMessage(CC.translate("&cUsage: /" + cmdArgs.getLabel() + " <category> <bug>"));
            return;

        }
        String bugString = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        Category category = null;
        for (Category cat : Category.values()) {
            if (cat.name().equalsIgnoreCase(args[0])) {
                category = cat;
                break;
            }
        }
        if (category == null) {
            sender.sendMessage(CC.translate("&cInvalid category availables categories: &f" + Arrays.toString(Category.values())));
            return;
        }
        this.bugManager.addSuggestion(category, bugString, sender.getName());
        sender.sendMessage(CC.translate("&aBug report has been created."));
    }

    public BugCommand() {
        this.bugManager = OrbitCommunity.get().getBugManager();
    }
}