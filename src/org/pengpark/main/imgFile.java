package org.pengpark.main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * Created by pengpark on 2015. 11. 27..
 */
public class imgFile {
    public static void Download(String url, String Path, String name) throws IOException {
        URL newURL = new URL(url);
        BufferedImage img = ImageIO.read(newURL);
        File file = new File(Path, name);
        ImageIO.write(img, "jpg", file);
    }
}
