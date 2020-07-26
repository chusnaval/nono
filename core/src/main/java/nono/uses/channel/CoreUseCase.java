package nono.uses.channel;

import java.util.List;

public interface CoreUseCase<T> {

	List<T> execute();

}
