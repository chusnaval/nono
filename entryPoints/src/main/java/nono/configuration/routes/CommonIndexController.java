package nono.configuration.routes;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;

@Path
public class CommonIndexController {

	@GetRoute("/")
	public String index() {
		return "index.html";
	}
}
