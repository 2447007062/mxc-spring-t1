package top.javaguo.core.intercept.webMvcConfigurerAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.javaguo.core.intercept.interceptor.APIInterceptor;
import top.javaguo.core.intercept.interceptor.SystemInterceptor;

/**
 * @describe 初始化配置类
 * @author javaGuo
 * @date 2018/11/15
 */
@Configuration
public class BaseWebAppConfigurer implements WebMvcConfigurer {

    @Autowired
    public SystemInterceptor systemInterceptor;

    @Autowired
    public APIInterceptor apiInterceptor;

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(apiInterceptor)
                .addPathPatterns("/api/**")//拦截
                .excludePathPatterns("/api/loginApi/**")//放行
                .excludePathPatterns("/api/shareApi/**")
                .excludePathPatterns("/api/miniAppApi/**")
        ;

        registry.addInterceptor(systemInterceptor)
                .addPathPatterns("/system/**");
    }

}
