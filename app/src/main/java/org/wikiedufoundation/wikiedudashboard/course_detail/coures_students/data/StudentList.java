
package org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class StudentList {
    private List<User> users;
}
