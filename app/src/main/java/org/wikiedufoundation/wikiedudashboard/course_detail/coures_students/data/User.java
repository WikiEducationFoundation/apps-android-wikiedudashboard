
package org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@AllArgsConstructor
public class User {
    private Boolean admin;
    private long characterSumDraft;
    private long characterSumMs;
    private long characterSumUs;
    private Boolean contentExpert;
    private String contributionUrl;
    private String roleDescription;
    private String sandboxUrl;
    private long totalUploads;
    private String username;
    private String courseTrainingProgress;
    private String enrolledAt;
    private long id;
    private Boolean programManager;
    private long recentRevisions;
    private long role;
}
