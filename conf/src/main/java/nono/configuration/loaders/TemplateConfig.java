package nono.configuration.loaders;

import com.blade.Blade;
import com.blade.ioc.annotation.Bean;
import com.blade.loader.BladeLoader;
import com.blade.mvc.view.template.JetbrickTemplateEngine;

@Bean
public class TemplateConfig implements BladeLoader {

	@Override
	public void load(Blade blade) {
		blade.templateEngine(new JetbrickTemplateEngine());
	}
}