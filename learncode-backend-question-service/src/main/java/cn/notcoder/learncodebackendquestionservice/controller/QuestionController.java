package cn.notcoder.learncodebackendquestionservice.controller;

import cn.notcoder.learncodebackendcommon.annotation.AuthCheck;
import cn.notcoder.learncodebackendcommon.common.BaseResponse;
import cn.notcoder.learncodebackendcommon.common.ErrorCode;
import cn.notcoder.learncodebackendcommon.common.ResultUtils;
import cn.notcoder.learncodebackendcommon.constant.UserConstant;
import cn.notcoder.learncodebackendcommon.exception.BusinessException;
import cn.notcoder.learncodebackendmodel.model.dto.question.QuestionAddRequest;
import cn.notcoder.learncodebackendmodel.model.dto.question.QuestionQueryRequest;
import cn.notcoder.learncodebackendmodel.model.dto.question.QuestionUpdateRequest;
import cn.notcoder.learncodebackendmodel.model.entity.User;
import cn.notcoder.learncodebackendmodel.model.enums.QuestionSubmitLangEnum;
import cn.notcoder.learncodebackendmodel.model.vo.QuestionAdminVO;
import cn.notcoder.learncodebackendmodel.model.vo.QuestionVO;
import cn.notcoder.learncodebackendquestionservice.service.impl.QuestionServiceImpl;
import cn.notcoder.learncodebackendserviceclient.service.JudgeFeignClient;
import cn.notcoder.learncodebackendserviceclient.service.UserFeignClient;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static cn.notcoder.learncodebackendcommon.constant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/")
@Slf4j
public class QuestionController {

    @Resource
    private QuestionServiceImpl questionService;

    @Resource
    private UserFeignClient userFeignClient;

    @Resource
    private JudgeFeignClient judgeFeignClient;

    @PostMapping("/admin/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addQuestion(@RequestBody QuestionAddRequest questionAddRequest, HttpServletRequest request) {
        User loginUser = userFeignClient.getLoginUser(request);
        Long questionId = questionService.addQuestion(questionAddRequest, loginUser);
        return ResultUtils.success(questionId);
    }

    @PostMapping("/admin/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> updateQuestion(@RequestBody QuestionUpdateRequest questionUpdateRequest) {
        Long questionId = questionService.updateQuestion(questionUpdateRequest);
        return ResultUtils.success(questionId);
    }

    @PostMapping("/admin/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteQuestionByIds(@RequestBody List<Long> ids) {
        Boolean result = questionService.deleteQuestionByIds(ids);
        return ResultUtils.success(result);
    }

    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<QuestionVO> getQuestionVOById(Long id) {
        QuestionVO questionVOById = questionService.getQuestionVOById(id);
        return ResultUtils.success(questionVOById);
    }

    @GetMapping("/admin/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<QuestionAdminVO> getQuestionAdminVOById(Long id) {
        QuestionAdminVO questionAdminVOById = questionService.getQuestionAdminVOById(id);
        return ResultUtils.success(questionAdminVOById);
    }

    @PostMapping("/list")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<Page<QuestionVO>> getQuestionVOPage(@RequestBody QuestionQueryRequest questionQueryRequest) {
        Page<QuestionVO> questionVOPage = questionService.getQuestionVOPage(questionQueryRequest);
        return ResultUtils.success(questionVOPage);
    }

    @PostMapping("/admin/list")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<QuestionAdminVO>> getQuestionAdminVOPage(@RequestBody QuestionQueryRequest questionQueryRequest) {
        Page<QuestionAdminVO> questionAdminVOPage = questionService.getQuestionAdminVOPage(questionQueryRequest);
        return ResultUtils.success(questionAdminVOPage);
    }

    @PostMapping("/admin/upload")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<String > uploadJudgeCaseFile(@RequestParam("file") MultipartFile file, String questionId)  {
        if (questionId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (file == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = file.getName();
        if (name.contains("/") || name.contains("\\")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "存在危险字符");
        }

        String resQuestionId = judgeFeignClient.uploadJudgeCase(file, questionId);

        return ResultUtils.success(
                resQuestionId
        );
    }

    @GetMapping("/lang/list")
    public BaseResponse<List<String>> getLangList() {
        return ResultUtils.success(QuestionSubmitLangEnum.getValues());
    }
}
