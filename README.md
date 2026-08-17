# SimpleStatusMod

A lightweight, fully server-side Minecraft mod that adds customizable player statuses to the **chat, tab list, and nametags**.

Clients do **not** need to install SimpleStatusMod.

## ✨ Features

* `/status <text>` - Set your personal status
* `/status` - Remove your current status
* Statuses are displayed in:

  * Chat
  * Tab list
  * Player nametags
* Supports Minecraft color and formatting codes using `&`

  * Examples: `&6Gold`, `&5Purple`, `&lBold`, `&kGibberish`
* Every player can have their own status
* Statuses persist across server restarts and player reconnects
* Fully server-side
* Lightweight and requires no client-side installation

## 🎨 Color & Formatting Codes

Use `&` followed by a Minecraft formatting code:

| Code        | Formatting        |
| ----------- | ----------------- |
| `&0` – `&9` | Colors            |
| `&a` – `&f` | Colors            |
| `&l`        | **Bold**          |
| `&o`        | *Italic*          |
| `&n`        | Underline         |
| `&m`        | ~~Strikethrough~~ |
| `&k`        | Obfuscated        |
| `&r`        | Reset formatting  |

Example:

```text
/status &6&lOwner
```

will display:

```text
[Owner] Player
```

with the configured Minecraft formatting.

## 💾 Storage

Player statuses are stored persistently in:

```text
config/simplestatusmod/statuses.json
```

Statuses are loaded when the server starts and saved whenever a status is changed.

The status data is managed in memory using a `HashMap` and persisted to JSON.

## 📦 Installation

1. Install **Fabric Loader** for your Minecraft version.
2. Install **Fabric API**.
3. Download SimpleStatusMod.
4. Place the SimpleStatusMod `.jar` into the server's `mods` folder.
5. Start the server.

**No client installation is required.**

## ⚙️ Requirements

* Minecraft `26.2`
* Fabric Loader `0.19.3` or newer
* Fabric API
* Java 25 or newer

## 🧪 Current Version

**1.0.0-beta.1**

This is the first public beta release. The core functionality is implemented and tested, but bugs may still occur.

Bug reports and feedback are welcome.

## 📄 License

SimpleStatusMod is licensed under the **MIT License**.

See [`LICENSE`](LICENSE) for the full license text.
