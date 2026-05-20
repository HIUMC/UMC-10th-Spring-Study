package com.example.umc10th.domain.member.repository;

import java.util.List;

public interface PreferenceRepositoryCustom {
    void saveAllByJdbc(Long memberId, List<Long> categoryIds);
}
