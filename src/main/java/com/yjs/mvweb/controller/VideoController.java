package com.yjs.mvweb.controller;

import com.yjs.mvweb.pojo.Mv;
import com.yjs.mvweb.service.MvService;
import com.yjs.mvweb.videotool.ConverVideoTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;


@Controller
@RequestMapping("/file")
public class VideoController {
    @Autowired
    private MvService mvService;
    @RequestMapping("/upload")
    public String upload(){
        return "/uploadtest";
    }
    @RequestMapping("/addVideo")
    public ModelAndView uploadflie_Video(
            @RequestParam("file") CommonsMultipartFile file,
            HttpServletRequest req, HttpServletRequest request) {
        String uploadname;
        String uploadsrc;
        String uploadpicsrc;
        System.out.println("进入addVideo视频上传控制层");
        if (file.getSize() != 0) {
            //上传的多格式的视频文件-作为临时路径保存，转码以后删除-路径不能写//
            Mv mv=new Mv();
            String path = "G:/IdeaProjects/video/";

            File TempFile = new File(path);
            if (TempFile.exists()) {
                if (TempFile.isDirectory()) {
                    System.out.println("该文件夹存在。");
                }else {
                    System.out.println("同名的文件存在，不能创建文件夹。");
                }
            }else {
                System.out.println("文件夹不存在，创建该文件夹。");
                TempFile.mkdir();
            }

            // 获取上传时候的文件名
            String filename = file.getOriginalFilename();

            // 获取文件后缀名
            String filename_extension = filename.substring(filename
                    .lastIndexOf(".") + 1);
            System.out.println("视频的后缀名:"+filename_extension);

            //时间戳做新的文件名，避免中文乱码-重新生成filename
            long filename1 = new Date().getTime();
            filename = Long.toString(filename1)+"."+filename_extension;

            //去掉后缀的文件名
            String filename2 = filename.substring(0, filename.lastIndexOf("."));
            System.out.println("视频名为:"+filename2);

            //源视频地址+重命名后的视频名+视频后缀
            String yuanPATH =(path+filename);

            System.out.println("视频的完整文件名1:"+filename);
            System.out.println("源视频路径为:"+yuanPATH);

            //上传到本地磁盘/服务器
            try {
                System.out.println("写入本地磁盘/服务器");
                InputStream is = file.getInputStream();
                OutputStream os = new FileOutputStream(new File(path, filename));
                int len = 0;
                byte[] buffer = new byte[2048];

                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
                os.close();
                os.flush();
                is.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("========上传完成，开始调用转码工具类=======");
            //调用转码机制flv mp4 f4v m3u8 webm ogg放行直接播放，
            //asx，asf，mpg，wmv，3gp，mov，avi，wmv9，rm，rmvb等进行其他转码为mp4

            if (filename_extension.equals("avi") || filename_extension.equals("rm")
                    || filename_extension.equals("rmvb") || filename_extension.equals("wmv")
                    || filename_extension.equals("3gp")  || filename_extension.equals("mov")
                    ||filename_extension.equals("flv")   || filename_extension.equals("ogg")
                    ||filename_extension.equals("mp4")
            ) {

                ConverVideoTest c = new ConverVideoTest();
                c.run(yuanPATH);   //调用转码
                System.out.println("=================转码过程彻底结束=====================");
            }

            //获取转码后的mp4文件名
            String Mp4path = "G:/IdeaProjects/finishvideo/";
            filename2 = filename2+".mp4";
            String NewVideopath =Mp4path +filename2;
            System.out.println("新视频的url:"+NewVideopath);

            //删除临时文件
            File file2 = new File(path);
            if (!file2.exists()) {
                System.out.println("没有该文件");
            }
            if (!file2.isDirectory()) {
                System.out.println("没有该文件夹");
            }
            String[] tempList = file2.list();
            File temp = null;
            for (int i = 0; i < tempList.length; i++) {
                if (path.endsWith(File.separator)) {
                    temp = new File(path + tempList[i]);
                } else {
                    temp = new File(path + File.separator + tempList[i]);
                }
                if (temp.isFile() || temp.isDirectory()) {
                    temp.delete();		//删除文件夹里面的文件
                }
            }
            System.out.println("所有的临时视频文件删除成功");
            uploadname=filename2;
            uploadsrc=NewVideopath;
            mv.setUploadname(uploadname);
            mv.setUploadsrc(uploadsrc);
            mvService.insertMv(mv);
        }
        return null;
    }
    @RequestMapping("/test")
    public ModelAndView testUser(){
        ModelAndView model = new ModelAndView();
        model.setViewName("videoView");
        Mv mv = new Mv();
        mv.setUploadname("命运之夜");
        mv.setUploadid(28);
        model.addObject("mv",mv);
        return model;

    }
    @RequestMapping("/find")
    public String findMv(){
        Mv mv=mvService.getResourceById(2);
        System.out.println("name:"+mv.getUploadname());
        return "/index";
    }
}
