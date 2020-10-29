package com.calculusmaster.pokecord.util;

import com.mongodb.client.model.Filters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Global
{
    public static final List<String> STARTERS = Arrays.asList("bulbasaur"); //TODO: Add the remaining starters and update this list
    public static final List<String> POKEMON = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(Global.class);

    //TODO: Set rarities for each Pokemon

    public static void logInfo(Class<?> clazz, String method, String msg)
    {
        LOGGER.info(clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1) + "#" + method + ": " + msg);
    }

    public static boolean isStarter(String s)
    {
        return STARTERS.contains(s.toLowerCase());
    }

    public static Object getEnumFromString(Object[] enumValues, String s)
    {
        for(Object o : enumValues) if(s.toLowerCase().equals(o.toString().toLowerCase())) return o;
        return null;
    }

    public static String normalCase(String s)
    {
        StringBuilder sb = new StringBuilder();
        for(String str : s.split("\\s+")) sb.append(str.substring(0, 1).toUpperCase()).append(str.substring(1).toLowerCase()).append(" ");
        return sb.toString().trim();
    }

    public static void buildPokemonList()
    {
        Mongo.PokemonInfo.find(Filters.exists("name")).forEach(d -> POKEMON.add(d.getString("name")));
    }
}