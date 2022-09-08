package com.basetest.optional.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-7 9:51
 * @description：
 * @modified By：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private String teamName;
    private Department department;

    public Team(String teamName) {
        this.teamName = teamName;
    }
}