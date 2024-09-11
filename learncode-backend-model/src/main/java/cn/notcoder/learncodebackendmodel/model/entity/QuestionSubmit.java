package cn.notcoder.learncodebackendmodel.model.entity;

import cn.hutool.json.JSONUtil;
import cn.notcoder.learncodebackendmodel.model.dto.questionsubmit.QuestionSubmitRequest;
import cn.notcoder.learncodebackendmodel.model.enums.QuestionSubmitStatusEnum;
import cn.notcoder.learncodebackendmodel.model.judge.JudgeInfo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目提交
 * @TableName question_submit
 */
@TableName(value ="question_submit")
@Data
public class QuestionSubmit implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 提交用户 id
     */
    private Long userId;

    /**
     * 编程语言
     */
    private String lang;

    /**
     * 提交代码
     */
    private String code;

    /**
     * 状态(0-待判题，1-判题中，2-成功，3-失败)
     */
    private Integer status;

    /**
     * 判题信息(json)
     */
    private String judgeInfo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    public static QuestionSubmit waiting(QuestionSubmitRequest questionSubmitRequest, Long userId) {
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setQuestionId(questionSubmitRequest.getQuestionId());
        questionSubmit.setUserId(userId);
        questionSubmit.setCode(questionSubmitRequest.getCode());
        questionSubmit.setLang(questionSubmitRequest.getLang());
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(JudgeInfo.waitting(
                questionSubmitRequest.getQuestionId().toString()
        )));
        return questionSubmit;
    }

    public static QuestionSubmit judging(Long questionSubmitId, JudgeInfo judgeInfo) {
        return update(
                questionSubmitId,
                judgeInfo,
                QuestionSubmitStatusEnum.RUNNING
        );
    }

    public static QuestionSubmit success(Long questionSubmitId, JudgeInfo judgeInfo) {
        return update(
                questionSubmitId,
                judgeInfo,
                QuestionSubmitStatusEnum.SUCCESS
        );
    }

    public static QuestionSubmit failed(Long questionSubmitId, JudgeInfo judgeInfo) {
        return update(
                questionSubmitId,
                judgeInfo,
                QuestionSubmitStatusEnum.FAILED
        );
    }

    private static QuestionSubmit update(
            Long questionSubmitId,
            JudgeInfo judgeInfo,
            QuestionSubmitStatusEnum status) {
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setId(questionSubmitId);
        questionSubmit.setStatus(status.getValue());
        questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        return questionSubmit;

    }
}