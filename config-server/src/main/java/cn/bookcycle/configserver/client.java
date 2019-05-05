package cn.bookcycle.configserver;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * @author yesyoungbaby
 * @Title: client
 * @ProjectName config-server
 * @Description: TODO
 * @date 2018/11/2614:29
 */
public class client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 10086);
        InputStream inputStream = socket.getInputStream();

        //获取数据长度
        int len = ByteBuffer.wrap(inputStream.readNBytes(4)).getIn   t();
        byte[] data = inputStream.readNBytes(len);
        for (byte x : data) {
            //输出所有随机数
            System.out.print((int) x + " ");
        }
        System.out.println();
        while (true) {
            //等待服务器的新数据到来
            int x = inputStream.read();
            System.out.println("Receive new data: " + x);
        }
    }
}
