package cn.notcoder.learncodebackendjudgeservice.service;


import cn.notcoder.learncodebackendmodel.model.judge.JudgeInfo;
import cn.notcoder.learncodebackendmodel.model.judge.JudgeRequest;

import java.util.concurrent.Future;

public interface JudgeService {
    Future<JudgeInfo> judgeQuestion(JudgeRequest judgeRequest);
}
