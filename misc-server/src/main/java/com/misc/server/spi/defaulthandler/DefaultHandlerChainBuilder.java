package com.misc.server.spi.defaulthandler;

import com.misc.server.rpc.RpcRequestHandler;
import com.misc.server.spi.handler.HandlerChainBuilder;
import com.misc.server.spi.handler.RequestHandlerProcess;


/**
 * 默认的构建器 {@link HandlerChainBuilder}
 *
 * @date:2020/2/17 14:31
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class DefaultHandlerChainBuilder implements HandlerChainBuilder {

    /**
     * 链式处理器
     */
    @Override
    public RequestHandlerProcess build() {
        RequestHandlerProcess process = new RequestHandlerProcess();
        process.addLast(new RecordRequestHandler());
//        process.addLast(new LogRequestHandler());
        process.addLast(new MessageRequestHandler());
//        process.addLast(new FileRequestHandler());
        process.addLast(new RpcRequestHandler(RpcMapBuilder.map));
        process.addLast(new HeartRequestHandler());
        return process;
    }
}
