package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.Preference;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.PolicyException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.exception.code.PolicyErrorCode;
import com.example.umc10th.domain.member.repository.AgreementRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.member.repository.PolicyRepository;
import com.example.umc10th.domain.restaurant.entity.address.EupMyeonDong;
import com.example.umc10th.domain.restaurant.exception.CategoryException;
import com.example.umc10th.domain.restaurant.exception.EupMyeonDongException;
import com.example.umc10th.domain.restaurant.exception.code.CategoryErrorCode;
import com.example.umc10th.domain.restaurant.exception.code.EupMyeonDongErrorCode;
import com.example.umc10th.domain.restaurant.repository.CategoryRepository;
import com.example.umc10th.domain.restaurant.repository.EupMyeonDongRepository;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final EupMyeonDongRepository eupMyeonDongRepository;
    private final CategoryRepository categoryRepository;
    private final PolicyRepository policyRepository;
    private final AgreementRepository agreementRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public MemberResDTO.Info signup(MemberReqDTO.SignUp request) {

        EupMyeonDong eupMyeonDong = eupMyeonDongRepository.findById(request.eupMyeonDongId())
                .orElseThrow(() -> new EupMyeonDongException(EupMyeonDongErrorCode.NOT_FOUND));

        String encodedPassword = passwordEncoder.encode(request.password());

        Member member = MemberConverter.toMember(request, encodedPassword, eupMyeonDong);

        List<Preference> preferenceList = request.categoryIds().stream().map(
                        id -> Preference.builder()
                                .member(member)
                                .category(categoryRepository.findById(id).orElseThrow(
                                        () -> new CategoryException(CategoryErrorCode.NOT_FOUND)
                                ))
                                .build())
                .toList();

        member.getPreference().addAll(preferenceList);
        memberRepository.save(member); // cascade

        // 정책 ID 일괄 검증
        if (request.agreements() != null && !request.agreements().isEmpty()) {
            List<Long> policyIds = request.agreements().stream()
                    .map(MemberReqDTO.AgreementReq::policyId)
                    .toList();
            if (policyIds.size() != policyRepository.findAllById(policyIds).size()) {
                throw new PolicyException(PolicyErrorCode.NOT_FOUND);
            }
        }

        // Bulk Insert Agreement
        if (request.agreements() != null && !request.agreements().isEmpty()) {
            agreementRepository.saveAllByJdbc(member.getId(), request.agreements());
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

    public MemberResDTO.MyPage myPage(AuthMember authMember) {
        return MemberConverter.toMyPage(authMember.getMember());
    }

    @Transactional(readOnly = true)
    public MemberResDTO.Token login(MemberReqDTO.Login request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        if(!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        AuthMember authMember = new AuthMember(member);
        String accessToken = jwtUtil.createAccessToken(authMember);

        return MemberConverter.toToken(accessToken);
    }
}
