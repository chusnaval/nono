package nono.init;

import com.blade.Blade;

import nono.configuration.loaders.TemplateConfig;
import nono.configuration.loaders.UseCaseLoader;

public class Application {

	// Nono's start point
	public static void main(String[] args) {

		Blade.of().scanPackages("nono") // scan name app package to load all blade beans from other modules
				.addLoader(new UseCaseLoader()) // scan useCase annoted from core witch is framework independent
				.addLoader(new TemplateConfig()) // use of JetbrickTemplateEngine config
				.addStatics("statics").start(); // add statics folder and start
	}

}
