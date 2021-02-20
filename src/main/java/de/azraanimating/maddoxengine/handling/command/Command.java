/*
 * Zyonic Software - 2020 - Tobias Rempe
 * This File, its contents and by extention the corresponding project may be used freely in Compliance with the attached License.
 *
 * info@zyonicsoftware.com
 */

package de.azraanimating.maddoxengine.handling.command;

import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;

public abstract class Command {

    private String name;
    private String syntax;
    private String description;

    protected void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public String getSyntax() {
        return syntax;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    protected abstract void execute(CommandEvent event, MaddoxMember sender, MaddoxGuild server);
}
