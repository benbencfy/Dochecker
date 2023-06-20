package com.wetoband.template.action;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.fy.basejar.tool.ActionToolBase;
import com.wetoband.template.AppConfig;
import com.wetoband.template.util.R;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * BLP Apis
 */
public class ToolTemplateAction extends ActionToolBase {

    public final static ApplicationContext IOC = AppConfig.initApplicationContext();


    @Action
    public R hello(HttpServletRequest request) throws Exception {
        return R.ok().put("msg", "Hello World");
    }



    @Action
    public R submitFile(HttpServletRequest request) throws Exception {
        //获取文件服务
        FileService fileService = IOC.getBean(FileService.class);

        //获取文件
        MultipartFile file = RequestUtils.getMultipartFileParameter(request, "file");
        JSONObject DocJsonObject = JSONObject.parseObject(RequestUtils.getStringParameter(request, "Document"));
        // 确保文件是PDF或Word格式
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if (!fileType.equals(".pdf") && !fileType.equals(".doc") && !fileType.equals(".docx")) {
            return R.error().message("文件格式不正确，只接受.pdf, .doc, .docx");
        }

        // 提交文件
        int i = fileService.submitFile(file);
        return i == 0 ? R.error().message("文件提交失败") : R.ok().message("文件提交成功");
    }


    public static String runTool(String toolId, String action, Map<String, Object> map, String bandId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("toolID", toolId);
        params.put("action", action);
        params.put("bandID", bandId);
        params.put("gid", bandId);
        params.putAll(map);
        String url = "https://www.wetoband.com/tre//runToolWithToolShopToolID?returnType=VALUE";
        HttpResponse response = HttpRequest.post(url)
                .header("wtbauthc-trust", "true")
                .cookie("access_token=" + ToolTemplateAction.getCurrentContext().getAccessToken())
                .form(params)
                .execute().sync();
        return response.body();

    }
}
