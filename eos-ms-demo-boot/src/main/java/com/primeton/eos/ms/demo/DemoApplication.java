/**
 * 
 */
package com.primeton.eos.ms.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

import com.primeton.eos.dap.sdk.api.bizflow.EnableSDKBizflows;

/**
 * @author wangwb
 *
 */
@SpringBootApplication
@EnableAsync
@EnableDiscoveryClient
@EnableSDKBizflows
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
