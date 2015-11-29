package org.pengpark.Data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * Created by pengpark on 2015. 11. 27..
 */
public class imgFile {
    // 해당 URL에서 이미지를 받기 위함
    // url에서 정보를 불러오면
    // 이미지를 Path에 저장하고
    // name으로 이미지 이름을 지정
    public static void Download(String url, String Path, String name) throws IOException {
        URL newURL = new URL(url);
        BufferedImage img = ImageIO.read(newURL);
        File file = new File(Path, name);
        ImageIO.write(img, "jpg", file);
    }
}
