package com.example.tripback.events;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventDto {

    @Getter
    @NoArgsConstructor
    public static class saveRequestDto {
        @NotNull(message = "시작일은 Null 일 수 없습니다.")
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(style = "yyyy-MM-dd")
        private LocalDate startDate;

        @NotNull(message = "종료일은 Null 일 수 없습니다.")
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(style = "yyyy-MM-dd")
        private LocalDate endDate;

        @Temporal(TemporalType.TIME)
        @DateTimeFormat(style = "hh:mm")
        private LocalTime startTime;
        @Temporal(TemporalType.TIME)
        @DateTimeFormat(style = "hh:mm")
        private LocalTime endTime;

        @NotNull(message = "제목은 Null 일 수 없습니다.")
        private String title;

        @NotNull
        private Long teamId;

        @Builder
        public saveRequestDto(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, String title) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.startTime = startTime;
            this.endTime = endTime;
            this.title = title;
        }

        public Events toEntity(){
            return Events.builder().startDate(startDate).endDate(endDate).startTime(startTime).endTime(endTime).title(title).build();
        }
    }
}
