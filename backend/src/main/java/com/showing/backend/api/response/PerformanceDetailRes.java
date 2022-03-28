package com.showing.backend.api.response;

import com.showing.backend.db.entity.performance.Performance;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("PerformanceResponse")
@Getter
@Setter
public class PerformanceDetailRes {

    @ApiModelProperty(name = "공연 id", example = "1")
    Long performanceId;

    @ApiModelProperty(name = "공연 이미지", example = "http://k.kakaocdn.net/dn/")
    String performanceImage;

    @ApiModelProperty(name = "공연 종류", example = "1")
    Integer performanceType;

    @ApiModelProperty(name = "공연 별점", example = "3")
    int rating;

    @ApiModelProperty(name = "공연 최근 시즌 정보")
    SeasonRes seasonRes;

    @ApiModelProperty(name = "공연 리뷰 목록 미리보기")
    List<PreviewReviewByPerformanceRes> previewReviewList;

    public static PerformanceDetailRes of(Performance performance, int rating, SeasonRes seasonRes, List<PreviewReviewByPerformanceRes> previewReviewList){
        PerformanceDetailRes res = new PerformanceDetailRes();
        res.setPerformanceId(performance.getId());
        res.setPerformanceImage(performance.getPerformanceImage());
        res.setPerformanceType(performance.getPerformanceType());
        res.setRating(rating);
        res.setSeasonRes(seasonRes);
        res.setPreviewReviewList(previewReviewList);

        return res;
    }
}
