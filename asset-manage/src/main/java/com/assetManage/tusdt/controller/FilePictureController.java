package com.assetManage.tusdt.controller;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.base.constants.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-02
 * Time: 01:31
 */
@Api(protocols = "http,https", tags = {"file"}, value = "/asset_manage/file",description = "图片文件上传显示接口")
@RestController
@RequestMapping(value = "/asset_manage/file")
public class FilePictureController {

    @Value("${cbs.imagesPath}")
    private String theSetDir; //全局配置文件中设置的图片的路径

    @ApiOperation(value = "上传文件到指定本地目录-支持多文件上传，无法使用swagger测试", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "上传成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<List<String>> fileUpload(HttpServletRequest request, MultipartFile filename, Model model) throws Exception {
        ResponseData<List<String>> responseData = new ResponseData<>();
        List<String> filesPath = new ArrayList<>();

        String parentDirPath = theSetDir.substring(theSetDir.indexOf(':')+1, theSetDir.length()); //通过设置的那个字符串获得存放图片的目录路径

        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        if(files.size()>3) {
            responseData.setError("文件数目大于三个");
            return responseData;
        }
        File parentDir = new File(parentDirPath);
        if(!parentDir.exists()) //如果那个目录不存在先创建目录
        {
            parentDir.mkdir();
        }
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            Date date = new Date();
            String filePath = parentDirPath + "/" + date.getTime() + fileName;
            File fileTemp = new File(filePath);
            try {
                file.transferTo(fileTemp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            filesPath.add(filePath);
        }
        responseData.setOK("上传成功",filesPath);
        return responseData;
    }

}
