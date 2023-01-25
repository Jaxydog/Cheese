package dev.jaxydog.cheese.util;

import net.minecraft.util.Identifier;

/** Interface that provides methods for registering mod content */
public interface Registerable {
	/** Returns the value's registry identifier */
	public Identifier getId();

	/** Registers the value */
	public void register();

	/** Interface that provides methods for registering mod content with a given value */
	public static interface With<T> {
		/** Returns the value's registry identifier */
		public Identifier getId();

		/** Registers the value */
		public void register(T value);
	}
}
