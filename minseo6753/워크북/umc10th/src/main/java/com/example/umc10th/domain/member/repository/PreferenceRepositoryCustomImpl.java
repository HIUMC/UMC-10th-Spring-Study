package com.example.umc10th.domain.member.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@RequiredArgsConstructor
public class PreferenceRepositoryCustomImpl implements PreferenceRepositoryCustom {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void saveAllByJdbc(Long memberId, List<Long> categoryIds) {
        String sql = "INSERT INTO preference (member_id, category_id, created_at, updated_at) VALUES (?, ?, NOW(), NOW())";
        jdbcTemplate.batchUpdate(sql, categoryIds, categoryIds.size(),
                (ps, categoryId) -> {
                    ps.setLong(1, memberId);
                    ps.setLong(2, categoryId);
                });
    }
}
