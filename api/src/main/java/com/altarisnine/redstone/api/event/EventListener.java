package com.altarisnine.redstone.api.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.altarisnine.redstone.api.event.EventPriority.MEDIUM;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventListener {

    EventPriority priority() default MEDIUM;

}
