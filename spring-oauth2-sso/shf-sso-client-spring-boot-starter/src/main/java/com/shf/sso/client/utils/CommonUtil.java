package com.shf.sso.client.utils;

import cn.hutool.core.date.DateUtil;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/4/25 18:18
 * @Version V1.0
 **/
@Component
public class CommonUtil {
    /**
     * 获取请求IP
     * @param request
     * @return
     */
    public static String getIP(HttpServletRequest request){
        String ip=request.getHeader("x-forwarded-for");
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("Proxy-Client-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("X-Real-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getRemoteAddr();
        }
        return ip;
    }
    public static String getUrl(HttpServletRequest request){
        return request.getRequestURL().toString();
    }
    /**
     * 返回UUID - code
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    public static String getUUID(Integer len){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        if(uuid.length() < len){
            return uuid;
        }
        return uuid.substring(0, len);
    }

    /**
     * 将String类型的时间转为java.util.Date
     * @param time
     * @return
     */
    public static Date parseDate(String time){
        return DateUtil.parse(time, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取HttpServletRequest
     * @return
     */
    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取HttpServletResponse
     * @return
     */
    public static HttpServletResponse getResponse(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 创建文件 - 如果路径不存在则创建路径
     * @param path
     * @param filename
     * @throws IOException
     */
    public static File createFile(String path, String filename) throws IOException {
        File file = new File(path + File.separator + filename);
        File fileParent = file.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        file.createNewFile();
        return file;
    }
    /**
     * 创建文件 - 如果路径不存在则创建路径
     * @param path
     * @throws IOException
     */
    public static File createFile(String path) throws IOException {
        File file = new File(path);
        File fileParent = file.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        file.createNewFile();
        return file;
    }
    /**
     * 因为在ie浏览器下，MultipartFile的getOriginalFilename()方法，返回的文件名包含了带盘符的路径，
     * 如果需要只获取原来的文件名，需要处理一下。
     * 在获取文件名后，判断是否在IE环境下运行的此方法，并做相应的字符串截取的处理，即可返回正确的结果。
     * @param file
     * @return
     */
    public static String getOriginalFileName(MultipartFile file){
        // 获取文件名
        String fileName = file.getOriginalFilename();
        //判断是否为IE浏览器的文件名，IE浏览器下文件名会带有盘符信息
        // Check for Unix-style path
        int unixSep = fileName.lastIndexOf('/');
        // Check for Windows-style path
        int winSep = fileName.lastIndexOf('\\');
        // Cut off at latest possible point
        int pos = (winSep > unixSep ? winSep : unixSep);
        if (pos != -1)  {
            // Any sort of path separator found...
            fileName = fileName.substring(pos + 1);
        }
        return fileName;
    }
    public static boolean matchers(String url, HttpServletRequest request) {
        AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
        if (matcher.matches(request)) {
            return true;
        }
        return false;
    }

}
