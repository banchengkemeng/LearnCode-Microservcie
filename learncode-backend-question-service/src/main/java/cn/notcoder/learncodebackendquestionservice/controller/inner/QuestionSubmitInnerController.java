package cn.notcoder.learncodebackendquestionservice.controller.inner;

import cn.notcoder.learncodebackendmodel.model.entity.QuestionSubmit;
import cn.notcoder.learncodebackendquestionservice.service.QuestionSubmitService;
import cn.notcoder.learncodebackendserviceclient.service.QuestionSubmitFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/inner/submit")
@Slf4j
public class QuestionSubmitInnerController implements QuestionSubmitFeignClient{

    @Resource
    private QuestionSubmitService questionSubmitService;

    @PostMapping("/saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody QuestionSubmit questionSubmit) {
        return questionSubmitService.saveOrUpdate(questionSubmit);
    }

    @GetMapping("/updateAcceptedNum")
    public void updateAcceptedNum(@RequestParam("questionId") Long questionId) {
        Boolean result = questionSubmitService.updateAcceptedNum(questionId);
        if (!result) {
            log.error("更新通过数失败, questionId: {}", questionId);
        }
    }
}
