package com.zkk.ws.impl;

import com.zkk.ws.WSInterface;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/20 16:48
 */
public class WSInterfaceImpl implements WSInterface {
    @Override
    public String sayHello(String name) {
        System.out.println(name);
        if ("张三".equals(name)) {
            return "z";
        }
        return null;
    }
    public static void main(String[] args) {
        //使用JaxWsServerFactoryBean发布
        JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
        //设置服务接口
        factoryBean.setServiceClass(WSInterface.class);
        //设置服务实现对象
        factoryBean.setServiceBean(new WSInterfaceImpl());
        //设置服务地址
        factoryBean.setAddress("http://127.0.0.1:1234/hello");
        //发布服务
        factoryBean.create();
        System.out.println("服务发布成功");
    }
}
