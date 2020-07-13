package nono.configuration.loaders;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.blade.Blade;
import com.blade.ioc.DynamicContext;
import com.blade.ioc.annotation.Bean;
import com.blade.ioc.bean.ClassInfo;
import com.blade.kit.ReflectKit;
import com.blade.loader.BladeLoader;

import lombok.extern.slf4j.Slf4j;
import nono.configuration.Factory;
import nono.uses.UseCase;
import nono.uses.channel.CoreUseCase;

@Slf4j
public class UseCaseLoader implements BladeLoader {

	/**
	 * Keeps all use case registered
	 */
	private List<Class<CoreUseCase>> registeredUsesCases = new ArrayList<>();

	@Override
	public void preLoad(Blade blade) {

		blade.scanPackages("nono").scanPackages().stream().flatMap(DynamicContext::recursionFindClasses)
				.map(ClassInfo::getClazz).filter(ReflectKit::isNormalClass)
				.forEach(c -> this.parseAndCreateUseCase(c, blade));

	}

	@Override
	public void load(Blade blade) {
		// inject all properties of use each use case, that is not annotated to make
		// independent framework
		registeredUsesCases.stream().forEach(c -> Arrays.asList(blade.getBean(c).getClass().getMethods()).stream()
				.filter(method -> method.getName().startsWith("set")).forEach(method -> {
					try {
						method.invoke(blade.getBean(c), blade.ioc().getBean(method.getName().substring(3)));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						log.error("Error on invoking use case properties", e);
					}
				}));

	}

	@SuppressWarnings("unchecked")
	private void parseAndCreateUseCase(Class<?> clazz, Blade blade) {
		if (clazz.getAnnotationsByType(UseCase.class).length > 0) {
			blade.register(clazz);
			registeredUsesCases.add((Class<CoreUseCase>) clazz);
		}

		if (null != clazz.getAnnotation(Factory.class) && clazz.getMethods().length > 0) {
			Object config = ReflectKit.newInstance(clazz);
			Arrays.stream(clazz.getMethods()).filter(m -> m.getAnnotation(Bean.class) != null).forEach(n -> {
				try {
					blade.ioc().addBean(n.getAnnotation(Bean.class).value(), n.invoke(config));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	}
}
