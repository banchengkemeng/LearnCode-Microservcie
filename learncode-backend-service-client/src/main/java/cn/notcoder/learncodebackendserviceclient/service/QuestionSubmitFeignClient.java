package cn.notcoder.learncodebackendserviceclient.service;

import cn.notcoder.learncodebackendmodel.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "learncode-backend-question-service", path = "/api/question/inner")
public interface QuestionSubmitFeignClient {

    @PostMapping("/submit/saveOrUpdate")
    boolean saveOrUpdate(@RequestBody QuestionSubmit questionSubmit);

    @GetMapping("/submit/updateAcceptedNum")
    void updateAcceptedNum(@RequestParam("questionId") Long questionId);
}
