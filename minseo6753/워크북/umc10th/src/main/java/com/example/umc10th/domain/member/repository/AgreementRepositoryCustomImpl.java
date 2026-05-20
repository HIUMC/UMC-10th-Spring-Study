package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class AgreementRepositoryCustomImpl implements AgreementRepositoryCustom {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void saveAllByJdbc(Long memberId, List<MemberReqDTO.AgreementReq> agreements) {
        String sql = "INSERT INTO agreement (member_id, policy_id, agreed, agree_date, created_at, updated_at) VALUES (?, ?, ?, ?, NOW(), NOW())";
        jdbcTemplate.batchUpdate(sql, agreements, agreements.size(),
                (ps, agreementReq) -> {
                    ps.setLong(1, memberId);
                    ps.setLong(2, agreementReq.policyId());
                    ps.setBoolean(3, agreementReq.agreed());
                    ps.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
                });
    }
}
