package com.example.tripback.events;

import com.example.tripback.common.utils.LocalDateFormatter;
import com.example.tripback.common.utils.LocalTimeFormatter;
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

        private String startTime;
        private String endTime;

        @NotNull(message = "제목은 Null 일 수 없습니다.")
        private String title;

        private MultipartFile img;

        @NotNull
        private Long teamId;

        @Builder
        public saveRequestDto(String startDate, String endDate, String startTime, String endTime, String title) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.startTime = startTime;
            this.endTime = endTime;
            this.title = title;
        }

        public Events toEntity(){
            LocalDateFormatter localDateFormatter = new LocalDateFormatter();
            LocalTimeFormatter localTimeFormatter = new LocalTimeFormatter();
            return Events.builder()
                    .startDate(localDateFormatter.parse(startDate, KOREA))
                    .endDate(localDateFormatter.parse(endDate, KOREA))
                    .startTime(localTimeFormatter.parse(startTime, KOREA))
                    .endTime(localTimeFormatter.parse(endTime, KOREA))
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

        @Temporal(TemporalType.TIME)
        @DateTimeFormat(style = "hh:mm")
        private LocalTime startTime;
        @Temporal(TemporalType.TIME)
        @DateTimeFormat(style = "hh:mm")
        private LocalTime endTime;

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

        @DateTimeFormat(style = "hh:mm")
        private LocalTime startTime;

        @DateTimeFormat(style = "hh:mm")
        private LocalTime endTime;

        private String title;

        public eventList(Events events) {
            this.eventId = events.getEventId();
            this.startDate = events.getStartDate();
            this.endDate = events.getEndDate();
            this.startTime = events.getStartTime();
            this.endTime = events.getEndTime();
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
