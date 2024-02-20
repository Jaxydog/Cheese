package dev.jaxydog.utility.register;

public interface Registerable extends Identifiable {

    interface Client extends Registerable {

        default void registerClient() {}

    }

    interface Main extends Registerable {

        default void registerMain() {}

    }

    interface Server extends Registerable {

        @SuppressWarnings("unused")
        default void registerServer() {}

    }

}
