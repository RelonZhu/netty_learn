package com.relon.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class OldServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8899);
        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

//            try {
//                byte[] bytes = new byte[4096];
//
//                while (true) {
//                    int read = dataInputStream.read(bytes, 0, bytes.length);
//
//                }
//            }
        }

    }
}
