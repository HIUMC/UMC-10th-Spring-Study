package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Policy;
import com.example.umc10th.domain.member.entity.mapping.Agreement;
import com.example.umc10th.domain.member.entity.mapping.Preference;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.PolicyException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.exception.code.PolicyErrorCode;
import com.example.umc10th.domain.member.repository.AgreementRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.member.repository.PolicyRepository;
import com.example.umc10th.domain.member.repository.PreferenceRepository;
import com.example.umc10th.domain.restaurant.entity.Category;
import com.example.umc10th.domain.restaurant.entity.address.EupMyeonDong;
import com.example.umc10th.domain.restaurant.exception.CategoryException;
import com.example.umc10th.domain.restaurant.exception.EupMyeonDongException;
import com.example.umc10th.domain.restaurant.exception.code.CategoryErrorCode;
import com.example.umc10th.domain.restaurant.exception.code.EupMyeonDongErrorCode;
import com.example.umc10th.domain.restaurant.repository.CategoryRepository;
import com.example.umc10th.domain.restaurant.repository.EupMyeonDongRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final EupMyeonDongRepository eupMyeonDongRepository;
    private final CategoryRepository categoryRepository;
    private final PreferenceRepository preferenceRepository;
    private final PolicyRepository policyRepository;
    private final AgreementRepository agreementRepository;

    @Transactional
    public MemberResDTO.Info signup(MemberReqDTO.SignUp request) {
        EupMyeonDong eupMyeonDong = eupMyeonDongRepository.findById(request.eupMyeonDongId())
                .orElseThrow(() -> new EupMyeonDongException(EupMyeonDongErrorCode.NOT_FOUND));

        Member member = MemberConverter.toMember(request, eupMyeonDong);
        memberRepository.save(member);

        for (Long categoryId : request.categoryIds()) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new CategoryException(CategoryErrorCode.NOT_FOUND));

            Preference preference = Preference.builder()
                    .member(member)
                    .category(category)
                    .build();

            member.getPreference().add(preference);
            preferenceRepository.save(preference);
        }

        for (MemberReqDTO.AgreementReq agreementReq : request.agreements()) {
            Policy policy = policyRepository.findById(agreementReq.policyId())
                    .orElseThrow(() -> new PolicyException(PolicyErrorCode.NOT_FOUND));

            Agreement agreement = Agreement.builder()
                    .member(member)
                    .policy(policy)
                    .agreed(agreementReq.agreed())
                    .agreeDate(LocalDate.now())
                    .build();

            agreementRepository.save(agreement);
        }

        return MemberConverter.toInfo(member);
    }

    public MemberResDTO.Location getLocation() {
        return null;
    }

    public MemberResDTO.Location updateLocation(MemberReqDTO.Location request) {
        return null;
    }

    public MemberResDTO.MissionCount getMissionCount() {
        return null;
    }

    public MemberResDTO.MyPage myPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        return MemberConverter.toMyPage(member);
    }
}
