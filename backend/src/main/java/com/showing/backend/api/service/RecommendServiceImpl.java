package com.showing.backend.api.service;

import com.showing.backend.api.response.PerformanceRes;
import com.showing.backend.db.repository.performance.PerformanceRepository;
import com.showing.backend.db.repository.recommend.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class RecommendServiceImpl implements RecommendService {

    private final RecommendRepository recommendRepository;
    private final PerformanceService performanceService;

    /**
     * performanceId 공연과 비슷한 공연 목록을 조회한다.
     * type이 0이라면 공연완료, 공연중, 공연예정 조회
     * type이 1이라면 공연중, 공연예정 조회
     */
    @Override
    public List<PerformanceRes> getRecommendPerformanceList(int type, List<Long> performanceIdList) {
        // performanceId 공연에 대해 공연완료, 공연중, 공연예정 상태인 추천 공연을 15개 조회한다.
        return recommendRepository.getTopCountRandomRecommendListByPerformanceId(type, 15, performanceIdList);
    }

    /**
     * userId 사용자가 별점 10점 중 starPoint점 이상 등록한 공연들과 비슷한 공연중, 공연예정 목록을 조회한다.
     */
    @Override
    public List<PerformanceRes> getRecommendPerformanceListByUser(Long userId, int starPoint) {
        // userId 사용자가 별점 10점 중 starPoint점 이상 등록한 공연 Id 조회
        List<Long> performanceIdList = performanceService.getPerformanceIdListByUserAndStarPoint(userId, starPoint);
        return getRecommendPerformanceList(1, performanceIdList);
    }

}