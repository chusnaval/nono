package nono.routes;

import com.blade.mvc.annotation.DeleteRoute;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.annotation.PutRoute;

@Path
public class IndexControllerTest {

	@GetRoute("/")
	public String index() {
		return "index.html";
	}

	@PostRoute("/channel/save")
	public void saveChannel(@Param String channelName) {
		System.out.println("channelName:" + channelName);
	}

	@PutRoute("/channel/update")
	public void updateChannel(@Param String channelName) {
		System.out.println("channelName:" + channelName);
	}

	@DeleteRoute("/channel/delete/:uid")
	public void updateChannel(@PathParam Integer uid) {
		System.out.println("delete channel:" + uid);
	}

}