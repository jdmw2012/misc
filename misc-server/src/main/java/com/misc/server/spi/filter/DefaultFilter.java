package com.misc.server.spi.filter;

import com.misc.core.model.netty.Request;

/**
 * 默认实现 ,
 *
 * @date:2019/12/26 19:56
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class DefaultFilter implements Filter {

    @Override
    public boolean doFilter(Request request) {
        return false;
    }
}
