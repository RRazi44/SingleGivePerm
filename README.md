# 🛡️​ SingleGivePerm - Spigot plugin



**SingleGivePerm** is an open-source Spigot plugin designed for streamlined permission management. It allows you to quickly assign or revoke permissions for an individual player, bypassing the need for a trditional rank-based system. This provides a fast and efficient way to manage your server's staff.



---

## ✨ Features

- Fast Permission Management: Quickly assign or remove permissions for individual players with simple commands.

- Player-Specific Control: Add permissions directy to a player without assigning a rank or group.

- Persistent Data: All permission changes are automatically saved to ``config.yml``, ensuring they remain activer after server restarts.

---

## How to use

This guide will walk you through the commands and configurations of the SingleGivePerm plugin.



##### Installation

1. Download the latest version of the plugin from [here.](https://github.com/RRazi44/SingleGivePerm/releases/download/1.0/SingleGivePerm-1.0.jar)

2. Place the .jar file in your server's /plugins folder.

3. Restart or reload your server.



#### Commands

The plugin provides two main commands for managing permissions.



#### Syntax

- ``/giveperm <player> <permission>``
- ``/removeperm <player> <permission>``


#### Permission Nodes:

- ``singlegiveperm.commands.giveperm``
- ``singlegiveperm.commands.removeperm``

#### Examples:

- **To give a player the permission for /home:**
  `/giveperm RRazi44 essentials.home`
  This will grant the player "RRazi44" the ability to use the `/home` command.

- To remove the permission from a player:
  ``/removeperm RRazi44 essentials.home``
  This will remove the previously granted permission from the player "RRazi44".

**Important:** Always use the in-game commands to manage permissions. Manually editing the file while the server is running may lead to data loss or errors.
