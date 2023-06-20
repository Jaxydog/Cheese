package dev.jaxydog.utility.register;

import dev.jaxydog.Cheese;
import dev.jaxydog.content.block.CustomBlocks;
import dev.jaxydog.content.item.CustomItems;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public final class AutoRegisterImpl {

	public static final Class<?>[] DEFINITIONS_CLASSES = new Class<?>[] { CustomBlocks.class, CustomItems.class };

	private AutoRegisterImpl() {}

	private static <R extends Registerable> void run(RegisterEnv env, Class<?> definitions) {
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

	private static <R extends Registerable> void run(RegisterEnv env) {
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

	@Environment(EnvType.SERVER)
	public static void runServer() {
		AutoRegisterImpl.run(RegisterEnv.SERVER);
	}

	private static enum RegisterEnv {
		CLIENT(Registerable.Client.class, "registerClient"),
		MAIN(Registerable.Main.class, "registerMain"),
		SERVER(Registerable.Server.class, "registerServer");

		private final Class<? extends Registerable> INTERFACE;
		private final String METHOD_NAME;

		private RegisterEnv(Class<? extends Registerable> iface, String methodName) {
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
			switch (this) {
				case CLIENT:
					return annotation.client();
				case MAIN:
					return annotation.main();
				case SERVER:
					return annotation.server();
			}

			return false;
		}

		public boolean shouldSkip(SkipRegistration annotation) {
			switch (this) {
				case CLIENT:
					return annotation.client();
				case MAIN:
					return annotation.main();
				case SERVER:
					return annotation.server();
			}

			return false;
		}
	}
}
