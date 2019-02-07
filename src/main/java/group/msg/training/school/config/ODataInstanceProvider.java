package group.msg.training.school.config;

import com.sap.cloud.sdk.service.prov.api.internal.InstanceProvider;
import org.springframework.context.ApplicationContext;

public class ODataInstanceProvider implements InstanceProvider {

	private static ApplicationContext context;

	public static <T> T getInstanceTyped(Class<T> clazz) {
		return context.getBean(clazz);
	}

	static void setContext(ApplicationContext context) {
		ODataInstanceProvider.context = context;
	}

	@Override
	public Object getInstance(Class clazz) {
		return context.getBean(clazz);
	}
}
