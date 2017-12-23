package org.book.rpc.dubbo.swagger.controller;

import io.swagger.annotations.*;
import org.book.rpc.dubbo.swagger.entity.Customer;
import org.book.rpc.dubbo.swagger.validate.UserValidate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(value = "描述", description = "控制器名称")
@RestController
public class SwaggerController {

    @ApiOperation(value = "接口名称", notes = "使用URL传参方式描述接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "age", value = "年龄", paramType = "path", defaultValue = "2", dataType = "int"),
            @ApiImplicitParam(name = "nickname", value = "昵称", paramType = "query", defaultValue = "张三", dataType = "string")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功", response = Customer.class),
            @ApiResponse(code = 401, message = "权限不足"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "资源未找到"),
            @ApiResponse(code = 500, message = "程序内部发生错误")
    })
    @RequestMapping(value = "api/{age}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer api(@PathVariable("age") int age, @RequestParam("nickname") String nickname) {
        Customer customer = new Customer();
        customer.setAge(age);
        customer.setNickname(nickname);
        return customer;
    }

    @ApiOperation(value = "接口名称", notes = "使用实体传参方式描述接口", response = Customer.class)
    @RequestMapping(value = "api", method = RequestMethod.POST)
    public Customer api(@RequestBody UserValidate userValidate) {
        Customer customer = new Customer();
        customer.setAge(userValidate.getAge());
        customer.setNickname(userValidate.getNickname());
        return customer;
    }

}
