# TrapdoorCrash

Click here to [download](https://github.com/piRrevreS/TrapdoorCrashFix/releases/download/1.0/TrapdoorCrashFix.jar)

<img src="https://github.com/piRrevreS/TrapdoorCrashFix/blob/main/img/0debf7a98319fa9c57afc599ab7a5055c01f8513.png" />

Especially, this plugin helps from 2023 crash with trapdoor and rails. Look at screenshot. To cause crash you should place redstone dust above trapdoor. Does not work in single player.
This crash is present on 1.16, 1.17, 1.18 and partially on 1.19

Configuration meanings:
redstone-updates-limit - Updates amount after what we should cancel the redstone line. The trapdoor and rails exploit produce 60k updates after that server is starting lagging to death
report-location - Print to log locations where redstone activity was halted
filter - A list with redstone blocks that should be filtered. For instance, you can write "TRAPDOOR" and only trapdoors will be tracked by plugin
