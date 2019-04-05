package com.altarisnine.redstone.api.configuration.serialization;

import com.altarisnine.redstone.api.configuration.ConfigurationSection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ConfigSerialization {

    public static Method getDeserializationMethod(Class<? extends ConfigSerializable> clazz) throws SerializationException {
        try {
            // Get method from the class
            final Method method = clazz.getDeclaredMethod("deserialize", ConfigurationSection.class);

            // TODO use this to automatically get the right type (for things like boundaries)
//            this.getClass().getClassLoader().loadClass()

            // Make sure the method has the correct return type
            if (!clazz.isAssignableFrom(method.getReturnType()))  throw new SerializationException("Deserialization method must return an instance of itself");

            // Make sure method is static
            if (!Modifier.isStatic(method.getModifiers())) throw new SerializationException("Deserialization method must be static");

            return method;
        } catch (NoSuchMethodException e) {
            throw new SerializationException("Unable to find deserialization method for " + clazz.getName(), e);
        }
    }

    public static <T extends ConfigSerializable> T deserializeWithMethod(ConfigurationSection section, Class<T> clazz) {
        try {
            // Get deserialization method
            Method deserializeMethod = getDeserializationMethod(clazz);

            // Invoke method
            T object = (T) deserializeMethod.invoke(null, section);

            return object;
        } catch (SerializationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Unable to deserialize with method from " + clazz.getName(), e);
        }
    }

}
