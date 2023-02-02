package net.orbitdev.orbitcommunity.minecraft.commands.suggestion;

import net.orbitdev.orbitcommunity.OrbitCommunity;
import net.orbitdev.orbitcommunity.minecraft.utils.CC;
import net.orbitdev.orbitcommunity.minecraft.utils.command.BaseCommand;
import net.orbitdev.orbitcommunity.minecraft.utils.command.Command;
import net.orbitdev.orbitcommunity.minecraft.utils.command.CommandArgs;
import net.orbitdev.orbitcommunity.shared.category.Category;
import net.orbitdev.orbitcommunity.shared.suggestion.SuggestionManager;
import org.bukkit.command.CommandSender;

import java.io.IOException;
import java.util.Arrays;

public class SuggestionCommand extends BaseCommand {

    private final SuggestionManager suggestionManager;

    @Command(name = "suggest", permission = "orbit.command.suggest", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        CommandSender sender = cmdArgs.getSender();
        String[] args = cmdArgs.getArgs();
        if (args.length < 2) {
            sender.sendMessage(CC.translate("&cUsage: /" + cmdArgs.getLabel() + " <category> <suggestion>"));
            return;

        }
        String suggestionString = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
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
        this.suggestionManager.addSuggestion(category, suggestionString, sender.getName());
        sender.sendMessage(CC.translate("&aSuggestion has been created."));
    }

    public SuggestionCommand() {
        this.suggestionManager = OrbitCommunity.get().getSuggestionManager();
    }
}
