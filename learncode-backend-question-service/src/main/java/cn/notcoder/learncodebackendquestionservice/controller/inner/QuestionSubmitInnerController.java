package cn.notcoder.learncodebackendquestionservice.controller.inner;

import cn.notcoder.learncodebackendmodel.model.entity.QuestionSubmit;
import cn.notcoder.learncodebackendquestionservice.service.QuestionSubmitService;
import cn.notcoder.learncodebackendserviceclient.service.QuestionSubmitFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/inner/submit")
public class QuestionSubmitInnerController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @PostMapping("/saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody QuestionSubmit questionSubmit) {
        return questionSubmitService.saveOrUpdate(questionSubmit);
    }
}
