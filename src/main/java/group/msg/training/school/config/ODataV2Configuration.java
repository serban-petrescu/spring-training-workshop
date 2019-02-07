package group.msg.training.school.config;

import com.sap.cloud.sdk.service.prov.v2.rt.core.web.EndPointsList;
import com.sap.cloud.sdk.service.prov.v2.web.ServiceInitializer;
import com.sap.gateway.core.odata4sap.ServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.olingo.odata2.core.servlet.ODataServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextListener;
import java.util.Collections;

@Slf4j
@Configuration
public class ODataV2Configuration {

	@Bean
	public ServletContextListener serviceInitializerV2() {
		return wrap(new ServiceInitializer());
	}

	@Bean
	public ServletRegistrationBean endpointsServletV2() {
		ServletRegistrationBean<EndPointsList> bean = new ServletRegistrationBean<>(new EndPointsList());
		bean.addUrlMappings("/api/odata/v2/");
		return bean;
	}

	@Bean
	public ServletRegistrationBean odataServletV2(ApplicationContext context) {
		ODataInstanceProvider.setContext(context);
		ServletRegistrationBean<ODataServlet> bean = new ServletRegistrationBean<>(new ODataServlet());
		bean.setUrlMappings(Collections.singletonList("/api/odata/v2/*"));
		bean.addInitParameter("org.apache.olingo.odata2.service.factory", ServiceFactory.class.getName());
		bean.addInitParameter("org.apache.olingo.odata2.path.split", "1");
		bean.setLoadOnStartup(1);
		return bean;
	}

	private ServletContextListener wrap(ServletContextListener listener) {
		return new SingleParameterSettingListener(listener, "group.msg.training.school.controllers.odata.v2");
	}
}
