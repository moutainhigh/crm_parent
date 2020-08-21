package com.zkk.ws;

import javax.jws.WebService;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/20 16:48
 */
@WebService
public interface WSInterface {
    String sayHello(String name);
}
