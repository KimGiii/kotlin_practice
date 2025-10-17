package coding_test.boj.previous;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BOJ_2751 {
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // 한 줄 최대 길이
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    } else {
                        continue;
                    }
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        // 정수 파싱 - 직접 바이트에서 숫자로 변환
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') { // 공백, 탭, 개행 건너뛰기
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) c = read();
            
            do {
                ret = ret * 10 + c - '0'; // ASCII to int 변환
            } while ((c = read()) >= '0' && c <= '9');

            return neg ? -ret : ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            
            return neg ? -ret : ret;
        }

        // 버퍼 관리 메서드들
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null) return;
            din.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int N = sc.nextInt();
        
        int size = 2000001;
        boolean[] exists = new boolean[size];
        
        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            exists[num + 1000000] = true;
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < size; i++) {
            if (exists[i]) {
                sb.append(i - 1000000).append('\n');
            }
        }

        System.out.print(sb.toString());
        sc.close();
    }
}
