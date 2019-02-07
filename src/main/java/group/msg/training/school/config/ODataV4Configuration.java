package group.msg.training.school.config;

import com.sap.cloud.sdk.service.prov.v4.rt.core.web.ODataApplicationInitializer;
import com.sap.cloud.sdk.service.prov.v4.rt.core.web.ODataServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextListener;
import java.util.Collections;

@Configuration
public class ODataV4Configuration {

	@Bean
	public ServletContextListener odataApplicationInitializerV4() {
		return wrap(new ODataApplicationInitializer());
	}

	@Bean
	public ServletRegistrationBean<ODataServlet> odataServletV4() {
		ServletRegistrationBean<ODataServlet> bean = new ServletRegistrationBean<>(new ODataServlet());
		bean.setUrlMappings(Collections.singletonList("/api/odata/v4/*"));
		bean.setName("ODataServletV4");
		bean.setLoadOnStartup(1);
		return bean;
	}


	private ServletContextListener wrap(ServletContextListener listener) {
		return new SingleParameterSettingListener(listener, "group.msg.training.school.controllers.odata.v4");
	}
}
