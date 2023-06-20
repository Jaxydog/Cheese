package dev.jaxydog.utility.register;

import dev.jaxydog.Cheese;
import net.minecraft.util.Identifier;

public interface Identifiable {
	public String getRawId();

	public default Identifier getId() {
		return Cheese.newId(this.getRawId());
	}
}
