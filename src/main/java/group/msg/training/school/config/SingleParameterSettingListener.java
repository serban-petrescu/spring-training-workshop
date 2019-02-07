package group.msg.training.school.config;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@RequiredArgsConstructor
class SingleParameterSettingListener implements ServletContextListener {
	private static final String PARAMETER_NAME = "package";

	private final ServletContextListener delegate;
	private final String parameterValue;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		delegate.contextInitialized(new ServletContextEvent(new Context(sce.getServletContext())));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		this.delegate.contextDestroyed(sce);
	}

	private interface GetInitParameter {
		String getInitParameter(String name);
	}

	@RequiredArgsConstructor
	private class Context implements ServletContext {
		@Delegate(excludes = GetInitParameter.class)
		private final ServletContext delegate;

		@Override
		public String getInitParameter(String name) {
			if (PARAMETER_NAME.equals(name)) {
				return parameterValue;
			} else {
				return delegate.getInitParameter(name);
			}
		}
	}
}
