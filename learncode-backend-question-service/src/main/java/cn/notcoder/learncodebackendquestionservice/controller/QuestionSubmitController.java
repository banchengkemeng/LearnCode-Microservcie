package cn.notcoder.learncodebackendquestionservice.controller;

import cn.notcoder.learncodebackendcommon.annotation.AuthCheck;
import cn.notcoder.learncodebackendcommon.common.BaseResponse;
import cn.notcoder.learncodebackendcommon.common.ResultUtils;
import cn.notcoder.learncodebackendcommon.constant.UserConstant;
import cn.notcoder.learncodebackendmodel.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import cn.notcoder.learncodebackendmodel.model.dto.questionsubmit.QuestionSubmitRequest;
import cn.notcoder.learncodebackendmodel.model.entity.User;
import cn.notcoder.learncodebackendmodel.model.vo.QuestionSubmitVO;
import cn.notcoder.learncodebackendquestionservice.service.QuestionSubmitService;
import cn.notcoder.learncodebackendserviceclient.service.UserFeignClient;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static cn.notcoder.learncodebackendcommon.constant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserFeignClient userFeignClient;

    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<Long> doSubmit(
            @RequestBody QuestionSubmitRequest questionSubmitRequest,
            HttpServletRequest request) {
        User loginUser = userFeignClient.getLoginUser(request);
        Long submissionID = questionSubmitService.doSubmitQuestion(questionSubmitRequest, loginUser);
        return ResultUtils.success(submissionID);
    }

    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<QuestionSubmitVO> getQuestionSubmitVO(Long id) {
        QuestionSubmitVO questionSubmitVOById = questionSubmitService.getQuestionSubmitVOById(id);
        return ResultUtils.success(questionSubmitVOById);
    }

    @PostMapping("/list")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<Page<QuestionSubmitVO>> getQuestionSubmitVOPage(
            @RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest) {
        Page<QuestionSubmitVO> questionSubmitVOPage = questionSubmitService.getQuestionSubmitVOPage(questionSubmitQueryRequest);
        return ResultUtils.success(questionSubmitVOPage);
    }
}
