package cn.bookcycle.configserver;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/**
 * @author yesyoungbaby
 * @Title: Server
 * @ProjectName config-server
 * @Description: TODO
 * @date 2018/11/2614:30
 */
public class Server {
    //Comment Encoding Charset "UTF-8"

    //用来保存自服务器启动以来生成的随机数
    private ArrayList<Integer> data;

    //生成随机数的线程
    class GenerateRandomNum implements Runnable {
        //private List<Integer> data;
        private Random random;

        GenerateRandomNum() {
            //初始化随机数数组
            data = new ArrayList<>();
            random = new Random();
        }

        @Override
        public void run() {
            while (true) {
                int x = random.nextInt(100);
                data.add(x);
                //System.out.println(x);
                try {
                    sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    currentThread().interrupt();
                }
            }
        }
    }

    class Worker implements Runnable {
        private Socket socket;

        Worker(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (OutputStream outputStream = socket.getOutputStream()) {
                //获取当前的数组大小
                int len = data.size();
                //分配字节缓存空间，大小为数组大小+4字节（用来存放数组大小）
                ByteBuffer byteBuffer = ByteBuffer.allocate(4 + len);
                //放入当前数组大小
                byteBuffer.putInt(len);
                for (Integer x : data) {
                    //对每个随机数写入字节缓存
                    byteBuffer.put(x.byteValue());
                }
                //将数据发送给客户端
                outputStream.write(byteBuffer.array());

                //死循环查询数组长度，一旦有新的数据生成就发送给客户端
                while (true) {
                    //不加这个sleep或者不print下面的语句的话，就检测不到data数组的大小变化
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        currentThread().interrupt();
                    }
                    //int temp = data.size();
                    //System.out.println("length of data: " + temp);

                    if (data.size() > len) {
                        //如果数组大小大于之前发送的长度，说明有新的数据生成
                        len = data.size();
                        //获取新的数据
                        byte x = data.get(len - 1).byteValue();
                        System.out.println("Sending new data: " + x + " to " + socket.getInetAddress() + socket.getPort());
                        //发送给客户端
                        outputStream.write(x);
                    }
                }
            } catch (IOException e1) {
                System.out.println("Disconnected to" + socket.getInetAddress());
                e1.printStackTrace();
                try {
                    socket.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.launch();
    }

    private void launch() {
        System.out.println("Server is started!");
        //启动生成随机数的线程
        (new Thread(new GenerateRandomNum())).start();
        //监听端口为10086
        int portNumber = 10086;
        //建立一个大小为10的线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (true) {
                //开始监听端口
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to " + clientSocket.getInetAddress().toString());
                //接收到连接请求后就建立新线程并将socket传入，然后将新线程交给线程池
                executor.execute(new Worker(clientSocket));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}
