package org.bymarx.account.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EncodeImage {  
    private Image img;  
    private int width;  
    private int height;
    private String path;
    /** 
     * 
     */  
    public EncodeImage(MultipartFile file, String path) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(file.getBytes());   
        MemoryCacheImageInputStream mciis = new MemoryCacheImageInputStream(bais);
        img = ImageIO.read(mciis);      // 构造Image对象  
        width = img.getWidth(null);    // 得到源图宽  
        height = img.getHeight(null);  // 得到源图长  
        this.path = path;
//        resizeFix(400, 400);
    }  
    /** 
     * 按照宽度还是高度进行压缩 
     * @param w int 最大宽度 
     * @param h int 最大高度 
     */  
    @SuppressWarnings("unused")
    private void resizeFix(int w, int h) throws IOException {  
        if (width / height > w / h) {  
            resizeByWidth(w);  
        } else {  
            resizeByHeight(h);  
        }  
    }  
    /** 
     * 以宽度为基准，等比例放缩图片 
     * @param w int 新宽度 
     */  
    @SuppressWarnings("unused")
    private void resizeByWidth(int w) throws IOException {  
        int h = height * w / width;
       // resize(this., h);  
    }  
    /** 
     * 以高度为基准，等比例缩放图片 
     * @param h int 新高度 
     */  
    @SuppressWarnings("unused")
    private void resizeByHeight(int h) throws IOException {  
        int w = width * h / height;
       // resize(w, h);  
    }  
    /** 
     * 强制压缩/放大图片到固定的大小 
     * @param w int 新宽度 
     * @param h int 新高度 
     */  
    public void resize() throws IOException {  
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        BufferedImage image = new BufferedImage((int)(this.width*0.7),(int)(this.height*0.7),BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, (int)(this.width*0.7),(int)(this.height*0.7), null); // 绘制缩小后的图  
        File destFile = new File(path);  
        if(!destFile.getParentFile().exists()){
        	destFile.getParentFile().mkdirs();
        }
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流  
        // 可以正常实现bmp、png、gif转jpg  
        ImageIO.write(image, "jpeg", out);
        out.close();  
    }  
}  