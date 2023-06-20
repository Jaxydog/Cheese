package dev.jaxydog.utility.register;

public @interface SkipRegistration {
	public boolean client() default true;

	public boolean main() default true;

	public boolean server() default true;
}
