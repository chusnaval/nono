package nono;

import com.blade.Blade;

public class MainTest {
	public static void main(String[] args) {
		Blade.of().get("/", ctx -> ctx.text("Hello Blade")).start();
	}
}
