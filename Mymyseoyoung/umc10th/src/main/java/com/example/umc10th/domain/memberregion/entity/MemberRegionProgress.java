package com.example.umc10th.domain.memberregion.entity;


import com.example.umc10th.domain.common.base.BaseEntity;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.region.entity.Region;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member_region_progress")
public class MemberRegionProgress extends BaseEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name ="member_region_progress_id")
    private Long id;

    //달성 개수
    private Integer completedCount;

    //전체 개수
    private Integer maxCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;


}