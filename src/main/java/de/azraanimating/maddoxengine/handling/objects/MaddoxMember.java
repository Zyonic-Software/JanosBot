/*
 * Zyonic Software - 2020 - Tobias Rempe
 * This File, its contents and by extention the corresponding project may be used freely in Compliance with the attached License.
 *
 * info@zyonicsoftware.com
 */

package de.azraanimating.maddoxengine.handling.objects;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;

import javax.annotation.Nonnull;
import java.awt.*;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

public class MaddoxMember {

    private final Member member;

    public MaddoxMember(final Member member) {
        this.member = member;
    }

    public Member getMember() {
        return this.member;
    }

    public User getUser() {
        return this.member.getUser();
    }

    public String getNickame() {
        return this.member.getNickname();
    }

    public String getUsername() {
        return this.member.getUser().getName();
    }

    public AuditableRestAction<Void> ban(final int deletionDays) {
        return this.member.ban(deletionDays);
    }

    public AuditableRestAction<Void> ban(final int deletionDays, @Nonnull final String reason) {
        return this.member.ban(deletionDays, reason);
    }

    public boolean canInteract(final Role role) {
        return this.member.canInteract(role);
    }

    public boolean canInteract(final Emote emote) {
        return this.member.canInteract(emote);
    }

    public boolean canInteract(final Member member) {
        return this.member.canInteract(member);
    }

    public boolean canInteract(final MaddoxMember maddoxMember) {
        return this.member.canInteract(maddoxMember.getMember());
    }

    public EnumSet<ClientType> getActiveClients() {
        return this.member.getActiveClients();
    }

    public List<Activity> getActivities() {
        return this.member.getActivities();
    }

    public Color getColor() {
        return this.member.getColor();
    }

    public int getColorRAW() {
        return this.member.getColorRaw();
    }

    public TextChannel getDefaultChannel() {
        return this.member.getDefaultChannel();
    }

    public OnlineStatus getOnlineStatus() {
        return this.member.getOnlineStatus();
    }

    public OnlineStatus getOnlineStatus(@Nonnull final ClientType clientType) {
        return this.member.getOnlineStatus(clientType);
    }

    public Guild getCurrentGuild() {
        return this.member.getGuild();
    }

    public JDA getCurrentJDA() {
        return this.member.getJDA();
    }

    public List<Role> getRoles() {
        return this.member.getRoles();
    }

    public OffsetDateTime getTimeJoined() {
        return this.member.getTimeJoined();
    }

    public OffsetDateTime getTimeBoosted() {
        return this.member.getTimeBoosted();
    }

    public boolean hasTimeJoinedValue() {
        return this.member.hasTimeJoined();
    }

    public boolean isGuildOwner() {
        return this.member.isOwner();
    }

    public AuditableRestAction<Void> kick() {
        return this.member.kick();
    }

    public AuditableRestAction<Void> kick(@Nonnull final String reason) {
        return this.member.kick(reason);
    }

    public AuditableRestAction<Void> modifyNickname(@Nonnull final String newNickname) {
        return this.member.modifyNickname(newNickname);
    }

    public String getAsMention() {
        return this.member.getAsMention();
    }

    public EnumSet<Permission> getPermissions() {
        return this.member.getPermissions();
    }

    public EnumSet<Permission> getPermissions(final GuildChannel guildChannel) {
        return this.member.getPermissions(guildChannel);
    }

    public EnumSet<Permission> getPermissionsExplicit() {
        return this.member.getPermissionsExplicit();
    }

    public EnumSet<Permission> getPermissionExplicit(final GuildChannel guildChannel) {
        return this.member.getPermissionsExplicit(guildChannel);
    }

    public boolean hasPermission(@Nonnull final Permission... permissions) {
        return this.member.hasPermission(permissions);
    }

    public boolean hasPermission(@Nonnull final Collection<Permission> permissions) {
        return this.member.hasPermission(permissions);
    }

    public boolean hasPermission(@Nonnull final GuildChannel guildChannel, @Nonnull final Permission... permissions) {
        return this.member.hasPermission(guildChannel, permissions);
    }

    public String getID() {
        return this.member.getId();
    }

    //CustomMethods

    public AuditableRestAction<Void> addRole(final Role role) {
        return this.member.getGuild().addRoleToMember(this.member, role);
    }

    public AuditableRestAction<Void> removeRole(final Role role) {
        return this.member.getGuild().removeRoleFromMember(this.member, role);
    }

    public PrivateChannel openPrivateChannel() {
        return this.member.getUser().openPrivateChannel().complete();
    }

}
