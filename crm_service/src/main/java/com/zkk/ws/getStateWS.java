package com.zkk.ws;

import javax.jws.WebService;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/20 17:57
 */
@WebService
public interface getStateWS {
    Integer getStateByPhone(String phone);

}
