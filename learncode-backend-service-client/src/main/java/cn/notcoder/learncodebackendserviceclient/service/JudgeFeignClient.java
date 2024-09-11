package cn.notcoder.learncodebackendserviceclient.service;


import cn.notcoder.learncodebackendmodel.model.judge.JudgeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "learncode-backend-judge-service", path = "/api/judge/inner")
public interface JudgeFeignClient {

    @PostMapping("/do")
    void judgeQuestion(@RequestBody JudgeRequest judgeRequest);

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadJudgeCase(@RequestPart("file") MultipartFile file, @RequestParam("questionId") String questionId);
}
