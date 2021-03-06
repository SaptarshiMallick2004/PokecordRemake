package com.calculusmaster.pokecord.game.enums.elements;

public enum StatusCondition
{
    NORMAL(""),
    BURNED("BRN"),
    FROZEN("FRZ"),
    PARALYZED("PAR"),
    POISONED("PSN"),
    ASLEEP("SLP"),
    CONFUSED("CNF");

    private final String abbrev;
    StatusCondition(String abbrev)
    {
        this.abbrev = abbrev;
    }

    public String getAbbrev()
    {
        return this.abbrev;
    }
}
