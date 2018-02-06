package com.waffle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ImportResource("classpath:beanRefContext.xml")
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableFeignClients
public class Application {

	public static void main(String[] args) {
	    // 设置环境变量的作用，可以自行debug进ConsulServerList.java中查看，代码逻辑简单，很容易懂
//        setEnv("DEPLOY_ENV", "test");// TODO: 手动设置环境变量，强烈建议将该值设置入系统环境变量，设置后本行删除
//        setEnv("PROJECT_ID","1000");//  TODO: 手动设置环境变量，强烈建议将该值设置入系统环境变量，设置后本行删除
		SpringApplication.run(Application.class, args);
//		log.info("done");
	}
        /** 手动设置环境变量方法。要想在本地调用测试环境的项目，需要将DEPLOY_ENV设置为TEST，将PROJECT_ID设置为任意非空值。
         *  强烈建议不要使用该方法!!!!
         *  强烈建议不要使用该方法!!!!
         *  强烈建议不要使用该方法!!!!
         *  将两值设置进本机的环境变量，然后将调用处删除，最为稳妥。
         * */
    protected static void setEnv(String key,String value) {
		Map<String,String> addEnv = new HashMap(1);
		addEnv.put(key,value);
		try {
		    Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
		    Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
		    theCaseInsensitiveEnvironmentField.setAccessible(true);
		    Map<String, String> cienv = (Map<String, String>) theCaseInsensitiveEnvironmentField.get(null);
		    cienv.putAll(addEnv);
		} catch (Exception e1) {
		}
	}
}
