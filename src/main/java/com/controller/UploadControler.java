package com.controller;

import com.DBUtile.CommUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class UploadControler {

    @ResponseBody
    @RequestMapping("upload")
    public String upload(HttpServletRequest request)  {
CommUtil.flag = false;
CommUtil.totalContent = 0;
CommUtil.currentContent = 0;

        try {
        DataInputStream dis = new DataInputStream(request.getInputStream());
        int contentLength = request.getContentLength();
        String contentType = request.getContentType();
        Integer equal =  contentType.indexOf("=");
        FileOutputStream fos = null;
        byte[] body = new byte[19000];
        int totalReadSize = 0;
        int k = 0;
CommUtil.totalContent = contentLength;
        contentType = contentType.substring(equal+1, contentType.length());
        File file = null;
        while (totalReadSize < contentLength) {
            int readSize = dis.read(body, 0, 19000);
 CommUtil.currentContent += readSize;
            if (k == 0) {
                String textBody = new String(body, "iso-8859-1");
                int pos = textBody.indexOf("Content-Type");
                pos = textBody.indexOf("\n", pos + 1);
                pos = textBody.indexOf("\n", pos + 1);
                String tempBody = textBody.substring(0, pos);
                file = createFile(request,tempBody);
                fos = new FileOutputStream(file);
                int start = tempBody.getBytes("iso-8859-1").length + 1;
                int end = textBody.getBytes("iso-8859-1").length;
                textBody = textBody.substring(start, end);
                int endTypePos = textBody.indexOf(contentType);
                if (endTypePos > 0) {
                    int endLen = contentType.getBytes("ISO-8859-1").length;
                        end = readSize - start - (endLen + 8);
                } else {
                    end = readSize - start;
                }
                fos.write(body, start, end);
CommUtil.flag = true;
            }else{
                fos.write(body, 0, readSize);
            }
            fos.flush();
            body = new byte[19000];
            totalReadSize += readSize;
            k++;
        }
        fos.flush();
        fos.close();
        dis.close();

CommUtil.path = file.getName();
         Thread.sleep(100);
      }catch (Exception e){}
CommUtil.flag = false;
        return "success";
    }


    // 显示进度条
    @ResponseBody
    @RequestMapping("queryProgress")
    public String queryProgress(){
        double k = 0.0;
        if (CommUtil.flag) {
            k = (CommUtil.currentContent / CommUtil.totalContent) ;
             k= k * 100;
        }
        String jsonObject = k+"";
       return  jsonObject;
    }

    // 得到路径
    @ResponseBody
    @RequestMapping("queryPath")
    public String queryPath(){
        String jsonObject =CommUtil.path ;
        return jsonObject;
    }


    public  File createFile(HttpServletRequest request,String textBody){
        int index = textBody.indexOf("Content-Type");
        String tempBodyText = textBody.substring(0, index);
        int lastIndexPoint=tempBodyText.lastIndexOf(".");
        String suffx=tempBodyText.substring(lastIndexPoint,index-3);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String NewFileName = sdf.format(new Date()) ;
        NewFileName+=suffx;
        String rootPath = request.getRealPath(CommUtil.uploadParent) + "\\" + NewFileName;
        File file = new File(rootPath);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        CommUtil.suffix=suffx;
        return file;
    }

}
