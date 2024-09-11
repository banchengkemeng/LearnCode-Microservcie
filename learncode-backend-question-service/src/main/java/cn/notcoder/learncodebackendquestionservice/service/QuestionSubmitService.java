package cn.notcoder.learncodebackendquestionservice.service;

import cn.notcoder.learncodebackendmodel.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import cn.notcoder.learncodebackendmodel.model.dto.questionsubmit.QuestionSubmitRequest;
import cn.notcoder.learncodebackendmodel.model.entity.QuestionSubmit;
import cn.notcoder.learncodebackendmodel.model.entity.User;
import cn.notcoder.learncodebackendmodel.model.vo.QuestionSubmitVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* 针对表【question_submit(题目提交)】的数据库操作Service
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    Long doSubmitQuestion(QuestionSubmitRequest questionSubmitRequest, User loginUser);
    QuestionSubmitVO getQuestionSubmitVOById(Long id);
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(QuestionSubmitQueryRequest questionSubmitQueryRequest);
    Boolean deleteByQuestionIds(List<Long> questionIds);
}
