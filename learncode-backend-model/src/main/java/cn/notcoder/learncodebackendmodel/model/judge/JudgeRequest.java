package cn.notcoder.learncodebackendmodel.model.judge;

import cn.notcoder.learncodebackendmodel.model.dto.question.JudgeCase;
import cn.notcoder.learncodebackendmodel.model.dto.question.JudgeConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JudgeRequest implements Serializable {
    private Long questionId;

    private Long questionSubmitId;

    private String lang;

    private String code;

    private JudgeConfig judgeConfig;

    private List<JudgeCase> judgeCase;

    private static final long serialVersionUID = 1L;
}
