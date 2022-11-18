package com.git;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;

import java.util.List;

/**
 * @description:
 * @author: GooGoo
 * @time: 2022/5/14 17:45
 */
public class GitInfoGet {
    public static void main(String[] args) {
        GitLabApi gitLabApi = new  GitLabApi ( "http://139.199.13.25:3000" , "0b73cd26c97af78b31690349954d36e1a3407834" );
        try {
            List<Project> projects = gitLabApi.getProjectApi().getProjects();
            System.out.println(projects);
        } catch (GitLabApiException e) {
            e.printStackTrace();
        }
    }

}
