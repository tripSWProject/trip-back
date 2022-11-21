package com.example.tripback.api.dto;

import com.example.tripback.api.entity.Locations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class LocationDto {

    @NoArgsConstructor
    @Getter
    public static class SaveLocation{
        @NotNull
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(style = "yyyy-MM-dd")
        private LocalDate date;
        @NotNull
        private String locations;

        @Temporal(TemporalType.TIME)
        @DateTimeFormat(style = "hh:mm")
        private LocalTime time;
        private String note;
        private String planName;

        private Long eventId;

        public Locations toEntity(){
            return Locations.builder()
                    .date(date)
                    .locations(locations)
                    .time(time)
                    .note(note)
                    .planName(planName)
                    .build();
        }
    }
}
