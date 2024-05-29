package com.ran.java.effective.item_9;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * TryFinallyCase
 *
 * @author rwei
 * @since 2024/5/7 16:07
 */
public class TryFinallyCase {
    public void copy(String src, String dst) throws IOException {
        InputStream in = Files.newInputStream(Paths.get(src));
        try {
            OutputStream out = Files.newOutputStream(Paths.get(dst));
            try {
                byte[] buf = new byte[100];
                int n = 0;
                while ((n = in.read(buf)) >= 0) {
                    out.write(buf, 0, n);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }
}
