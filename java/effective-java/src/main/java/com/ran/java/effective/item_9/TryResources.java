package com.ran.java.effective.item_9;

import java.io.*;

/**
 * TryResources
 *
 * @author rwei
 * @since 2024/5/8 13:50
 */
public class TryResources {
    public void copy(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[64];
            int n = 0;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        }
    }
}
