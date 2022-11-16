package com.example.tripback.api.dto;

import com.example.tripback.common.utils.LocalDateFormatter;
import com.example.tripback.common.utils.LocalTimeFormatter;
import com.example.tripback.api.entity.Events;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Locale.KOREA;

public class EventDto {

    @Getter @Setter
    @NoArgsConstructor
    public static class saveRequestDto {
        @NotNull(message = "시작일은 Null 일 수 없습니다.")
        private String startDate;

        @NotNull(message = "종료일은 Null 일 수 없습니다.")
        private String endDate;

        @NotNull(message = "제목은 Null 일 수 없습니다.")
        private String title;

        private MultipartFile img;

        @NotNull
        private Long teamId;

        @Builder
        public saveRequestDto(String startDate, String endDate, String title) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
        }

        public Events toEntity(){
            LocalDateFormatter localDateFormatter = new LocalDateFormatter();
            return Events.builder()
                    .startDate(localDateFormatter.parse(startDate, KOREA))
                    .endDate(localDateFormatter.parse(endDate, KOREA))
                    .title(title).build();
        }
    }

    @Getter
    public static class PatchRequestDto {
        @NotNull
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(style = "yyyy-MM-dd")
        private LocalDate startDate;
        @NotNull
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(style = "yyyy-MM-dd")
        private LocalDate endDate;

        private String title;
        private MultipartFile img;

        @NotNull
        private Long eventId;
    }

    @Getter
    public static class eventList{
        private Long eventId;

        @DateTimeFormat(style = "yyyy-MM-dd")
        private LocalDate startDate;

        @DateTimeFormat(style = "yyyy-MM-dd")
        private LocalDate endDate;

        private String title;

        public eventList(Events events) {
            this.eventId = events.getEventId();
            this.startDate = events.getStartDate();
            this.endDate = events.getEndDate();
            this.title = events.getTitle();
        }
    }
    @Getter
    public static class responseList{
        private List<eventList> calendarList;

        public responseList(List<Events> calendarList) {
            this.calendarList = calendarList.stream()
                    .map(eventList::new)
                    .collect(Collectors.toList());
        }
    }
}
