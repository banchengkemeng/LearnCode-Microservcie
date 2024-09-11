package cn.notcoder.learncodebackendserviceclient.service;

import cn.notcoder.learncodebackendmodel.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "learncode-backend-question-service", path = "/api/question/inner")
public interface QuestionSubmitFeignClient {

    @PostMapping("/submit/saveOrUpdate")
    boolean saveOrUpdate(@RequestBody QuestionSubmit questionSubmit);
}
