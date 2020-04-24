package com.chat.core.register;

import com.chat.core.exception.RegisterException;
import com.chat.core.spi.SPIUtil;

/**
 * RegisterFactory 获取 注册中心
 *
 * @date:2020/1/21 15:39
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class RegisterFactory {
    /**
     * 这里没有考虑多服务
     */
    public static RegistryService createRegistry() throws RegisterException {
        return SPIUtil.loadFirstInstanceOrDefault(RegistryService.class, RedisRegistryService.class);
    }
}
