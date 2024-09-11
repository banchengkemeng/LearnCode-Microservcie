package cn.notcoder.learncodebackendquestionservice.service;

import cn.notcoder.learncodebackendmodel.model.dto.question.QuestionAddRequest;
import cn.notcoder.learncodebackendmodel.model.dto.question.QuestionQueryRequest;
import cn.notcoder.learncodebackendmodel.model.dto.question.QuestionUpdateRequest;
import cn.notcoder.learncodebackendmodel.model.entity.Question;
import cn.notcoder.learncodebackendmodel.model.entity.User;
import cn.notcoder.learncodebackendmodel.model.vo.QuestionAdminVO;
import cn.notcoder.learncodebackendmodel.model.vo.QuestionVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* 针对表【question(题目)】的数据库操作Service
*/
public interface QuestionService extends IService<Question> {
    Long addQuestion(QuestionAddRequest questionAddRequest, User loginUser);
    Long updateQuestion(QuestionUpdateRequest questionUpdateRequest);
    Boolean deleteQuestionByIds(List<Long> ids);
    QuestionVO getQuestionVOById(Long id);
    QuestionAdminVO getQuestionAdminVOById(Long id);
    Page<QuestionVO> getQuestionVOPage(QuestionQueryRequest questionQueryRequest);
    Page<QuestionAdminVO> getQuestionAdminVOPage(QuestionQueryRequest questionQueryRequest);
}
