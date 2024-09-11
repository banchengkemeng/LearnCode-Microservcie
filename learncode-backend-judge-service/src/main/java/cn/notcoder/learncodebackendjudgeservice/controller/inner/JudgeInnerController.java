package cn.notcoder.learncodebackendjudgeservice.controller.inner;

import cn.notcoder.learncodebackendcommon.common.ErrorCode;
import cn.notcoder.learncodebackendcommon.common.ResultUtils;
import cn.notcoder.learncodebackendcommon.exception.BusinessException;
import cn.notcoder.learncodebackendjudgeservice.service.JudgeService;
import cn.notcoder.learncodebackendmodel.model.judge.JudgeRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/inner")
public class JudgeInnerController{

    @Resource
    private JudgeService judgeService;

    @PostMapping("/upload")
    String uploadJudgeCase(
            @RequestPart("file") MultipartFile file,
            @RequestParam("questionId") String questionId) {
        String rootPath = System.getProperty("user.dir") + "/case/" + questionId + "/";
        File rootDir = new File(rootPath);
        if (!rootDir.isDirectory()) {
            boolean res = rootDir.mkdirs();
            if (!res) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        }
        File saveFile = new File(rootPath + file.getOriginalFilename());

        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, e.getMessage());
        }

        return questionId;
    }


    @PostMapping("/do")
    public void judgeQuestion(@RequestBody JudgeRequest judgeRequest) {
        judgeService.judgeQuestion(judgeRequest);
    }
}
