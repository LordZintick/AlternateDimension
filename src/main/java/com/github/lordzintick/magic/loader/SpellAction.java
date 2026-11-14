package com.github.lordzintick.magic.loader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@FunctionalInterface
public interface SpellAction {
    void execute(Spell.SpellParameterProvider paramProvider);
}
