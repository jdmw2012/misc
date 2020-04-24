package com.chat.core.netty_file;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import static org.junit.Assert.*;

public class PackageDecoderTest {

    @Test
    public void handlerRemoved0() throws IOException {
        FileInputStream reader = new FileInputStream("C:\\Users\\12986\\Desktop\\log4j.properties");
        FileOutputStream writer = new FileOutputStream("C:\\Users\\12986\\Desktop\\log4j-1.properties");

        FileChannel channel = writer.getChannel();


        channel.transferFrom(reader.getChannel(), 0, 100);


    }
}