package com.assetManage.tusdt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = {"com.assetManage.*.dao","com.assetManage.*.mapper"})
@SpringBootApplication
public class AssetManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetManageApplication.class, args);
	}

}
