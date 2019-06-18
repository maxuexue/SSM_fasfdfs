package com.qf.controller;

import com.qf.pojo.Items;
import com.qf.service.ItemsService;
import com.qf.utils.FastDFSClient;
import com.qf.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    //上传图片,并新增商品
    @RequestMapping("/doAdd")
    @ResponseBody
    public ResultUtils addItems(Items items, @RequestParam(name="img") MultipartFile file){
        StringBuffer sb=new StringBuffer("http://192.168.111.166/");
        try {
            //实现图片上传,得到图片的原始名字
            String filename = file.getOriginalFilename();
            //得到图片的后缀名
            String suffix = filename.substring(filename.lastIndexOf(".") + 1);
            FastDFSClient fastDFSClient=new FastDFSClient("client.conf");
            String[] strings = fastDFSClient.uploadFile(file.getBytes(), suffix);

            //上传成功后,返回图片的地址,格式://group1   M00/00/00/xxxxxx.jpg
            for (int i = 0; i < strings.length; i++) {
                sb.append(strings[i]);
                if (i==0){
                    //返回图片的地址,格式://group1   M00/00/00/xxxxxx.jpg中间需要"/"分隔符
                    //sb.append(File.separator);
                    sb.append("/");
                }
            }
            System.out.println("上传成功,图片访问地址:"+sb);
        } catch (Exception e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return ResultUtils.error("上传失败");
        }

        //把图片地址添加到表中
        items.setImages(sb.toString());

        int i = itemsService.addItems(items);
        if (i>0){
            return ResultUtils.success("添加商品成功");
        }
        return ResultUtils.error("添加商品失败");
    }

    //查询所有商品
    @RequestMapping("/doFindAll")
    @ResponseBody
    public ResultUtils findAll(){
        List<Items> itemsList = itemsService.findAll();

        //虽然put不是静态方法,但是success返回了ResultUtils对象,所以可以调用put方法
        return ResultUtils.success().put("items", itemsList);
    }

    //webuploader上传图片
    @RequestMapping("/upload")
    @ResponseBody
    public ResultUtils upload(@RequestParam MultipartFile file){
        StringBuffer sb=new StringBuffer("http://192.168.111.166/");
        try {
            //实现图片上传,得到图片的原始名字
            String filename = file.getOriginalFilename();
            //得到图片的后缀名
            String suffix = filename.substring(filename.lastIndexOf(".") + 1);
            FastDFSClient fastDFSClient=new FastDFSClient("client.conf");
            String[] strings = fastDFSClient.uploadFile(file.getBytes(), suffix);

            //上传成功后,返回图片的地址,格式://group1   M00/00/00/xxxxxx.jpg
            for (int i = 0; i < strings.length; i++) {
                sb.append(strings[i]);
                if (i==0){
                    //返回图片的地址,格式://group1   M00/00/00/xxxxxx.jpg中间需要"/"分隔符
                    //sb.append(File.separator);
                    sb.append("/");
                }
            }
            return ResultUtils.success("上传成功").put("path", sb.toString());
        } catch (Exception e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return ResultUtils.error("上传失败");
        }
    }

    //新增商品
    @RequestMapping("/doAddItems")
    @ResponseBody
    public ResultUtils uploadAdd(Items items){
        int i = itemsService.addItems(items);
        if (i>0){
            return ResultUtils.success("新增成功");
        }
        return ResultUtils.error("新增失败");
    }
}
