package dev.jaxydog.utility.register;

import dev.jaxydog.Cheese;
import dev.jaxydog.content.block.CustomBlocks;
import dev.jaxydog.content.item.CustomItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public final class AutoRegisterImpl {

    public static final Class<?>[] DEFINITIONS_CLASSES = new Class<?>[] { CustomBlocks.class, CustomItems.class };

    private AutoRegisterImpl() {}

    private static void run(RegisterEnv env, Class<?> definitions) {
        if (!definitions.isAnnotationPresent(AutoRegister.class)) return;
        if (!env.shouldRegister(definitions.getAnnotation(AutoRegister.class))) return;

        for (var field : definitions.getFields()) {
            if (field.isAnnotationPresent(SkipRegistration.class)) {
                if (env.shouldSkip(field.getAnnotation(SkipRegistration.class))) continue;
            }

            var modifiers = field.getModifiers();

            if (!(Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers))) continue;

            try {
                var value = field.get(null);

                if (!env.getInterface().isInstance(value)) continue;

                env.getMethod().invoke(value);
            } catch (Exception e) {
                Cheese.LOGGER.error(e.getLocalizedMessage());
            }
        }
    }

    private static void run(RegisterEnv env) {
        for (var definitions : AutoRegisterImpl.DEFINITIONS_CLASSES) {
            AutoRegisterImpl.run(env, definitions);
        }
    }

    @Environment(EnvType.CLIENT)
    public static void runClient() {
        AutoRegisterImpl.run(RegisterEnv.CLIENT);
    }

    public static void runMain() {
        AutoRegisterImpl.run(RegisterEnv.MAIN);
    }

    @SuppressWarnings("unused")
    @Environment(EnvType.SERVER)
    public static void runServer() {
        AutoRegisterImpl.run(RegisterEnv.SERVER);
    }

    private enum RegisterEnv {
        CLIENT(Registerable.Client.class, "registerClient"),
        MAIN(Registerable.Main.class, "registerMain"),
        SERVER(Registerable.Server.class, "registerServer");

        private final Class<? extends Registerable> INTERFACE;
        private final String METHOD_NAME;

        RegisterEnv(Class<? extends Registerable> iface, String methodName) {
            this.INTERFACE = iface;
            this.METHOD_NAME = methodName;
        }

        public Class<? extends Registerable> getInterface() {
            return this.INTERFACE;
        }

        public String getMethodName() {
            return this.METHOD_NAME;
        }

        public Method getMethod() throws NoSuchMethodException {
            return this.getInterface().getMethod(this.getMethodName());
        }

        public boolean shouldRegister(AutoRegister annotation) {
            return switch (this) {
                case CLIENT -> annotation.client();
                case MAIN -> annotation.main();
                case SERVER -> annotation.server();
            };

        }

        public boolean shouldSkip(SkipRegistration annotation) {
            return switch (this) {
                case CLIENT -> annotation.client();
                case MAIN -> annotation.main();
                case SERVER -> annotation.server();
            };

        }
    }

}
