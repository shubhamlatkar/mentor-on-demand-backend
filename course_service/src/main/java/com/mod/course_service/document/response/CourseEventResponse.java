package com.mod.course_service.document.response;

import com.mod.course_service.document.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEventResponse {
    private String eventType;
    private CourseResponse course;
    private List<Topic> topics;
    private String title;
}
