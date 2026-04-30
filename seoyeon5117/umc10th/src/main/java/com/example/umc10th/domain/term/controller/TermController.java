package com.example.umc10th.domain.term.controller;

import com.example.umc10th.domain.term.dto.TermReqDTO;
import com.example.umc10th.domain.term.dto.TermResDTO;
import com.example.umc10th.domain.term.enums.TermSuccessCode;
import com.example.umc10th.domain.term.service.TermService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/terms")
public class TermController {

    private final TermService termService;

    // 약관 등록
    @PostMapping
    public ApiResponse<TermResDTO.CreateTerm> createTerm(
            @RequestBody TermReqDTO.CreateTerm dto
    ) {
        BaseSuccessCode code = TermSuccessCode.TERM_CREATE;
        return ApiResponse.onSuccess(code, termService.createTerm(dto));
    }

    // 활성화 된 약관 조회
    @GetMapping
    public ApiResponse<List<TermResDTO.GetTerm>> getTerms() {
        BaseSuccessCode code = TermSuccessCode.TERMS_GET;
        return ApiResponse.onSuccess(code, termService.getActiveTerms());
    }

    // 약관 조회
    @GetMapping("/{termId}")
    public ApiResponse<TermResDTO.GetTerm> getTerm(
            @PathVariable Long termId
    ) {
        BaseSuccessCode code = TermSuccessCode.TERM_GET;
        return ApiResponse.onSuccess(code, termService.getTerm(termId));
    }

    // 약관 수정
    @PutMapping("/{termId}")
    public ApiResponse<TermResDTO.UpdateTerm> updateTerm(
            @PathVariable Long termId,
            @RequestBody TermReqDTO.UpdateTerm dto
    ) {
        BaseSuccessCode code = TermSuccessCode.TERM_UPDATE;
        return ApiResponse.onSuccess(code, termService.updateTerm(termId, dto));
    }

    // 약관 삭제
    @DeleteMapping("/{termId}")
    public ApiResponse<Void> deleteTerm(
            @PathVariable Long termId
    ) {
        BaseSuccessCode code = TermSuccessCode.TERM_DELETE;
        termService.deleteTerm(termId);
        return ApiResponse.onSuccess(code, null);
    }
}
