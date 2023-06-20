package dev.jaxydog.utility.register;

public interface Registerable extends Identifiable {
	public static interface Client extends Registerable {
		public default void registerClient() {}
	}

	public static interface Main extends Registerable {
		public default void registerMain() {}
	}

	public static interface Server extends Registerable {
		public default void registerServer() {}
	}
}
