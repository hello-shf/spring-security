package com.shf.security.utils;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/5/5 10:53
 * @Version V1.0
 **/
public class VerifyCodeUtil {
    public static final String SESSION_KEY = "verifyCode";
    public static final String BUFFIMG_KEY = "buffImg";
    /**
     * 验证码图片的宽度。
     */
    private static int width = 100;
    public static final long VERIFYCODE_TIMEOUT = 30*1000;//一分钟

    /**
     *  验证码图片的高度。
     */
    private static int height = 30;

    /**
     * 验证码字符个数
     */
    private static int codeCount = 4;

    /**
     * 字体高度
     */
    private static int fontHeight;

    /**
     * 干扰线数量
     */
    private static int interLine = 12;

    /**
     * 第一个字符的x轴值，因为后面的字符坐标依次递增，所以它们的x轴值是codeX的倍数
     */
    private static int codeX;

    /**
     * codeY ,验证字符的y轴值，因为并行所以值一样
     */
    private static int codeY;

    /**
     * codeSequence 表示字符允许出现的序列值
     */
    static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    public static Map<String, Object> getVerifyCode(){
        Map<String, Object> result = new HashMap<>();
        //width-4 除去左右多余的位置，使验证码更加集中显示，减得越多越集中。
        //codeCount+1     //等比分配显示的宽度，包括左右两边的空格
        codeX = (width-4) / (codeCount+1);
        //height - 10 集中显示验证码
        fontHeight = height - 10;
        codeY = height - 7;
        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D gd = buffImg.createGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);
        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Times New Roman", Font.PLAIN, fontHeight);
        // 设置字体。
        gd.setFont(font);
        // 画边框。
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);
        // 随机产生16条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(Color.gray);
        for (int i = 0; i < interLine; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;
        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red,green,blue));
            gd.drawString(strRand, (i + 1) * codeX, codeY);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        result.put(BUFFIMG_KEY, buffImg);
        result.put(SESSION_KEY, randomCode.toString());
        return result;
    }
    /**
     * 定时删除session中存在的验证码信息
     * @param session
     */
    public static void removeAttrbute(final HttpSession session) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                session.removeAttribute(SESSION_KEY);
                timer.cancel();
            }
        }, VERIFYCODE_TIMEOUT);
    }
}
