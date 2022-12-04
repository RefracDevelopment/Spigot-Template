package me.refracdevelopment.example.locale;

import dev.rosewood.rosegarden.locale.Locale;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnglishLocale implements Locale {

    @Override
    public String getLocaleName() {
        return "en_US";
    }

    @Override
    public String getTranslatorName() {
        return "Refrac";
    }

    @Override
    public Map<String, Object> getDefaultLocaleValues() {
        return new LinkedHashMap() {{
            this.put("#0", "Plugin Message Prefix");
            this.put("prefix", "<g:#8A2387:#E94057:#F27121>ExamplePlugin &8| &f");

            this.put("#1", "Generic Command Messages");
            this.put("no-permission", "&cYou don't have permission for that!");
            this.put("no-console", "&cOnly players may execute this command.");
            this.put("unknown-command", "Unknown command, use #00B4DB/%cmd%&f help for more info");
            this.put("example-message", "&eHello World!.");

            this.put("#2", "Base Command Message");
            this.put("base-command-color", "&e");
            this.put("base-command-help", "&eUse &b/%cmd% help &efor command information.");

            this.put("#3", "Help Command");
            this.put("command-help-title", "&fAvailable Commands:");
            this.put("command-help-description", "Displays the help menu.");
            this.put("command-help-list-description", "&8 - &d/%cmd% %subcmd% %args% &7- %desc%");
            this.put("command-help-list-description-no-args", "&8 - &d/%cmd% %subcmd% &7- %desc%");

            this.put("#4", "Reload Command");
            this.put("command-reload-description", "Reloads the plugin");
            this.put("command-reload-usage", "&cUsage: &e/%cmd% %subcmd%");
            this.put("command-reload-success", "&aConfiguration and locale files were reloaded.");

            this.put("#5", "Version Command");
            this.put("command-version-description", "Display the version info for ExamplePlugin");
        }};
    }
}