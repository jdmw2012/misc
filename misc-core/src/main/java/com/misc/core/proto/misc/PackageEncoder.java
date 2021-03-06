package com.misc.core.proto.misc;


import com.misc.core.env.MiscProperties;
import com.misc.core.model.MiscPack;
import com.misc.core.proto.misc.serial.MiscSerializableHandler;
import com.misc.core.proto.misc.common.MiscProtoConstance;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.Map;
import java.util.Objects;

import static com.misc.core.commons.Constants.DEFAULT_SERIALIZABLE_TYPE;
import static com.misc.core.commons.Constants.DEFAULT_SERVER_VERSION;
import static com.misc.core.commons.PropertiesConstant.*;

/**
 * 编码器  将 {@link MiscPack} 编码成为  ByteBuf 然后放入字节缓冲区
 * <p>
 * 主要就是写 一个 版本号, 数据包长度, 数据包 , 来做校验
 * <p>
 * ChannelOutboundHandlerAdapter  -- > MessageToByteEncoder -- > PackageEncoder
 *
 * @date:2019/11/10 13:20
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
final class PackageEncoder extends MessageToByteEncoder<MiscPack> {

    /**
     * 自定义协议头
     */
    private final short version;
    /**
     * 写出类型
     */
    private final byte type;

    /**
     * 编解码处理器
     */
    private final Map<Byte, MiscSerializableHandler> serializeHandlerMap;

    PackageEncoder(MiscProperties properties, Map<Byte, MiscSerializableHandler> serializeHandlerMap) {
        super();
        this.version = properties.getShort(CLIENT_SERVER_VERSION, DEFAULT_SERVER_VERSION);
        this.type = properties.getByte(CLIENT_SERIALIZABLE_TYPE, DEFAULT_SERIALIZABLE_TYPE.getCode());
        this.serializeHandlerMap = Objects.requireNonNull(serializeHandlerMap);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, MiscPack msg, ByteBuf out)
            throws Exception {
        // 魔数
        out.writeByte(MiscProtoConstance.MAGIC_NUMBER);
        // 服务版本号
        out.writeShort(version);
        // 序列号类型
        out.writeByte(type);

        MiscSerializableHandler miscSerializableHandler = serializeHandlerMap.getOrDefault(type, MiscSerializableHandler.DEFAULT_HANDLER);
        miscSerializableHandler.encode(msg, out);
    }
}
