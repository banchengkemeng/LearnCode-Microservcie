package cn.notcoder.learncodebackendmodel.model.vo;

import cn.notcoder.learncodebackendmodel.model.judge.JudgeInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class QuestionSubmitVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 题目名称
     */
    private String questionName;

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
    private JudgeInfo judgeInfo;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}
