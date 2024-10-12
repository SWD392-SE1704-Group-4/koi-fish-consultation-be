package com.fengshui.corebusiness.resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/app-user")
@ComponentScan(basePackages = "com.fengshui.common.services")
public class AppUserResource {

}
