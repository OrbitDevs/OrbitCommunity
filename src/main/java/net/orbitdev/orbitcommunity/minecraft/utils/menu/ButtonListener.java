package net.orbitdev.orbitcommunity.minecraft.utils.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class ButtonListener implements Listener {


    @EventHandler(priority = EventPriority.MONITOR)
    public void onButtonPress(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        Menu openMenu = Menu.getOpenedMenus().get(player.getName());


        if (openMenu != null) {

            if (event.getSlot() != event.getRawSlot()) {
                if ((event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT)) {
                    event.setCancelled(false);
                }
                return;
            }

            if (openMenu.getButtons().containsKey(event.getSlot())) {
                Button button = openMenu.getButtons().get(event.getSlot());
                boolean shouldCancel = button.shouldCancel(player, event.getSlot(), event.getClick());
                boolean shouldShift = button.shouldShift(player, event.getSlot(), event.getClick());

                if (shouldCancel && shouldShift) {
                    event.setCancelled(true);
                }
                else {
                    event.setCancelled(shouldCancel);
                }

                button.clicked(player, event.getSlot(), event.getClick(), event.getHotbarButton());

                if (Menu.getOpenedMenus().containsKey(player.getName())) {
                    Menu newMenu = Menu.getOpenedMenus().get(player.getName());

                    if (newMenu == openMenu) {
                        if (openMenu.isUpdateAfterClick()) {
                            openMenu.setClosedByMenu(true);
                            newMenu.openMenu(player);
                        }
                    }
                }
                else if (button.shouldUpdate(player, event.getSlot(), event.getClick())) {
                    openMenu.setClosedByMenu(true);
                    openMenu.openMenu(player);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Menu openMenu = Menu.getOpenedMenus().get(player.getName());

        if (openMenu != null) {
            openMenu.onClose(player);

            Menu.getOpenedMenus().remove(player.getName());

        }
    }
}