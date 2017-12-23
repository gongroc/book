package org.book.rpc.cloud.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "控制器名称", description = "控制器介绍")
@RestController
public class SwaggerController {

    @ApiOperation(value = "接口名称", notes = "接口介绍")
    @ApiImplicitParam(name = "id", value = "参数介绍", paramType = "path", defaultValue = "2")
    @RequestMapping(value = "private/{id}",method = RequestMethod.GET)
    public String api(@PathVariable("id") int id) {
        return "success : " + id;
    }

}
