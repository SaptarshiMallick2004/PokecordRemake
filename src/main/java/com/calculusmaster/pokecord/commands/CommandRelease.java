package com.calculusmaster.pokecord.commands;

import com.calculusmaster.pokecord.game.Pokemon;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;

public class CommandRelease extends Command
{
    private static Map<String, Integer> releaseRequests = new HashMap<>();

    public CommandRelease(MessageReceivedEvent event, String[] msg)
    {
        super(event, msg, "release <index>");
    }

    @Override
    public Command runCommand()
    {
        if(this.msg.length != 2)
        {
            this.embed.setDescription(CommandInvalid.getShort());
            return this;
        }
        else if(!this.msg[1].chars().allMatch(Character::isDigit) && !this.msg[1].equals("confirm") && !this.msg[1].equals("deny"))
        {
            this.embed.setDescription(CommandInvalid.getShort());
            return this;
        }
        else if(this.msg[1].chars().allMatch(Character::isDigit) && this.playerData.getPokemonList().length() <= Integer.parseInt(this.msg[1]))
        {
            this.embed.setDescription(CommandInvalid.getShort());
            return this;
        }

        if(releaseRequests.containsKey(this.player.getId()) && this.msg[1].equals("confirm"))
        {
            Pokemon p = Pokemon.build(this.playerData.getPokemonList().getString(releaseRequests.get(this.player.getId()) - 1));

            this.playerData.removePokemon(releaseRequests.get(this.player.getId()));
            releaseRequests.remove(this.player.getId());

            this.embed.setDescription("Released Level " + p.getLevel() + " " + p.getName());
        }
        else if(releaseRequests.containsKey(this.player.getId()) && this.msg[1].equals("deny"))
        {
            Pokemon p = Pokemon.build(this.playerData.getPokemonList().getString(releaseRequests.get(this.player.getId()) - 1));

            releaseRequests.remove(this.player.getId());

            this.embed.setDescription("Cancelled releasing Level " + p.getLevel() + " " + p.getName());
        }
        else
        {
            releaseRequests.put(this.player.getId(), Integer.parseInt(this.msg[1]));
            Pokemon p = Pokemon.build(this.playerData.getPokemonList().getString(releaseRequests.get(this.player.getId()) - 1));

            this.embed.setDescription("Do you want to release your Level " + p.getLevel() + " " + p.getName() + "? (Type `p!release confirm` or `p!release deny` to continue)");
        }
        return this;
    }
}